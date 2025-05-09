pipeline {
    agent {
        dockerfile {
            filename 'jenkins/Dockerfile'
            dir 'jenkins'
        }
    }

    environment {
        JAVA_HOME = '/usr/lib/jvm/java-17-openjdk-amd64'
        ANDROID_HOME = '/opt/android-sdk'
        PATH = "${ANDROID_HOME}/cmdline-tools/latest/bin:${ANDROID_HOME}/platform-tools:${ANDROID_HOME}/build-tools/33.0.2:/usr/local/bin:${env.PATH}"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Dependencies') {
            steps {
                sh './gradlew dependencies'
            }
        }

        stage('Build AAB') {
            steps {
                sh 'bundle install --path vendor/bundle'
                sh 'fastlane buildRelease'
            }
        }

        stage('Upload to Artifact') {
            steps {
                archiveArtifacts artifacts: '**/build/outputs/**/*.aab', fingerprint: true
            }
        }
    }
}
