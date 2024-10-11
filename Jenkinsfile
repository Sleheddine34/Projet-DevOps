pipeline {
    agent any

    tools {
        jdk '$JAVA_HOME'
        maven '$M2_HOME'
    }

    stages {
        stage('Compile Stage') {
            steps {
                sh 'mvn clean compile'
            }
        }
         stage('GIT') {
            steps {
                echo "getting Projet from Git"
                url: 'https://github.com/Sleheddine34/Projet-DevOps.git'
            }
        }
        stage('MVN CLEAN') {
            steps {
                sh 'mvn compile'     
            }
        }  
        stage('MVN COMPILE') {
            steps {
                sh 'mvn compile'     
            }
        }  
        stage('MVN SONARQUBE') {
            steps {
                script {
                    sh 'mvn clean verify sonar:sonar \
                        -Dsonar.password=201JMt2434**'
            }
        }
    }
  }
}
