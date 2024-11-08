pipeline {
    agent any

    tools {
        jdk 'JAVA_HOME'
        maven 'M2_HOME'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/Mohamed_Chouaibi']], 
                          userRemoteConfigs: [[url: 'https://github.com/Sleheddine34/Projet-DevOps.git']]])
            }
        }
        
        stage('Compile Stage') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('JUnit/Mockito Tests') {
            steps {
                sh 'mvn test' 
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

        stage('Build') {
            steps {
                sh 'mvn clean package'
                sh 'ls target'
            }
        }

        stage('Build Docker Image') {
            steps {  
                sh "docker build -t mohamed855/my-alpine:latest ."
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials-id', passwordVariable: 'DOCKER_PASSWORD', usernameVariable: 'DOCKER_USERNAME')]) {
                        sh "echo \$DOCKER_PASSWORD | docker login -u \$DOCKER_USERNAME --password-stdin"
                    }
                    sh 'docker push mohamed855/my-alpine:latest'
                }
            }
        }

        stage('Deploy with Docker Compose') {
            steps {
                script {
                    sh 'ls -la'
                    sh 'docker compose -f ./docker-compose.yml up -d'
                }
            }
        }

        stage('Email Notification') {
            steps {
                mail bcc: '', 
                     body: '''
Final Report: The pipeline has completed successfully. No action required.
''', 
                     cc: '', 
                     from: '', 
                     replyTo: '', 
                     subject: 'Succès de la pipeline DevOps Project', 
                     to: 'chouaibimohamed87@gmail.com, mohamed.chouaibi@esprit.tn'
            }
        }
    }

    post {
        success {
            script {
                emailext (
                    subject: "Build Success: ${currentBuild.fullDisplayName}",
                    body: "Le build a réussi ! Consultez les détails à ${env.BUILD_URL}",
                    recipientProviders: [[$class: 'CulpritsRecipientProvider'], [$class: 'DevelopersRecipientProvider']],
                    to: 'chouaibimohamed87@gmail.com, mohamed.chouaibi@esprit.tn'
                )
            }
        }
        failure {
            script {
                emailext (
                    subject: "Build Failure: ${currentBuild.fullDisplayName}",
                    body: "Le build a échoué ! Vérifiez les détails à ${env.BUILD_URL}",
                    recipientProviders: [[$class: 'CulpritsRecipientProvider'], [$class: 'DevelopersRecipientProvider']],
                    to: 'chouaibimohamed87@gmail.com, mohamed.chouaibi@esprit.tn'
                )
            }
        }
        always {
            script {
                emailext (
                    subject: "Build Notification: ${currentBuild.fullDisplayName}",
                    body: "Consultez les détails du build à ${env.BUILD_URL}",
                    recipientProviders: [[$class: 'CulpritsRecipientProvider'], [$class: 'DevelopersRecipientProvider']],
                    to: 'chouaibimohamed87@gmail.com, mohamed.chouaibi@esprit.tn'
                )
            }
        }
    }
}
