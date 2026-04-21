pipeline {
    agent any

    environment {
        IMAGE_NAME = "employee-api"
        DOCKER_USER = "your_dockerhub_username"
    }

    stages {

        stage('Checkout Code') {
            steps {
                git branch: 'main',
                url: 'https://github.com/YOUR_USERNAME/employee-api.git'
            }
        }

        stage('Build Maven') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t $IMAGE_NAME .'
            }
        }

        stage('Run Container (Test)') {
            steps {
                sh 'docker run -d -p 9231:9231 $IMAGE_NAME'
            }
        }

        stage('Push to Docker Hub') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-creds',
                    usernameVariable: 'USER',
                    passwordVariable: 'PASS')]) {

                    sh '''
                    echo $PASS | docker login -u $USER --password-stdin
                    docker tag employee-api $USER/employee-api:latest
                    docker push $USER/employee-api:latest
                    '''
                }
            }
        }
    }
}