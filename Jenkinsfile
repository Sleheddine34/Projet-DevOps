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
                script {
                    // Calculate pipeline duration
                    def duration = currentBuild.durationString
                    // Send notification
                    emailext(
                        subject: "DevOps Pipeline Report",
                        body: """
                            The pipeline has completed successfully. No action required.
                            Time Taken: ${duration}
                        """,
                        to: 'chouaibimohamed87@gmail.com, mohamed.chouaibi@esprit.tn'
                    )
                }
            }
        }
    }

    post {
        success {
            script {
                def duration = currentBuild.durationString
                emailext(
                    subject: "Build Successful - Green Banner",
                    body: """
                        <h3 style="color:green;">The Jenkins build was successful.</h3>
                        <p>Time Taken: ${duration}</p>
                    """,
                    to: 'chouaibimohamed87@gmail.com, mohamed.chouaibi@esprit.tn'
                )
            }
        }
        failure {
            script {
                def duration = currentBuild.durationString
                emailext(
                    subject: "Build Failed - Red Banner",
                    body: """
                        <h3 style="color:red;">The Jenkins build has failed.</h3>
                        <p>Time Taken: ${duration}</p>
                    """,
                    to: 'chouaibimohamed87@gmail.com, mohamed.chouaibi@esprit.tn'
                )
            }
        }
        always {
            script {
                def duration = currentBuild.durationString
                emailext(
                    subject: "Build Status - Completed",
                    body: """
                        The Jenkins build has finished.
                        <p>Time Taken: ${duration}</p>
                    """,
                    to: 'chouaibimohamed87@gmail.com, mohamed.chouaibi@esprit.tn'
                )
            }
        }
    }
}
