pipeline {
    agent any
    
    environment {
        DOCKER_IMAGE = 'abiola101/fibonacci-app'
        DOCKER_TAG = "${BUILD_NUMBER}"
    }
    
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', 
                    url: 'https://github.com/abiol-a/fibonacci-app.git'
            }
        }
        
        stage('Build with Maven') {
            steps {
                sh 'mvn clean compile'
            }
        }
        
        stage('Run Unit Tests') {
            steps {
                sh 'mvn test'
            }
            post {
                success {
                    echo 'All of the tests have passed!'
                }
                failure {
                    echo 'The Tests have failed!'
                    error('The pipeline stopped due to the test failures')
                }
            }
        }
        
        stage('Package Application') {
            steps {
                sh 'mvn package'
            }
        }
        
        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("${DOCKER_IMAGE}:${DOCKER_TAG}")
                    docker.build("${DOCKER_IMAGE}:latest")
                }
            }
        }
        
        stage('Push to Docker Hub') {
            steps {
                script {
                    docker.withRegistry('', 'docker-hub-credentials') {
                        docker.image("${DOCKER_IMAGE}:${DOCKER_TAG}").push()
                        docker.image("${DOCKER_IMAGE}:latest").push()
                    }
                }
            }
        }
        
        stage('Deploy Container') {
            steps {
                sh '''
                    docker stop fibonacci-container || true
                    docker rm fibonacci-container || true
                    docker run -d -p 8081:8080 --name fibonacci-container abiola101/fibonacci-app:latest
                '''
            }
        }
    }
    
    post {
        always {
            junit 'target/surefire-reports/*.xml'
            echo 'The pipeline completed!'
        }
        success {
            echo 'The Pipeline has succeeded!'
        }
        failure {
            echo 'The Pipeline has failed. Please check your logs.'
        }
    }
}