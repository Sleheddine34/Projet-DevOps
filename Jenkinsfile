pipeline {
    agent any
 environment {
        SMTP_HOST = 'smtp.gmail.com'
        SMTP_PORT = '465'
        SMTP_USERNAME = 'chouaibimohamed87@gmail.com'  // replace with your Gmail address
        SMTP_PASSWORD = 'tyid nmej ndqy gmpf'    // replace with your Gmail App password
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

    //    stage('JUnit/Mockito Tests') {
      //      steps {
        //        sh 'mvn test' 
          //  }
       // }

  //      stage('SonarQube Analysis') {
    //        steps {
      //          withCredentials([string(credentialsId: 'sq1', variable: 'SONAR_TOKEN')]) {
        //            sh "mvn sonar:sonar -Dsonar.projectKey=JenkinsFile -Dsonar.host.url=http://192.168.33.10:9000 -Dsonar.login=$SONAR_TOKEN"
          //      }
            //}
       // }

      //  stage('Deploy to Nexus') {
        //    steps {
          //      sh 'mvn deploy -DskipTests -DaltDeploymentRepository=deploymentRepo::default::http://192.168.33.10:8082/repository/maven-releases/'
           // }
       // }

      //  stage('Build') {
        //    steps {
          //      sh 'mvn clean package'
            //    sh 'ls target'
           // }
       // }

     //   stage('Build Docker Image') {
       //     steps {  
         //       sh "docker build -t mohamed855/my-alpine:latest ."
           // }
       // }

      //  stage('Push Docker Image') {
        //    steps {
          //      script {
            //        withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials-id', passwordVariable: 'DOCKER_PASSWORD', usernameVariable: 'DOCKER_USERNAME')]) {
              //          sh "echo \$DOCKER_PASSWORD | docker login -u \$DOCKER_USERNAME --password-stdin"
                //    }
                  //  sh 'docker push mohamed855/my-alpine:latest'
               // }
           // }
       // }

    //    stage('Deploy with Docker Compose') {
      //      steps {
        //        script {
          //          sh 'ls -la'
            //        sh 'docker compose -f ./docker-compose.yml up -d'
              //  }
          //  }
       // }

        stage('Email Notification') {
            steps {
                mail bcc: '', 
                     body: '''
Final Report: The pipeline has completed successfully. No action required.
''', 
                     cc: '', 
                     from: '', 
                     replyTo: '', 
                     subject: 'Succès de la pipeline DevOps Project', 
                     to: 'chouaibimohamed87@gmail.com, mohamed.chouaibi@esprit.tn'
            }
        }
    }

      post {
        success {
            emailext (
                subject: 'Build Successful',
                body: 'The Jenkins build was successful.',
                to: 'chouaibimohamed87@gmail.com',
                smtpHost: env.SMTP_HOST,
                smtpPort: env.SMTP_PORT,
                smtpUser: env.SMTP_USERNAME,
                smtpPassword: env.SMTP_PASSWORD,
                ssl: true
            )
        }
        failure {
            emailext (
                subject: 'Build Failed',
                body: 'The Jenkins build has failed.',
                to: 'chouaibimohamed87@gmail.com',
                smtpHost: env.SMTP_HOST,
                smtpPort: env.SMTP_PORT,
                smtpUser: env.SMTP_USERNAME,
                smtpPassword: env.SMTP_PASSWORD,
                ssl: true
            )
        }
        always {
            emailext (
                subject: 'Build Status',
                body: 'The Jenkins build has finished.',
                to: 'chouaibimohamed87@gmail.com',
                smtpHost: env.SMTP_HOST,
                smtpPort: env.SMTP_PORT,
                smtpUser: env.SMTP_USERNAME,
                smtpPassword: env.SMTP_PASSWORD,
                ssl: true
            )
        }
    }
  
}
