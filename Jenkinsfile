pipeline {
    agent any

    environment {
        DOCKER_IMAGE_BACKEND = 'ankitaagrawal12/backend:latest'
        DOCKER_IMAGE_FRONTEND = 'ankitaagrawal12/frontend:latest'
    }

    stages {
        stage('Checkout Code') {
            steps {
                echo 'Checking out code from repository...'
                git url: 'https://github.com/ankita101242/Upcoming_Universe_K8.git', branch: 'main'
            }
        }

        stage('Build and Push Frontend Docker Image') {
            steps {
                echo 'Building and pushing frontend Docker image...'
                script {
                    docker.build(DOCKER_IMAGE_FRONTEND, './frontend').push()
                }
            }
        }

        stage('Build and Push Backend Docker Image') {
            steps {
                echo 'Building and pushing backend Docker image...'
                script {
                    docker.build(DOCKER_IMAGE_BACKEND, './backend').push()
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                echo 'Applying Kubernetes deployment manifests...'
                sh '''
                    kubectl apply -f frontend/frontend-deployment.yaml
                    kubectl apply -f backend/backend-deployment.yaml
                    kubectl apply -f backend/mysql-deployment.yaml
                '''
            }
        }
    }

    post {
        success {
            echo 'Pipeline executed successfully!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}
