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
        withSonarQubeEnv('SonarQubeServer') { // This matches the name configured in Jenkins
            sh "mvn sonar:sonar -Dsonar.projectKey=tp-foyer -Dsonar.login=${SONAR_AUTH_TOKEN}"
        }
    }
}
    }
}
