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
                bat 'mvn clean compile'
            }
        }
        
        stage('Run Unit Tests') {
            steps {
                bat 'mvn test'
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
                bat 'mvn package'
            }
        }
        
        stage('Build Docker Image') {
            steps {
                script {
                    bat "docker build -t ${DOCKER_IMAGE}:${DOCKER_TAG} ."
                    bat "docker tag ${DOCKER_IMAGE}:${DOCKER_TAG} ${DOCKER_IMAGE}:latest"
                }
            }
        }
        
        stage('Push to Docker Hub') {
            steps {
                script {
                    withCredentials([usernamePassword(
                        credentialsId: 'docker-hub-credentials',
                        usernameVariable: 'DOCKER_USER',
                        passwordVariable: 'DOCKER_PASS'
                    )]) {
                        bat 'echo %DOCKER_PASS% | docker login -u %DOCKER_USER% --password-stdin'
                        bat "docker push ${DOCKER_IMAGE}:${DOCKER_TAG}"
                        bat "docker push ${DOCKER_IMAGE}:latest"
                    }
                }
            }
        }
        
        stage('Deploy Container') {
            steps {
                bat '''
                    docker stop fibonacci-container 2>nul || ver>nul
                    docker rm fibonacci-container 2>nul || ver>nul
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