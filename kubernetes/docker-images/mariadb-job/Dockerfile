#rkalluru/b36-dbjob:v2
FROM centos 
RUN yum install mariadb nc -y 
COPY entrypoint.sh /entrypoint.sh 
RUN chmod +x /entrypoint.sh 
ADD https://s3-us-west-2.amazonaws.com/studentapi-cit/studentapp-ui-proj1.sql /tmp/student.sql 
CMD ["/entrypoint.sh"]