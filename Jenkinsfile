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

        // Stage 2: Cleaning the project using Maven
        stage('MVN CLEAN') { 
            steps {
                echo "Cleaning project with Maven"
                sh 'mvn clean package'
            }
        }
/*
        // Stage 3: Compiling the project using Maven
        stage('MVN COMPILE') {
            steps {
                echo "Compiling project with Maven"
                sh 'mvn compile'
            }
        }

        // Stage 4: Packaging the project using Maven
        stage('MVN PACKAGE') {
            steps {
                echo "Packaging project with Maven"
                sh 'mvn package'
            }
        }

         
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
        

        stage('Deploy to Nexus') {
            steps {
                echo "Deploying artifacts to Nexus repository"
                sh '''
                    mvn deploy -DskipTests \
                    -DaltDeploymentRepository=deploymentRepo::default::http://192.168.33.10:8081/repository/maven-releases/
                '''
            }
        }
       
*/
        // Stage 5: Building Docker image
        stage('Building Docker Image') {
            steps {
                echo "Building Docker image for project"
                sh 'docker build -t yousseeef/tp-foyer:5.0.0 .'
            }
        }

        // Stage 6: Pushing Docker image to Docker Hub
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
    }

    post {
        always {
            echo "Job Finished"
        }
    }
}
