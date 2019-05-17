#!/bin/bash -x

while true; do 
    nc mariadb-service 3306 &>/dev/null 
    if [ $? -eq 0 ]; then 
        break 
    fi 
    sleep 5
done

cat /tmp/student.sql

mysql -h mariadb-service -u root -proot </tmp/student.sql