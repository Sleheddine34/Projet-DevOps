pipeline {
    agent any

    tools {
        jdk 'JAVA_HOME'
        maven 'M2_HOME'
    }

    stages {
        // Récupération du code depuis GitHub
        stage('Checkout') { 
            steps {
                echo "Getting Project from Git"
                git url: 'https://github.com/Sleheddine34/Projet-DevOps.git', branch: 'joseph'
            }
        }

        // Nettoyage et compilation du projet avec Maven
        stage('Compile Stage') { 
            steps {
                echo "Cleaning Project with Maven"
                sh 'mvn clean compile'
            }
        }

        // Analyse de la qualité du code avec SonarQube
        stage('SonarQube Analysis') {
            steps {
                withCredentials([string(credentialsId: 'sonar-token', variable: 'SONAR_TOKEN')]) {
                    echo "Analyzing code with SonarQube"
                    sh "mvn sonar:sonar -Dsonar.projectKey=votre_projet_key -Dsonar.host.url=http://192.168.33.10:9000 -Dsonar.login=$SONAR_TOKEN"
                }
            }
        }

        // Déploiement (ajoutez ce stage si nécessaire)
        stage('Deploy to Nexus') {
            steps {
                sh 'mvn deploy -DskipTests -DaltDeploymentRepository=deploymentRepo::default::http://192.168.33.10:8081/repository/maven-releases/'
            }
        }
    }

    post {
        always {
            echo "Job Finished"
        }
    }
}
