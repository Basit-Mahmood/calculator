pipeline { 
    agent any
	// Automatically triggers the build in every minute. pollSCM() check the github frequently. If there are any commit(s). It will trigger the Jenkins build automatically
	//triggers {
        //pollSCM('* * * * *')
    //}
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
		// This stage copies the generated checkstyle report to the Jenkins output.
		stage('Static code analysis') { 
			steps { 
				bat 'gradlew checkstyleMain'
				publishHTML (target: [
					reportDir: 'build/reports/checkstyle/',
					reportFiles: 'main.html',
					reportName: "Checkstyle Report"
				])
            } 
        }
    }
	// All notifications are usually called in the post section of the pipeline, which is executed after all steps, no matter whether the build succeeded 
	// or failed. We used the always keyword; however, there are different options (always, changed, fixed etc etc)
	post {
		always {
			mail to: 'basit.mahmood2@gmail.com',
			subject: "Completed Pipeline: ${currentBuild.fullDisplayName}",
			body: "Your build completed, please check: ${env.BUILD_URL}"
		}
	}
} 
