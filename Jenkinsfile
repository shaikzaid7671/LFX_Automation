pipeline {
    agent any

    environment {
        DEVICE_ID = "10BE571EH40009D"
    }

    stages {

        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("lfx-automation")
                }
            }
        }

        stage('Build Project') {
            steps {
                bat 'mvn clean install -DskipTests'
            }
        }

        stage('Start Appium') {
            steps {
                bat 'start /B appium'
                bat 'ping 127.0.0.1 -n 10 > nul'
            }
        }

        stage('Run Tests') {
            steps {
                bat "mvn test -Dtest=LoginTest -DdeviceId=%DEVICE_ID%"
            }
        }
    }
}
