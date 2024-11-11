pipeline {
    agent any

    stages {
        // Stage 1: Cloning code from GitHub
        stage('GIT') { 
            steps {
                echo "Cloning project from GitHub"
                git url: 'https://github.com/Sleheddine34/Projet-DevOps.git', branch: 'joseph'
            }
        }
        // Stage 2: Pre-commit Security Hooks
        stage('Pre-commit Security Hooks') {
            steps {
                script {
                    sh '''
                    if ! command -v pre-commit &> /dev/null
                    then
                        echo "pre-commit is not installed, installing in a virtual environment..."
                        python3 -m venv venv
                        . venv/bin/activate
                        pip install pre-commit
                    fi
                    # Disable existing Git hooks to avoid conflicts
                    git config --unset-all core.hooksPath
                    # Install pre-commit hooks
                    pre-commit install
                    # Run pre-commit checks
                    pre-commit run --all-files
                    '''
                }
            }
        }

        // Commit Phase: JUnit/Mockito Security Unit Tests
        stage('MVN CLEAN') { 
            steps {
                echo "Cleaning project with Maven"
                sh 'mvn clean package'
            }
        }

        // Stage 3: Compiling the project using Maven
        stage('MVN COMPILE') {
            steps {
                echo "Compiling project with Maven"
                sh 'mvn compile'
            }
        }
        

        // Stage 4: JUnit test
        stage('JUnit/Mockito Tests') {
            steps {
                sh 'mvn test' 
            }
        }
    


        // Stage 8: MVN SONARQUBE analysis
        
        stage('MVN SONARQUBE') {
            steps {
                echo "Analyzing code quality with SonarQube"
                sh '''
                    mvn sonar:sonar \
                    -Dsonar.projectKey=tn.esprit:tp-foyer \
                    -Dsonar.host.url=http://192.168.33.10:9000 \
                    -Dsonar.login=sqa_c454a45c9a349e8975f7096ac3e5436da30ec05e
                '''
            }
        }
        // Acceptance Phase: Security Scanning Tools

         stage('Security Scan: Nmap') {
            steps {
                echo "Starting Nmap security scan..."
                sh 'nmap -sT -p 1-65535 -v 192.168.33.10'
            }
}

        // Stage 9: Deploy to Nexus repository
        stage('Deploy to Nexus') {
            steps {
                echo "Deploying artifacts to Nexus repository"
                sh '''
                    mvn deploy -DskipTests \
                    -DaltDeploymentRepository=deploymentRepo::default::http://192.168.33.10:8081/repository/maven-releases/
                '''
            }
        }

        // Stage 10: Building Docker image
        stage('Building Docker Image') {
            steps {
                echo "Building Docker image for project"
                sh 'docker build -t yousseeef/tp-foyer:5.0.0 .'
            }
        }

        // Stage 11: Pushing Docker image to DockerHub
        stage('Push Docker Image to DockerHub') {
            steps {
                echo "Pushing Docker image to Docker Hub"
                // Avoid using hardcoded credentials in a real project
                sh '''
                    echo "Logging in to Docker Hub"
                    echo "Lool1234&" | docker login -u yousseeef --password-stdin
                    docker push yousseeef/tp-foyer:5.0.0
                '''
            }
        }

        // Stage 12: Run Docker Compose
        stage('Run Docker Compose') {
            steps {
                script {
                    sh '''
                        sudo docker-compose down 
                        sudo docker-compose up -d
                    '''
                }
            }
        }
                // Operations Phase: Container and Pipeline Monitoring

        // Stage 13: Monitoring Containers

         stage('Start Monitoring Containers') {
            steps {
                sh 'docker start aab0f831dce5'
            }
        }

        // Stage 14: Send Email Notification
        stage('Email Notification') {
            steps {
                mail bcc: '', 
                     body: '''Final Report: The pipeline has completed successfully. No action required.''', 
                     cc: '', 
                     from: '', 
                     replyTo: '', 
                     subject: 'Succès de la pipeline DevOps Project', 
                     to: 'fakhfakh4321@gmail.com, youssef.fakhfakh@esprit.tn'
            }
        }
 post {
        success {
            script {
                emailext (
                    subject: "Build Success: ${currentBuild.fullDisplayName}",
                    body: "Le build a réussi ! Consultez les détails à ${env.BUILD_URL}",
                    recipientProviders: [[$class: 'CulpritsRecipientProvider'], [$class: 'DevelopersRecipientProvider']],
                    to: 'fakhfakh4321@gmail.com, youssef.fakhfakh@esprit.tn'
                )
            }
        }
        failure {
            script {
                emailext (
                    subject: "Build Failure: ${currentBuild.fullDisplayName}",
                    body: "Le build a échoué ! Vérifiez les détails à ${env.BUILD_URL}",
                    recipientProviders: [[$class: 'CulpritsRecipientProvider'], [$class: 'DevelopersRecipientProvider']],
                    to: 'fakhfakh4321@gmail.com, youssef.fakhfakh@esprit.tn'
                )
            }
        }
        always {
            script {
                emailext (
                    subject: "Build Notification: ${currentBuild.fullDisplayName}",
                    body: "Consultez les détails du build à ${env.BUILD_URL}",
                    recipientProviders: [[$class: 'CulpritsRecipientProvider'], [$class: 'DevelopersRecipientProvider']],
                     to: 'fakhfakh4321@gmail.com, youssef.fakhfakh@esprit.tn'
                )
            }
        }
    }
    }

    post {
        always {
            echo "Job Finished"
        }
    }
}
