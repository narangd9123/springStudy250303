pipeline {
    agent { docker { image 'gradle:7.4.2-jdk17-alpine' } }
    stages {
        stage('build') {
            steps {
                sh './gradlew --version'  // Gradle 버전 확인
                sh './gradlew build'      // 빌드 실행
            }
        }
    }
}
