pipeline {
    agent any

    environment {
        DEVICE_ID = "10BE571EH40009D" // get from 'adb devices'
    }

    stages {

        stage('Clone Repo') {
            steps {
                git 'https://github.com/shaikzaid7671/LFX_Automation.git'
            }
        }

        stage('Build Project') {
            steps {
                bat 'mvn clean install -DskipTests'
            }
        }

        stage('Start Appium') {
            steps {
                // Run Appium server on host machine
                bat 'start /B appium'
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
