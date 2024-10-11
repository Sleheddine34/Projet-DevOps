pipeline {
    agent any

    tools {
        jdk '$JAVA_HOME'
        maven '$M2_HOME'
    }


    stages {
        stage('Clone Timesheet Project') {
            steps {
                git branch: 'master',
                    url: 'https://github.com/hwafa/timesheetproject.git'
            }
        }

        stage('Compile Timesheet Project') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Clone DevOps Project') {
            steps {
                echo "Cloning DevOps project from Git"
                git branch: 'master',
                    url: 'https://github.com/Sleheddine34/Projet-DevOps.git'
            }
        }

        stage('MVN CLEAN') {
            steps {
                sh 'mvn clean'     
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
                    withSonarQubeEnv('SonarQubeServer') { // Replace 'SonarQubeServer' with the actual name of your SonarQube instance
                        sh 'mvn clean verify sonar:sonar \
                            -Dsonar.login=$admin \
                            -Dsonar.password=$201JMt2434**
                    }
                }
            }
        }
    }
}
