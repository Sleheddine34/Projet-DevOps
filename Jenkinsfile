pipeline {
    agent any

    tools {
        jdk 'JAVA_HOME' // Ensure this matches the JDK version you have installed (17)
        maven 'M2_HOME' // Ensure this matches your Maven installation
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'joseph',
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
                withCredentials([string(credentialsId: 'sonar-token', variable: 'SONAR_TOKEN')]) {
                    sh "mvn sonar:sonar -Dsonar.projectKey=JenkinsFile -Dsonar.host.url=http://192.168.50.4:9000 -Dsonar.login=$SONAR_TOKEN"
                }
            }
        }
        stage('Deploy to Nexus') {
            steps {
                sh 'mvn deploy -DskipTests -DaltDeploymentRepository=deploymentRepo::default::http://192.168.50.4:8081/repository/maven-releases/'
            }
        }
    }

    post {
        always {
            echo "Job Finished"
        }
    }
}
