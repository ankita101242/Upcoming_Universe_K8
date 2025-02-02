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
                    echo 'Cleaning up existing Docker containers...'
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
                        echo 'Pushing Docker images to Docker Hub...'
                        sh 'docker push ankitaagrawal12/frontend:latest'
                        sh 'docker push ankitaagrawal12/backend:latest'
                    }
                }
            }
        }

        stage('Start Minikube') {
            steps {
                script {
                    echo 'Checking if Minikube is running...'
                    sh '''
                        if ! minikube status | grep -q "Running"; then
                            echo "Minikube is not running. Starting Minikube..."
                            minikube start
                        else
                            echo "Minikube is already running."
                        fi
                    '''
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                echo 'Applying Kubernetes deployment manifests...'
                sh '''
                    export KUBECONFIG=$KUBECONFIG
                    kubectl apply -f frontend/frontend-deployment.yaml --validate=false
                    kubectl apply -f backend/backend-deployment.yaml --validate=false
                    kubectl apply -f backend/mysql-deployment.yaml --validate=false
                '''
            }
        }

        stage('Open Minikube Dashboard') {
            steps {
                script {
                    echo 'Opening Minikube dashboard in the background...'
                    sh 'minikube dashboard &'
                }
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
