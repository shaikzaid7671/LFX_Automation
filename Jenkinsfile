pipeline {
    agent any

    stages {

        stage('Clone Code') {
            steps {
                git 'https://github.com/shaikzaid7671/LFX_Automation.git'
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
                        sh 'mvn test'
                    }
                }
            }
        }

    }
}
