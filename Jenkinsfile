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
		// package application to JAR file
		stage('Package') {
            steps {
                bat 'gradlew build'
            }
        }
		// To push an image to a private registry and not the central Docker registry you must tag it with the registry hostname and port 
		// To tag a local image with name "calculator" into the private registry localhost:5000 use the -t flag
		stage('Docker build') {
            steps {
                bat 'docker build -t localhost:5000/calculator .'
            }
        }
		// Now, we can store the image in the registry using the push command
		// Note that there is no need to specify the registry address because Docker uses the naming convention to resolve it. 
		stage('Docker push') {
            steps {
                bat 'docker push localhost:5000/calculator'
            }
        }
        // After running this stage, the calculator container is running as a daemon, publishing its port as 8765, and being removed 
        // automatically when stopped.
        stage("Deploy to staging") {
			steps {
				bat 'docker run -d --rm -p 8765:8090 --name calculator localhost:5000/calculator'
			}
		}
		// Since the docker run -d command is asynchronous, we need to wait, using the sleep operation to make sure the service is 
		// already running.
		stage("Acceptance test ") {
			steps {
		    	sleep 60
				bat 'gradlew acceptanceTest -Dcalculator.url=http://localhost:8090/calculator'
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
			
			// Adding a cleanup stage environment:
			// ------------------------------------
			//
			// As the final stage of acceptance testing, we can add the staging environment cleanup. The best place to do this is 
			// in the post section, to make sure it executes even in case of failure. This statement makes sure that the calculator container
			// is no longer running on the Docker host.
			bat 'docker stop calculator'
		}
	}
} 
