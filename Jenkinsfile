pipeline {
    agent any
    triggers {
        pollSCM('* * * * *')
    }
    stages {
        stage('Compile') {
            steps {
                ./gradlew.bat compileJava
            }
        }
        stage('Unit Test') {
            steps {
                ./gradlew.bat test
            }
        }
        stage('Code coverage') {
            steps {
                ./gradlew.bat jacocoTestReport"
                    publishHTML (target: [
                    reportDir: 'build/reports/jacoco/test/html',
                        reportFiles: 'index.html',
                        reportName: 'JacocoReport'
                    ])
                        ./gradlew.bat jacocoTestCoverageVerification"
            }
        }
        stage('Sonarqube Analysis') {
            steps {
                withSonarQubeEnv('SonarQubePruebas') {
                    ./gradlew.bat sonarqube'
                }
            }
        }
    }
}
