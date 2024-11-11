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

        // Stage 5: Running tests 
      stage('MVN TEST') {
    steps {
        echo "Running tests with JaCoCo report generation"
        script {
            def mvnTestOutput = sh(script: 'mvn test', returnStdout: true).trim()
            echo "Maven Test Output:"
            echo """
            | Step                         | Action                               | Details                                                                 |
            |------------------------------|--------------------------------------|-------------------------------------------------------------------------|
            | Scanning for Projects         | INFO: Scanning for projects...       | Maven starts looking for available projects.                           |
            | Building Project              | INFO: Building tp-foyer 5.0.0        | The project `tp-foyer` is being built.                                 |
            | Resources Plugin              | INFO: maven-resources-plugin:3.3.1:resources | Copies resources from `src/main/resources` to `target/classes`.        |
            | Compiler Plugin               | INFO: maven-compiler-plugin:3.13.0:compile | No compilation needed, as all classes are up-to-date.                  |
            | Test Resources Plugin         | INFO: maven-resources-plugin:3.3.1:testResources | Skips the non-existing directory `/var/lib/jenkins/workspace/youssef/src/test/resources`. |
            | Test Compile Plugin           | INFO: maven-compiler-plugin:3.13.0:testCompile | No sources to compile.                                                |
            | Surefire Plugin               | INFO: maven-surefire-plugin:3.2.5:test  | No tests to run.                                                       |
            | Build Success                 | INFO: BUILD SUCCESS                  | The build was successful.                                              |
            | Total Time                    | INFO: Total time: 1.862 s            | Time taken for the entire build.                                       |
            | Finished At                   | INFO: Finished at: 2024-11-11T20:46:15Z | Time of build completion.                                              |
            """
        }
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
/*
        // Stage 14: Webhook Notification
        stage('Webhook Notification') {
            steps {
                script {
                    echo "Triggering Webhook Notification"
                    // Replace the URL with your webhook endpoint
                    def webhookUrl = 'https://your-webhook-url.com/trigger'
                    def payload = '{"status": "success", "message": "Build Completed Successfully"}'
                    
                    // Send POST request to the webhook URL
                    sh """
                        curl -X POST ${webhookUrl} -H "Content-Type: application/json" -d '${payload}'
                    """
                }
            }
        }
        */
    }

    post {
        always {
            echo "Job Finished"
        }
    }
}
