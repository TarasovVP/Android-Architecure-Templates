pipeline {
    agent any

    environment {
        JAVA_HOME = "/usr/lib/jvm/temurin-17"
        PATH = "${JAVA_HOME}/bin:${env.PATH}"
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
                sh 'bundle install'
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
