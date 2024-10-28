pipeline {
    agent any

     tools {
        jdk 'JAVA_HOME'
        maven 'M2_HOME'
    }

   stages {
         stage('Checkout') {
            steps {
                // Replace 'your-branch-name' with the correct branch name
                checkout([$class: 'GitSCM', branches: [[name: '*/MedAchrafSabbagh']], 
                          userRemoteConfigs: [[url: 'https://github.com/Sleheddine34/Projet-DevOps.git']]])
            }
        }
        stage('Compile Stage') {
            steps {
                sh 'mvn clean compile'
            }
        }
       stage('SonarQube Analysis') {
    steps {
        withSonarQubeEnv('SonarQubeServer') { // This matches the name configured in Jenkins
            sh "mvn sonar:sonar -Dsonar.projectKey=tp-foyer -Dsonar.login=${SONAR_AUTH_TOKEN}"
        }
    }
}
        stage('Build') {
            steps {
                // Compile and package the application
                sh 'mvn clean package'
                // Verify that the .jar file exists
                sh 'ls target'
            }
        }
  stage('Build Docker Image') {
            steps {
                
                    // Build Docker image using the Docker CLI command
                    sh "docker build -t masdmz/alpine:latest ."
                
            }
        }
       
stage('Push Docker Image') {
    steps {
        script {
            // Login to Docker Hub (using credentials stored in Jenkins)
            withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials-id', passwordVariable: 'DOCKER_PASSWORD', usernameVariable: 'DOCKER_USERNAME')]) {
                // Ensure successful login
                sh "echo \$DOCKER_PASSWORD | docker login -u \$DOCKER_USERNAME --password-stdin"
            }
            
            // Push the image to Docker Hub
            sh 'docker push masdmz/alpine:latest'
        }
    }
}

        
    }
}
