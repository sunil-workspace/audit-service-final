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
    }
}