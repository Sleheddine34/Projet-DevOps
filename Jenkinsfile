pipeline {
    agent any
    environment {
        SMTP_USERNAME = 'chouaibimohamed87@gmail.com'  // replace with your Gmail address
        SMTP_PASSWORD = 'bzax aqyg viby gkai'    // replace with your Gmail App password
    }
    tools {
        jdk 'JAVA_HOME'
        maven 'M2_HOME'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/Mohamed_Chouaibi']], 
                          userRemoteConfigs: [[url: 'https://github.com/Sleheddine34/Projet-DevOps.git']]])
            }
        }
        
        stage('Compile Stage') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Email Notification') {
            steps {
                emailext(
                    subject: 'DevOps Pipeline Report',
                    body: 'The pipeline has completed successfully. No action required.',
                    to: 'chouaibimohamed87@gmail.com, mohamed.chouaibi@esprit.tn'
                )
            }
        }
    }

    post {
        success {
            emailext (
                subject: 'Build Successful',
                body: 'The Jenkins build was successful.',
                to: 'chouaibimohamed87@gmail.com, mohamed.chouaibi@esprit.tn'
            )
        }
        failure {
            emailext (
                subject: 'Build Failed',
                body: 'The Jenkins build has failed.',
                to: 'chouaibimohamed87@gmail.com, mohamed.chouaibi@esprit.tn'
            )
        }
        always {
            emailext (
                subject: 'Build Status',
                body: 'The Jenkins build has finished.',
                to: 'chouaibimohamed87@gmail.com, mohamed.chouaibi@esprit.tn'
            )
        }
    }
}
