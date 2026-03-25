pipeline {
    agent any

    environment {
        DEVICE_ID = "10BE571EH40009D"
    }

    stages {

        stage('Build') {
            steps {
                bat 'mvn clean install -DskipTests'
            }
        }

        stage('Start Appium') {
            steps {
                bat 'start cmd /c appium'
                bat 'ping 127.0.0.1 -n 10 > nul'
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("lfx-automation")
                }
            }
        }
            stage('Run Tests in Container') {
            steps {
                script {
                    docker.image("lfx-automation").inside {
                        bat 'mvn test'
                    }
                }
            }
        }

        stage('Run LoginTest') {
            steps {
                bat "mvn test -Dtest=LoginTest -DdeviceId=%DEVICE_ID%"
            }
        }
    }
}

        
