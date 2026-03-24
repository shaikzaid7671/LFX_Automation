pipeline {
    agent any

    environment {
        DEVICE_ID = "10BE571EH40009D"
    }

    stages {

        stage('Build Project') {
            steps {
                bat 'mvn clean install -DskipTests'
            }
        }

        stage('Start Appium') {
            steps {
                bat 'start cmd /c appium'
                bat 'timeout /t 15'
            }
        }

        stage('Run Tests') {
            steps {
                bat "mvn test -DdeviceId=${DEVICE_ID}"
            }
        }

        stage('Allure Report') {
            steps {
                allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: '**/target/surefire-reports/*.xml', allowEmptyArchive: true
        }
    }
}
