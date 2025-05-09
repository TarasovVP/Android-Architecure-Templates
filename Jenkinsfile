pipeline {
    agent any

    environment {
        JAVA_HOME = tool name: 'JDK 17', type: 'jdk'
        ANDROID_HOME = '/opt/android-sdk'
        PATH = "${ANDROID_HOME}/cmdline-tools/latest/bin:${ANDROID_HOME}/platform-tools:${ANDROID_HOME}/build-tools/33.0.2:${env.PATH}"
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
                sh '''
                export DEBIAN_FRONTEND=noninteractive
                apt-get update -y
                apt-get install -y ruby-full build-essential libffi-dev libssl-dev
                '''

                sh 'gem install bundler -N'
                sh 'gem install fastlane -N'

                sh 'bundle install --path vendor/bundle'
                sh 'fastlane buildRelease'
            }
        }

        stage('Upload to Artifact') {
            steps {
                archiveArtifacts artifacts: 'app/build/outputs/**/*.aab', fingerprint: true
            }
        }
    }
}
