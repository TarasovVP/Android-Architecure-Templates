pipeline {
    agent any

    environment {
        ANDROID_HOME = '/opt/android-sdk'
        PATH = "${ANDROID_HOME}/cmdline-tools/latest/bin:${ANDROID_HOME}/platform-tools:${ANDROID_HOME}/build-tools/33.0.2:${env.PATH}"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Create local.properties') {
            steps {
                withCredentials([
                    string(credentialsId: 'CLOUD_URL', variable: 'CLOUD_URL'),
                    string(credentialsId: 'JDBC_URL', variable: 'JDBC_URL'),
                    string(credentialsId: 'DRIVER_CLASS_NAME', variable: 'DRIVER_CLASS_NAME'),
                    string(credentialsId: 'DB_USER_NAME', variable: 'DB_USER_NAME'),
                    string(credentialsId: 'DB_USER_PASSWORD', variable: 'DB_USER_PASSWORD')
                ]) {
                    sh '''
                        cat <<EOF > shared/local.properties
                        CLOUD_URL=$CLOUD_URL
                        JDBC_URL=$JDBC_URL
                        DRIVER_CLASS_NAME=$DRIVER_CLASS_NAME
                        DB_USER_NAME=$DB_USER_NAME
                        DB_USER_PASSWORD=$DB_USER_PASSWORD
                        EOF
                    '''
                }
            }
        }

        stage('Dependencies') {
            steps {
                sh './gradlew dependencies'
            }
        }

        stage('Install Ruby & Fastlane deps') {
            steps {
                sh 'bundle install --path vendor/bundle'
            }
        }

        stage('Build AAB') {
            steps {
                sh 'bundle exec fastlane buildRelease'
            }
        }

        stage('Upload to Artifact') {
            steps {
                archiveArtifacts artifacts: '**/build/outputs/**/*.aab', fingerprint: true
            }
        }
    }
}
