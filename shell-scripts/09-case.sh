#!/bin/bash

ACTION=$1
PACKAGE_NAME=$2 
LOG=/tmp/case.log 
rm -f $LOG 

case $ACTION in 
    install) 
        sudo yum install $PACKAGE_NAME -y  &>>$LOG
        STATUS=$?
        ;; 
    remove) 
        sudo yum remove $PACKAGE_NAME -y  &>>$LOG
        STATUS=$? 
        ;;
esac
    
case $STATUS in 
    0) 
        echo "$ACTION SUCCESS"
        exit 0 
        ;;
    1) 
        echo "$ACTION FAIILURE"
        echo "Refer log file :: $LOG for errors"
        exit 1 
        ;;
esac