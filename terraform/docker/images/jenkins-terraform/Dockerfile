#rkalluru/b36-jenkins-terraform:v3
FROM rkalluru/b36-jnlp-slave:v1
USER root 
RUN yum install wget unzip git -y 
RUN curl -s https://raw.githubusercontent.com/linuxautomations/labautomation/master/tools/terraform/install.sh | bash 
RUN mkdir /home/centos 
RUN chown jenkins:jenkins /home/centos
USER jenkins
