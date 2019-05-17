#rkalluru/b36-app:v4
FROM tomcat 
ADD https://s3-us-west-2.amazonaws.com/studentapi-cit/student.war webapps/student.war 
ADD https://s3-us-west-2.amazonaws.com/studentapi-cit/mysql-connector.jar lib/mysql-connector.jar
COPY context.xml conf/context.xml 
RUN sed -i -e "/org.apache.catalina.webresources/ c JAVA_OPTS=\"\$JAVA_OPTS -Djava.protocol.handler.pkgs=org.apache.catalina.webresources -DDBENDPOINT=\${DBENDPOINT} -DDBNAME=\${DBNAME} -DDBUSER=\${DBUSER} -DDBPASS=\${DBPASS}\"" bin/catalina.sh
