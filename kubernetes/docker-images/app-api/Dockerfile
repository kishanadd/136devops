#rkalluru/b36-app-api:v1
FROM centos 
RUN yum install java -y 
ADD https://s3-us-west-2.amazonaws.com/studentapi-cit/studentapi.war /studentapi.war  
CMD java -jar /studentapi.war  