pipeline {
    agent any
    triggers {
        pollSCM('* * * * *')
    }
    stages {
        stage('Compile') {
            steps {
                sh "./gradlew bootJar"
            }
        }
        stage('Unit Test') {
            steps {
                sh "./gradlew test"
            }
        }
        stage('Code coverage') {
            steps {
                sh "./gradlew jacocoTestReport"
                    publishHTML (target: [
                    reportDir: 'build/reports/jacoco/test/html',
                        reportFiles: 'index.html',
                        reportName: 'JacocoReport'
                    ])
                        sh "./gradlew jacocoTestCoverageVerification"
            }
        }
        stage('Sonarqube Analysis') {
            steps {
                withSonarQubeEnv('SonarQubePruebas') {
                    sh './gradlew sonarqube'
                }
            }
        }
    }
}
