#rkalluru/b36-web:v2
FROM centos 
RUN yum install httpd -y 
ADD https://s3-us-west-2.amazonaws.com/studentapi-cit/index.html /var/www/html/index.html 
RUN chmod 644 /var/www/html/index.html 
ADD proxy.conf /etc/httpd/conf.d/proxy.conf
CMD httpd -DFOREGROUND