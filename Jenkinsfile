pipeline {

    // Instructs Jenkins to use the buildconductor docker image to run this pipeline
    
    agent {
        docker { 
            image 'greghodgkinson/jenkins-buildconductor-iib:edge' 
            label 'docker'
            args '-u root'
        }
        
    }

    stages {
    
        // Pull down source code from Git repo
        
       stage('Checkout Code'){
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'load']], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/sitaramaprasad-erothu/mongodbiibexample.git']]])
            }
          
       }   
       
       stage('Checkout .brokerFile'){
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'brokerFiles-load']], submoduleCfg: [], userRemoteConfigs: [[url: 'http://gitlab.prolifics.com/serothu/brokerfiles.git']]])
            }
          
       }  
       
       // Build BAR file from source code 123
       
        stage('Build BAR') {
            steps {
                sh "/buildconductor/iib/run-automation.sh build crypto-mongodb 'PRO_ENCRYPT_API PRO_DECRYPT_STORE_MONGODB'" 
            }
        }   
        
        // Either override and deploy the BAR (with or without broker file), or upload it to UCD so it can be deployed from there (one of the below steps should be uncommented)
        
        stage('Override and Deploy BAR') {
         steps {
                sh "/buildconductor/iib/run-automation.sh overrideAndDeploy crypto-mongodb na na na TEST iibuser iibuser 'PRO_ENCRYPT_API PRO_DECRYPT_STORE_MONGODB' overrides/development.properties ../brokerFiles-load/DEV/TEST.broker"
        
          }
        }    
    }
}
