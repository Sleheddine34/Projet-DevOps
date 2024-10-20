pipeline {
    agent any
    environment {
        // Replace with your actual SonarQube server name configured in Jenkins
        SONARQUBE_SERVER = 'SonarQubeServer'
    }
    stages {
        stage('Build') {
            steps {
                script {
                    // Clean and build the project
                    sh 'mvn clean package -DskipTests'
                }
            }
        }
        stage('SonarQube Analysis') {
            steps {
                // Use SonarQube environment to run the analysis
                withSonarQubeEnv('SonarQubeServer') {
                    sh '''
                        mvn sonar:sonar \
                        -Dsonar.projectKey=gestion-station-ski \
                        -Dsonar.host.url=http://192.168.1.22:9000 \
                        -Dsonar.login=squ_11a1c80c4224ea09db96778205183377f350cf36 \
                        -Dsonar.maven.plugin.version=3.9.1.2184
                    '''
                }
            }
        }
    }
    post {
        always {
            cleanWs()
        }
    }
}