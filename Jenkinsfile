pipeline {
    agent any

    tools {
        jdk 'JAVA_HOME'
        maven 'M2_HOME'
    }

    stages {
        stage('GIT') {
            steps {
                git branch: 'master',
                    url: 'https://github.com/hwafa/timesheetproject.git'
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
            environment {
                SONARQUBE_URL = 'http://192.168.33.10:9000'
            }
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh "mvn sonar:sonar -Dsonar.host.url=${SONARQUBE_URL} -Dsonar.login=squ_77746f6cb69cc6b21d898275a6f50cdfa7405e97"
                }
            }
        }

    }
}