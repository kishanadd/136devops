#!/bin/bash 

## Color Variables
R="\e[31m"
G="\e[32m"
Y="\e[33m"
M="\e[35m"
U="\e[4m"
N="\e[0m"

LOG=/tmp/stack.log 
rm -f $LOG 
## Check sudo access is given to script or not

ID=$(id -u)
if [ $ID -ne 0 ]; then 
    echo -e "$R Script should run as root user or sudo access ..$N "
    exit 2
fi

HEAD() {
    echo -e "\n\t\t$M $U $1 $N\n"
}

Print() {
    echo -n -e "   $1\t- "
}

Stat() {
    if [ "$1" = "IGNORE" ]; then 
        echo -e "$Y IGNORE $N"
        return
    fi
    if [ $1 -eq 0 ]; then 
        echo -e "$G SUCCESS $N"
    else 
        echo -e "$R FAILURE $N"
        echo "Refer file :: $LOG for error log"
        exit 1
    fi
}

APPUSER=student
APPHOME=/home/$APPUSER
TOMCAT_VER="8.5.38"
TOMCAT_URL="https://archive.apache.org/dist/tomcat/tomcat-8/v$TOMCAT_VER/bin/apache-tomcat-$TOMCAT_VER.tar.gz"
TOMCAT_DIR=$APPHOME/apache-tomcat-$TOMCAT_VER
JDBC_CONN='<Resource name="jdbc/TestDB" auth="Container" type="javax.sql.DataSource" maxTotal="100" maxIdle="30"  maxWaitMillis="10000" username="USERNAME" password="PASSWORD" driverClassName="com.mysql.jdbc.Driver" url="jdbc:mysql://RDS-DB-ENDPOINT:3306/DATABASE"/>'

## Main Program 
# ------------------------------------------------------------------
HEAD "INSTALL WEB SERVER"
Print "Install HTTPD"
yum install httpd -y &>>$LOG
Stat $?

Print "Update proxy config"
echo 'ProxyPass "/student" "http://localhost:8080/student"
ProxyPassReverse "/student"  "http://localhost:8080/student"' >/etc/httpd/conf.d/app-proxy.conf 
Stat $?

Print "Update Index file"
sudo curl -s https://s3-us-west-2.amazonaws.com/studentapi-cit/index.html -o /var/www/html/index.html &>>$LOG 
Stat $? 

Print "Start Web Service"
systemctl enable httpd &>>$LOG 
systemctl restart httpd &>>$LOG 
Stat $?

# ------------------------------------------------------------------

HEAD "INSTALL APP SERVER"
Print "Create APP User"
id $APPUSER &>/dev/null 
if [ $? -eq 0 ] ; then 
    Stat IGNORE
else 
    useradd $APPUSER &>>$LOG 
    Stat $?
fi  

Print "Install Java"
yum install java -y &>>$LOG 
Stat $?

Print "Download Tomcat"
cd $APPHOME
wget -qO- $TOMCAT_URL 2>>$LOG| tar -xz &>>$LOG
Stat $?

Print "Download Student App"
wget -q https://s3-us-west-2.amazonaws.com/studentapi-cit/student.war -O $TOMCAT_DIR/webapps/student.war &>>$LOG 
Stat $? 

Print "Download MySQL JDBC"
wget https://s3-us-west-2.amazonaws.com/studentapi-cit/mysql-connector.jar -O $TOMCAT_DIR/lib/mysql-connector.jar &>>$LOG 
Stat $? 

Print "Add JDBC Connection"
sed -i -e '/TestDB/ d' -e "$ i $JDBC_CONN" $TOMCAT_DIR/conf/context.xml
Stat $?

Print "Fix Permissions"
chown $APPUSER:$APPUSER $TOMCAT_DIR -R &>>$LOG 
Stat $?

Print "Setup Tomcat Init Script"
wget -q https://s3-us-west-2.amazonaws.com/studentapi-cit/tomcat-init -O /etc/init.d/tomcat
chmod +x /etc/init.d/tomcat
Stat $?

Print "Start Tomcat Service"
systemctl daemon-reload &>>$LOG
systemctl restart tomcat &>>$LOG
Stat $?

