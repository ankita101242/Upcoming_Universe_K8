pipeline {
    agent any

    environment {
        GITHUB_REPO_URL = 'https://github.com/ankita101242/Upcoming_Universe_K8.git'
        KUBECONFIG = credentials('kubeconfig-id')
    }

    stages {

        stage('Cleanup') {
            steps {
                script {
                    sh 'docker rm -f frontend || true'
                    sh 'docker rm -f backend || true'
                }
            }
        }
        
        stage('Checkout Code') {
            steps {
                echo 'Checking out code from repository...'
                git branch: 'main', url: "${GITHUB_REPO_URL}"
            }
        }

        stage('Build Docker Images') {
            steps {
                script {
                    echo 'Building backend Docker image...'
                    dir('./backend') {
                        docker.build("ankitaagrawal12/backend", '.')
                    }
                    echo 'Building frontend Docker image...'
                    dir('./frontend') {
                        docker.build("ankitaagrawal12/frontend", '.')
                    }
                }
            }
        }

        stage('Push Docker Images') {
            steps {
                script {
                    docker.withRegistry('', 'DockerHubCred') {
                        sh 'docker push ankitaagrawal12/frontend:latest'
                        sh 'docker push ankitaagrawal12/backend:latest'
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
