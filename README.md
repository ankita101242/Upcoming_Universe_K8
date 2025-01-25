# Upcoming_Universe_K8
<p>Upcoming Universe K8
Overview</p>
<p>Upcoming Universe K8 is a project aimed at automating the deployment of a full-stack application using Kubernetes, Docker, and Continuous Integration/Continuous Deployment (CI/CD) pipelines. It involves deploying a frontend, backend, and database using Kubernetes, while also implementing CI/CD practices to streamline the development process.
Features</p>
<pre><code>Frontend: React-based user interface that interacts with the backend services.
Backend: A service developed using your chosen stack (e.g., Node.js, Spring Boot, etc.) to manage business logic and database communication.
Database: A MySQL database deployed alongside the backend for persistent data storage.
CI/CD Pipeline: Automated build, test, and deployment pipeline using Jenkins, Docker, and Kubernetes.
</code></pre>
<p>Architecture</p>
<pre><code>Frontend: Built with React.js, Dockerized, and deployed to Kubernetes.
Backend: The backend service is containerized with Docker and deployed on Kubernetes.
Database: MySQL database is deployed and managed within the Kubernetes cluster.
CI/CD Pipeline: Jenkins pipeline to build Docker images, push them to Docker Hub, and deploy to Kubernetes.
</code></pre>
<p>Prerequisites</p>
<pre><code>Docker: For building and running containerized applications.
Kubernetes: For orchestrating containerized services.
Jenkins: For setting up and managing the CI/CD pipeline.
GitHub: Repository for version control and code management.
</code></pre>
<p>Setup Instructions</p>
<ol>
<li>Clone the Repository</li>
</ol>
<p>git clone <a href="https://github.com/ankita101242/Upcoming_Universe_K8.git">https://github.com/ankita101242/Upcoming_Universe_K8.git</a>
cd Upcoming_Universe_K8</p>
<ol start="2">
<li><p>Configure Docker</p>
<p> Build and push Docker images for both frontend and backend:</p>
</li>
</ol>
<p>docker build -t ankitaagrawal12/frontend ./frontend
docker build -t ankitaagrawal12/backend ./backend
docker push ankitaagrawal12/frontend
docker push ankitaagrawal12/backend</p>
<ol start="3">
<li><p>Set up Kubernetes Deployment</p>
<p> Ensure your Kubernetes cluster is set up and kubectl is configured to access it.
 Apply Kubernetes manifests to deploy the application:</p>
</li>
</ol>
<p>kubectl apply -f frontend/frontend-deployment.yaml
kubectl apply -f backend/backend-deployment.yaml
kubectl apply -f backend/mysql-deployment.yaml</p>
<ol start="4">
<li><p>Configure CI/CD Pipeline (Jenkins)</p>
<p> Set up Jenkins with a pipeline that:
 Clones the GitHub repository.
 Builds Docker images.
 Pushes them to Docker Hub.
 Deploys the containers to Kubernetes.</p>
</li>
</ol>
<p>Ensure the following Jenkins credentials are set up:</p>
<pre><code>DockerHubCred: Your Docker Hub credentials.
kubeconfig-id: Your Kubernetes config file credentials.
</code></pre>
<p>Usage</p>
<p>Once the CI/CD pipeline runs successfully, your application will be deployed on the Kubernetes cluster. You can access the frontend via the service endpoint exposed by the cluster.
Future Enhancements</p>
<pre><code>Implement multi-environment deployment (e.g., development, staging, production).
Integrate automated testing in the CI/CD pipeline.
Improve monitoring and logging for better observability in Kubernetes.
</code></pre>
<p>License</p>
<p>This project is licensed under the MIT License - see the LICENSE file for details.</p>
