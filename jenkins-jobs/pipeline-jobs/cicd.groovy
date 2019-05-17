def sonarUrl="http://35.235.116.247:9000"
def sonarToken="fcef9328937448b6466d72a63028fae143ac1284"
def nexusUrl="35.203.184.85"
node('maven') {
    
    stage('Clone Maven Code UI') {
        dir('MAVEN') {
        git credentialsId: 'dbe25c12-5564-4f14-b9fa-a5de080ec5c5', url: 'https://gitlab.com/batch36/mavenrepo.git'
    } }

    stage('Clone Terraform Code') {
        dir('TERRAFORM') {
        git credentialsId: 'dbe25c12-5564-4f14-b9fa-a5de080ec5c5', url: 'https://gitlab.com/batch36/terraform.git'
    } }

    stage('Clone Maven Code API') {
        dir('API') {
        git credentialsId: 'dbe25c12-5564-4f14-b9fa-a5de080ec5c5', url: 'https://gitlab.com/batch36/studentapp-api.git'
    } }



    stage('Compile-UI') {
        dir('MAVEN') {
        sh """
            mvn compile
        """
    } }

    stage('Code Quality Check-UI') {
        dir('MAVEN') {
        // Code quality check using SonarQube
        sh """
            true mvn sonar:sonar -Dsonar.host.url=${sonarUrl} -Dsonar.login=${sonarToken}
        """
    } }

    stage('Upload Artifacts-UI') {
        dir('MAVEN') {
        withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'NexusCred',
usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
        sh """
            mvn -s settings.xml deploy -DNEXUS_SERVER=${nexusUrl} -DNEXUS_USERNAME=${USERNAME} -DNEXUS_PASSWORD=${PASSWORD} -DID=deployment
        """
        }
        env.URL="http://172.31.17.51:8080/job/STUDENT-PROJ/job/CI-Student-App-Dev/$BUILD_ID/consoleText"
        env.WAR_URL=sh(script: 'curl -s $URL | grep Uploaded | grep war | grep studentapp | xargs -n 1 | grep ^http', returnStdout: true).trim()
    } }

    stage('Compile-API') {
        dir('API') {
        sh """
            mvn compile
        """
    } }

    stage('Code Quality Check-API') {
        dir('API') {
        // Code quality check using SonarQube
        sh """
            true mvn sonar:sonar -Dsonar.host.url=${sonarUrl} -Dsonar.login=${sonarToken}
        """
    } }

    stage('Packaging-API') {
        dir('API') {
        sh """
            mvn package
        """
    } }

    stage('Upload Artifacts-API') {
        dir('API') {
        withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'NexusCred',
usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
        sh """
            mvn -s settings.xml deploy -DNEXUS_SERVER=${nexusUrl} -DNEXUS_USERNAME=${USERNAME} -DNEXUS_PASSWORD=${PASSWORD} -DID=deployment
        """
        }
        env.URL="http://172.31.17.51:8080/job/STUDENT-PROJ/job/CI-Student-App-Dev/$BUILD_ID/consoleText"
        env.API_URL=sh(script: 'curl -s $URL | grep Uploaded | grep war | grep studentapi | xargs -n 1 | grep ^http', returnStdout: true).trim()
    } }
        // mvn -s settings.xml clean package deploy -DNEXUS_SERVER=18.220.69.69 -DNEXUS_USERNAME=admin -DNEXUS_PASSWORD=admin123 -DID=deployment


    stage('Create Test Env') {
        dir('TERRAFORM') {

            withCredentials(bindings: [sshUserPrivateKey(credentialsId: 'Centos-SSH-PEM', keyFileVariable: 'SSH_PRIVATE_KEY_CENTOS')]) {
                sh """
                    cat $SSH_PRIVATE_KEY_CENTOS >centos.pem
                    chmod 600 centos.pem
                """
            }

            withCredentials([string(credentialsId: 'DBPASSWORD', variable: 'TOKEN')]) {
                env.DBPASSWORD=sh(script: 'echo $TOKEN', returnStdout: true).trim()
            }

            withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'AWSCred',
usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
        wrap([$class: 'AnsiColorBuildWrapper', 'colorMapName': 'xterm']) {
            sh """
                export AWS_ACCESS_KEY_ID=${USERNAME}
                export AWS_SECRET_ACCESS_KEY=${PASSWORD}
                export AWS_DEFAULT_REGION="us-east-2"
                cd stack-1
                terraform init 
                terraform apply -auto-approve -var-file=dev.tfvars -var DBPASSWORD=${DBPASSWORD} -var WAR_URL=${WAR_URL} -var API_URL=${API_URL}
            """
            }
         }
        }

        env.ELBIP=sh(script: 'cat /tmp/ec2-elb', returnStdout: true).trim()
    }

    stage('UI Testing') {

        dir('API') {
        git credentialsId: 'dbe25c12-5564-4f14-b9fa-a5de080ec5c5', url: 'https://gitlab.com/batch36/selenium.git'
        sh """
            sed -i -e "s/IPADDRESS/${ELBIP}/"  src/test/java/framework/CrudStudent.java
            mvn clean install "-Dremote=true" "-DseleniumGridURL=http://35.234.134.41:4444/wd/hub"  "-Dbrowser=Chrome"  "-Doverwrite.binaries=true"
            cat target/surefire-reports/emailable-report.html
        """
        }
    }

    stage('API Testing') {

        dir('API') {
        git credentialsId: 'dbe25c12-5564-4f14-b9fa-a5de080ec5c5', url: 'https://gitlab.com/batch36/selenium.git'
        sh """
            python scripts/api-tests.py ${ELBIP}
        """
        }
    }
}