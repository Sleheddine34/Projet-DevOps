pipeline {
    agent any

    tools {
        jdk '$JAVA_HOME'
        maven '$M2_HOME'
    }
 stages {
        stage('GIT') {
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

     stage('MVN SONARQUBE') {
    steps {
        script {
            withSonarQubeEnv('SonarQubeServer') { // This should match the name in the Jenkins configuration
                sh '''mvn clean verify sonar:sonar -Dsonar.login=$admin -Dsonar.password=$201JMt2434**'''
            }
        }
    }
}

    }
}


























    
    
