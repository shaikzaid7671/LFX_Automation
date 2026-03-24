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
                bat 'timeout /t 10'
            }
        }

        stage('Run LoginTest') {
            steps {
                bat "mvn test -Dtest=LoginTest -DdeviceId=${DEVICE_ID}"
            }
        }
    }
}
