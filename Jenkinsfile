pipeline {
    agent any

    tools {
        jdk 'JAVA_HOME'
        maven 'M2_HOME'
    }

    stages {
         stage('Git') {
            steps {
                echo "Getting project from GIT";
                checkout([$class: 'GitSCM', branches: [[name: '*/testSleh']], 
                          userRemoteConfigs: [[url: 'https://github.com/Sleheddine34/Projet-DevOps.git']]])
            }
        }
        stage('Compile Stage') {
            steps {
                sh 'mvn clean compile -DskipTests';
                sh 'mvn clean package'
            }
        }
        // Test stage removed/commented out
        // stage('Test Stage') {
        //     steps {
        //         sh 'mvn clean test'
        //     }
        // }
        stage('SonarQube Analysis') {
            steps {
                withCredentials([string(credentialsId: 'sonar-token', variable: 'SONAR_TOKEN')]) {
                    sh "mvn sonar:sonar -DskipTests -Dsonar.projectKey=JenkinsFile -Dsonar.host.url=http://192.168.50.4:9000 -Dsonar.login=\$SONAR_TOKEN"
                }
            }
        }
        stage('Deploy to Nexus') {
            steps {
                sh 'mvn deploy -DskipTests -DaltDeploymentRepository=deploymentRepo::default::http://192.168.50.4:8081/repository/maven-releases/'
            }
        }
        stage('Build Docker Image') {
            steps {
                sh 'docker build -t sleheddine/tp-foyer:5.0.0 .'
            }
        }
//         stage('Build Docker Image') {
//             steps {
                
//                     // Build Docker image using the Docker CLI command
//                     sh "docker build -t sleheddine/alpine:latest ."
                
//             }
//         }
//         stage('Push Docker Image') {
//            steps {
//                script {
//                 // Login to Docker Hub (using credentials stored in Jenkins)
//                 withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials-id', passwordVariable: 'DOCKER_PASSWORD', usernameVariable: 'DOCKER_USERNAME')]) {
//                 // Ensure successful login
//                 sh "echo \$DOCKER_PASSWORD | docker login -u \$DOCKER_USERNAME --password-stdin"
//             }
            
//                 // Push the image to Docker Hub
//                 sh 'docker push sleheddine/alpine:latest'
//         }
//     }
// }
//           stage('Deploy with Docker Compose') {
//             steps {
//               script {
//               // List the contents of the current directory to confirm the presence of docker-compose.yml
//               sh 'ls -la'
//               // Run Docker Compose to bring up services from the file in the repository
//               sh 'docker-compose -f ./docker-compose.yml up -d'
//         }
//     }
// }
    }
}
