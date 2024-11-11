pipeline {
    agent any

    tools {
        jdk 'JAVA_HOME'
        maven 'M2_HOME'
    }

    environment {
        TARGET_BRANCH = 'testSleh'
    }

    stages {
        stage('Check Branch') {
            when {
                expression {
                    return env.BRANCH_NAME == TARGET_BRANCH
                }
            }
            steps {
                echo "Branch check passed. Current branch: ${env.BRANCH_NAME}"
            }
        }
        
        stage('Git') {
            steps {
                echo "Getting project from GIT"
                checkout([$class: 'GitSCM', branches: [[name: "*/${TARGET_BRANCH}"]], 
                          userRemoteConfigs: [[url: 'https://github.com/Sleheddine34/Projet-DevOps.git']]])
            }
        }
        stage('Compile Stage') {
            steps {
                sh 'mvn clean compile -DskipTests'
                sh 'mvn clean package'
            }
        }
        stage('Test Stage') {
            steps {
                sh 'mvn test'
            }
        }
        stage('SonarQube Analysis') {
            steps {
                withCredentials([string(credentialsId: 'sonar-token', variable: 'SONAR_TOKEN')]) {
                    sh "mvn sonar:sonar -DskipTests -Dsonar.projectKey=JenkinsFile -Dsonar.host.url=http://192.168.50.4:9000 -Dsonar.login=\$SONAR_TOKEN"
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
                        docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD
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
                    mail to: 'sleheddinedhaouadi@gmail.com',
                         subject: 'Jenkins Notification: Docker Image Pushed',
                         body: 'A new Docker image has been successfully pushed to DockerHub.'
                }
            }
        }
        stage('Clean Up Previous Containers') {
            steps {
                script {
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
    
    post {
        always {
            script {
                if (env.BRANCH_NAME != TARGET_BRANCH) {
                    echo "Skipping build as this pipeline is configured only to run for branch: ${TARGET_BRANCH}"
                    currentBuild.result = 'NOT_BUILT'
                    error("Pipeline stopped as it is not configured for branch ${env.BRANCH_NAME}")
                }
            }
        }
    }
}
