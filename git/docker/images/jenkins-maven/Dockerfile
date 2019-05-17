#rkalluru/b36-jenkins-maven:v3
FROM rkalluru/b36-jnlp-slave:v1
USER root 
RUN yum install git maven wget unzip mariadb -y 
RUN curl -s https://raw.githubusercontent.com/linuxautomations/labautomation/master/tools/terraform/install.sh | bash 
USER jenkins
