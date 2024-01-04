pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'Javahome'
    }
    environment {
        // Define the Java home directory
        JAVA_HOME = 'C:\Program Files\Java\jdk-1.8'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'master', url: 'https://github.com/adilfettach/craftStore-With-spring-core-testing'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }
    }

    post {
        success {
            echo 'Build succeeded!'
        }

        failure {
            echo 'Build failed!'
        }
    }
}


