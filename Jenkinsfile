pipeline {
    agent any

    stages {
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
