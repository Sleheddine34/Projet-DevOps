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

        // Stage 5: Running tests and generating JaCoCo report
        stage('MVN TEST') {
            steps {
                echo "Running tests with JaCoCo report generation"
                sh 'mvn test'
            }
        }

        // Stage 6: Generating JaCoCo coverage report
        stage('JaCoCo Report') {
            steps {
                echo "Generating JaCoCo coverage report"
                sh 'mvn jacoco:report'
            }
        }

        // Stage 7: Publishing JaCoCo coverage report
        stage('JaCoCo coverage report') {
            steps {
                step([$class: 'JacocoPublisher',
                      execPattern: '**/target/jacoco.exec', // Fichier de couverture généré par JaCoCo
                      classPattern: '**/classes', // Modèle des classes compilées
                      sourcePattern: '**/src', // Modèle des sources du projet
                      exclusionPattern: '*/target/**/,**/*Test*,**/*_javassist/**' // Exclusions des fichiers à ignorer
                ])
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

        // Stage 13: Send Email Notification
        stage('Send Email Notification') {
            steps {
                script {
                    // Envoi d'un email de notification
                    mail to: 'fakhfakh4321@gmail.com',
                         subject: 'Jenkins Notification: Docker Image Pushed',
                         body: 'A new Docker image has been successfully pushed to DockerHub.'
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
