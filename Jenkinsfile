pipeline {
    agent any
    environment {
        DOCKER = 'sudo docker'
    }

    stages {
        stage('Clone Repository') {
            steps {
                checkout scm
                echo 'Checkout Scm'
            }
        }

        stage('Build image') {
            steps {
                sh 'ls -al'
                sh 'chmod +x ./gradlew'
                sh './gradlew build'
                sh 'docker build -t jandb:msaboard .'
                echo 'Build image...'
            }
        }

        stage('Remove Previous image') {
            steps {
                script {
                    try {
                        sh 'docker stop boardService'
                        sh 'docker rm boardService'
                    } catch (e) {
                        echo 'fail to stop and remove container'
                    }
                }
            }
        }
        stage('Run New image') {
            steps {
                sh 'docker run --name boardService -d -p 8300:8300 -e USE_PROFILE=dev jandb:msaboard'
                echo 'Run New member image'
            }
        }
    }
}