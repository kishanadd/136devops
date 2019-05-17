node('docker') {
    stage('Download Repo') {
        git credentialsId: 'dbe25c12-5564-4f14-b9fa-a5de080ec5c5', url: 'https://gitlab.com/batch36/mavenrepo.git'
    }

    stage('Compile and Package') {
        sh """
            mvn clean
            mvn versions:set -DnewVersion=${RELEASE_VERSION}
            mvn compile package
        """
    }

    stage('Make Docker Image') {
        sh """
            cp target/student*.war docker/student.war 
            cd docker 
            docker build -t rkalluru/b36-app:v${RELEASE_VERSION} .
            docker push rkalluru/b36-app:v${RELEASE_VERSION}
        """
    }

    // stage('Rolling Update') {
    //     sh '''
    //         kubectl set image deployment/app tomcat=rkalluru/b36-app:v${RELEASE_VERSION} -n appui
    //     '''
    // }

    stage('Blue-Green Deployment') {
        sh '''
            sed -i -e "s/VERSION/${RELEASE_VERSION}/" kubernetes/deployment.yml
            kubectl apply -f kubernetes/deployment.yml
        '''
    }
}