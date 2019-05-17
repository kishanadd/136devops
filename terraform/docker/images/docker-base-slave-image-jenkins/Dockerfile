#rkalluru/b36-jnlp-slave:v1
FROM centos
RUN yum install java -y
ARG JENKINS_REMOTING_VERSION=3.27
RUN mkdir -p /usr/share/jenkins /opt/bin
RUN curl --create-dirs -sSLo /usr/share/jenkins/slave.jar https://repo.jenkins-ci.org/public/org/jenkins-ci/main/remoting/$JENKINS_REMOTING_VERSION/remoting-$JENKINS_REMOTING_VERSION.jar \
  && chmod 755 /usr/share/jenkins \
  && chmod 644 /usr/share/jenkins/slave.jar
COPY jenkins-slave /usr/local/bin/jenkins-slave
COPY entry_point.sh /opt/bin/entry_point.sh 
RUN chmod +x /opt/bin/entry_point.sh /usr/local/bin/jenkins-slave
RUN useradd jenkins
RUN chmod a+rwx /home/jenkins
WORKDIR /home/jenkins
USER jenkins

ENTRYPOINT ["/opt/bin/entry_point.sh", "/usr/local/bin/jenkins-slave"]