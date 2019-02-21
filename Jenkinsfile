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
				
				}
          }
         stage('Push Image'){
		 steps{
                sh 'docker login -u sunildocker2019 -p Sunil@1105'
				sh 'docker push sunildocker2019/audit-docker:${BUILD_NUMBER}'
                sh 'docker push sunildocker2019/audit-docker:latest'
				}
			
   				
    }
}