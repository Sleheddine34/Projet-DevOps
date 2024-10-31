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
                checkout([$class: 'GitSCM', branches: [[name: '*/testSleh']], 
                          userRemoteConfigs: [[url: 'https://github.com/Sleheddine34/Projet-DevOps.git']]])
            }
        }
        stage('Compile Stage') {
            steps {
                sh 'mvn clean compile -DskipTests'
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
                    sh "docker build -t sleheddine/alpine:latest ."
                
            }
        }
    }
}
