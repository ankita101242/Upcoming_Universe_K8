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

        stage('Build Docker Images') {
            steps {
                script {
                    echo 'Building backend Docker image...'
                    dir('./backend') {
                        docker.build(DOCKER_IMAGE_BACKEND, '.')
                    }
                    echo 'Building frontend Docker image...'
                    dir('./frontend') {
                        docker.build(DOCKER_IMAGE_FRONTEND, '.')
                    }
                }
            }
        }

        stage('Push Docker Images') {
            steps {
                script {
                    echo 'Pushing backend Docker image...'
                    withCredentials([usernamePassword(credentialsId: 'DockerHubCred', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                        sh 'echo $DOCKER_PASSWORD | docker login -u $DOCKER_USERNAME --password-stdin'
                        sh "docker push ${DOCKER_IMAGE_BACKEND}"
                        sh 'docker logout'
                    }
                    echo 'Pushing frontend Docker image...'
                    withCredentials([usernamePassword(credentialsId: 'DockerHubCred', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                        sh 'echo $DOCKER_PASSWORD | docker login -u $DOCKER_USERNAME --password-stdin'
                        sh "docker push ${DOCKER_IMAGE_FRONTEND}"
                        sh 'docker logout'
                    }
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
