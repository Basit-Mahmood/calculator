pipeline { 
    agent any 
    stages { 
        stage('Compile') { 
            steps { 
                // Compile Java Code using gradle
                bat 'gradlew compileJava'
            } 
        } 
        stage('Unit test') { 
            steps { 
                // Run Unit Tests
                bat 'gradlew test' 
            } 
        } 
		// This stage copies the generated JaCoCo report to the Jenkins output.
		stage('Code coverage') { 
            steps { 
                bat 'gradlew jacocoTestReport'
				publishHTML (target: [
					reportDir: 'build/reports/jacoco/test/html',
					reportFiles: 'index.html',
					reportName: "JaCoCo Report"
				])
                bat 'gradlew jacocoTestCoverageVerification' 
            } 
        } 
    } 
} 
