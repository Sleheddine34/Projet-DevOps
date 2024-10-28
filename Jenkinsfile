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
                withCredentials([string(credentialsId: 'sonar-token', variable: 'SONAR_TOKEN')]) {
                    sh "mvn sonar:sonar -Dsonar.projectKey=JenkinsFile -Dsonar.host.url=http://192.168.33.10:9000 -Dsonar.login=$SONAR_TOKEN"
                }
            }
        }
        stage('Deploy to Nexus') {
    steps {
        sh 'mvn deploy -DskipTests -DaltDeploymentRepository=deploymentRepo::default::http://192.168.33.10:8082/repository/maven-releases/'
          }
       }
    }
}