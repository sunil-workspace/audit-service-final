pipeline {
    agent any

    stages {
        stage ('Compile Stage') {

            steps {
                withMaven(maven : 'maven_3_6_0') {
                    sh 'mvn clean compile'
                }
            }
        }
		stage ('Build Stage') {

            steps {
                withMaven(maven : 'maven_3_6_0') {
                    sh 'mvn install'
                }
            }
        }

		 stage ('Testing Stage') {

            steps {
                withMaven(maven : 'maven_3_6_0') {
                    sh 'mvn test'
                }
            }
        } 
		 stage('Build Image'){
               steps{
			   sh 'sudo docker build -t sunildocker2019/audit-docker:${BUILD_NUMBER} .'
               sh 'docker tag sunildocker2019/audit-docker:${BUILD_NUMBER} sunildocker2019/audit-docker:latest'
			   sh 'docker tag sunildocker2019/audit-docker:${BUILD_NUMBER} 012515449968.dkr.ecr.us-east-1.amazonaws.com/audit-repo:latest'
				
				}
          }
         stage('Push Image'){
		 steps{
                sh 'docker login -u sunildocker2019 -p Docker@2019'
				sh 'docker push sunildocker2019/audit-docker:${BUILD_NUMBER}'
                sh 'docker push sunildocker2019/audit-docker:latest'
				sh 'docker push 012515449968.dkr.ecr.us-east-1.amazonaws.com/audit-repo:latest'
				}	
   				
    }
	stage('Force Deploy') {
         steps{
                sh 'aws ecs update-service --region us-east-1 --cluster sunil-audit --service audit-service --force-new-deployment'
			}
     }
	 stage('Remove Image from Server') {
         steps{
                sh 'docker image rm sunildocker2019/audit-docker:latest'
			}
     }
  }
}