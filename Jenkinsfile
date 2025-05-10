pipeline {
    agent any

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
