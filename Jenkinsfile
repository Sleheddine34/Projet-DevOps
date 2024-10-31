pipeline {
    agent any

    tools {
        jdk 'JAVA_HOME'
        maven 'M2_HOME'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'master',
                    url: 'https://github.com/Sleheddine34/Projet-DevOps.git'
            }
        }
        stage('Compile Stage') {
            steps {
                sh 'mvn clean compile'
            }
        }
        stage('SonarQube Analysis') {
            steps {
                withCredentials([string(credentialsId: 'sq1', variable: 'SONAR_TOKEN')]) {
                    sh "mvn sonar:sonar -Dsonar.projectKey=JenkinsFile -Dsonar.host.url=http://192.168.33.10:9000 -Dsonar.login=$SONAR_TOKEN"
                }
            }
        }
        stage('Deploy to Nexus') {
    steps {
        sh 'mvn deploy -DskipTests -DaltDeploymentRepository=deploymentRepo::default::http://192.168.33.10:8082/repository/maven-releases/'
          }
       }
  stage('Build Docker Image') {
            steps {
                
                    // Build Docker image using the Docker CLI command
                   sh "docker build -t mohamed855/my-alpine:latest ."

                
            }
        }

  stage('Deploy with Docker Compose') {
    steps {
        script {
            // List the contents of the current directory to confirm the presence of docker-compose.yml
            sh 'ls -la'
            // Run Docker Compose to bring up services from the file in the repository
            sh 'docker-compose -f ./docker-compose.yml up -d'
        }
    }
}


    }
}