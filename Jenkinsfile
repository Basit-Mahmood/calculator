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
    } 
} 
