pipeline {
    agent any

    tools {
        jdk 'JAVA_HOME' // Assurez-vous que 'JAVA_HOME' correspond bien au nom configuré
        maven 'M2_HOME' // Assurez-vous que 'M2_HOME' correspond bien au nom configuré
    }

    stages {
        stage('Git') {
            steps {
                echo "Getting project from GIT"
                checkout([$class: 'GitSCM', branches: [[name: '*/testSleh']], 
                          userRemoteConfigs: [[url: 'https://github.com/Sleheddine34/Projet-DevOps.git']]])
            }
        }
        stage('Compile Stage') {
            steps {
                sh 'mvn clean install -DskipTests'
                sh 'mvn package -DskipTests'
            }
        }
        stage('Test Stage') {
            steps {
                sh 'mvn test jacoco:report'
            }
        }
        stage('SonarQube Analysis') {
            steps {
                withCredentials([string(credentialsId: 'sonar-token', variable: 'SONAR_TOKEN')]) {
                    sh 'mvn sonar:sonar -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml'
                }
            }
        }
        stage('Deploy to Nexus') {
            steps {
                sh 'mvn deploy -DaltDeploymentRepository=deploymentRepo::default::http://192.168.50.4:8081/repository/maven-releases/'
            }
        }
        stage('Build Docker Image') {
            steps {
                sh 'docker build -t sleheddine/tp-foyer:5.0.0 .'
            }
        }
        stage('Push Docker Image to DockerHub') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials-id', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                    sh '''
                        echo $DOCKER_PASSWORD | docker login -u $DOCKER_USERNAME --password-stdin
                        docker tag sleheddine/tp-foyer:5.0.0 sleheddine/tp-foyer:latest
                        docker push sleheddine/tp-foyer:5.0.0
                        docker push sleheddine/tp-foyer:latest
                    '''
                }
            }
        }
        stage('Send Email Notification') {
            steps {
                script {
                    // Envoi d'un email de notification
                    mail to: 'sleheddinedhaouadi@gmail.com',
                         subject: 'Jenkins Notification: Docker Image Pushed',
                         body: 'A new Docker image has been successfully pushed to DockerHub.'
                }
            }
        }
        stage('Clean Up Previous Containers') {
            steps {
                script {
                    // Supprime tous les conteneurs liés à ce projet pour éviter des conflits
                    sh 'docker-compose down -v || true'
                }
            }
        }
        stage('Run Docker Compose') {
            steps {
                script {
                    sh '''
                        pwd
                        ls -la
                        docker-compose down -v
                        docker-compose up -d
                        docker-compose ps
                    '''
                }
            }
        }
        stage('Check and Start Prometheus') {
            steps {
                script {
                    def prometheusRunning = sh(script: 'docker ps -q -f name=prometheus', returnStdout: true).trim()
                    if (prometheusRunning) {
                        echo 'Prometheus is already running.'
                    } else {
                        echo 'Starting Prometheus container...'
                        sh 'docker start prometheus'
                    }
                }
            }
        }
        stage('Check and Start Grafana') {
            steps {
                script {
                    def grafanaRunning = sh(script: 'docker ps -q -f name=grafana', returnStdout: true).trim()
                    if (grafanaRunning) {
                        echo 'Grafana is already running.'
                    } else {
                        echo 'Starting Grafana container...'
                        sh 'docker start grafana'
                    }
                }
            }
        }
    }
}
