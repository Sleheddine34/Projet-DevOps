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
    failure {
        emailext (
            subject: "Jenkins Build Failed: ${currentBuild.fullDisplayName}",
            body: """
                <html>
                <body>
                    <table border="0" cellpadding="5" cellspacing="0" width="100%">
                        <tr>
                            <td bgcolor="#FF0000" align="center" style="color:white; font-size:20px; font-weight:bold;">
                                The Jenkins build has failed.
                            </td>
                        </tr>
                        <tr>
                            <td align="center" style="font-size:16px;">
                                Time Taken: ${currentBuild.durationString} and counting
                            </td>
                        </tr>
                    </table>
                </body>
                </html>
            """,
            to: "chouaibimohamed87@gmail.com, mohamed.chouaibi@esprit.tn"
        )
    }

    success {
        emailext (
            subject: "Jenkins Build Success: ${currentBuild.fullDisplayName}",
            body: """
                <html>
                <body>
                    <table border="0" cellpadding="5" cellspacing="0" width="100%">
                        <tr>
                            <td bgcolor="#00FF00" align="center" style="color:white; font-size:20px; font-weight:bold;">
                                The Jenkins build has succeeded.
                            </td>
                        </tr>
                        <tr>
                            <td align="center" style="font-size:16px;">
                                Time Taken: ${currentBuild.durationString} 
                            </td>
                        </tr>
                    </table>
                </body>
                </html>
            """,
            to: "chouaibimohamed87@gmail.com, mohamed.chouaibi@esprit.tn"
        )
    }
}

}
