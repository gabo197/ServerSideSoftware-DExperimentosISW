pipeline {
     agent any
     tools {
         maven 'MAVEN_3_8_1'
         jdk 'JDK_11'
     }

     stages {
         stage ('Compile Stage') {
             steps {
                 withMaven(maven : 'MAVEN_3_8_1') {
                 bat 'mvn clean compile'
                 }
             }
         }
         stage ('Testing Stage') {
             steps {
                 withMaven(maven : 'MAVEN_3_8_1') {
                 bat 'mvn test'
                 }
             }
         }
         stage ('package Stage') {
             steps {
                 withMaven(maven : 'MAVEN_3_8_1') {
                 bat 'mvn package'
                 }
             }
         }
     }
}