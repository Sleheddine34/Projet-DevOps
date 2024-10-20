pipeline {
    agent any

    stages {
        // Récupération du code depuis GitHub
        stage('GIT') { 
            steps {
                echo "Getting Project from Git"
                git url: 'https://github.com/Sleheddine34/Projet-DevOps.git', branch: 'joseph'
            }
        }

        // Nettoyage du projet avec Maven
        stage('MVN CLEAN') { 
            steps {
                echo "Cleaning Project with Maven"
                sh 'mvn clean'
            }
        }

        // Compilation du projet avec Maven
        stage('MVN COMPILE') {
            steps {
                echo "Compiling Project with Maven"
                sh 'mvn compile'
            }
        }

        // Analyse de la qualité du code avec SonarQube
        stage('MVN SONARQUBE') {
            steps {
                echo "Analyzing code with SonarQube"
                sh 'mvn sonar:sonar -Dsonar.projectKey=votre_projet_key -Dsonar.host.url=http://192.168.33.10:9000 -Dsonar.login=votre_token'
            }
        }
    }

    post {
        always {
            echo "Job Finished"
        }
    }
}
