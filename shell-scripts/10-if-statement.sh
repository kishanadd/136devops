#!/bin/bash

## 
Check() {
    if [ $1 -eq 0 ]; then 
        echo "$ACTION :: Success"
        exit 0
    else 
        echo "$ACTION :: Failure"
        echo "Refer log file :: $LOG for errors"
        exit 1 
    fi
}

####
ACTION=$1
PACKAGE_NAME=$2
LOG=/tmp/if.log
if [ -f $LOG ];then  
    rm -f $LOG
fi 

if [ -z "$ACTION" -o -z "$PACKAGE_NAME" ]; then 
  echo -e "Usage:: $0 ACTION(install|remove) PACKAGE_NAME"
  exit 2
fi 

if [ "$ACTION" == "install" ]; then 
    sudo yum install $PACKAGE_NAME -y &>>$LOG 
    Check $?
elif [ "$ACTION" == "remove" ]; then 
    sudo yum remove $PACKAGE_NAME -y &>>$LOG 
    Check $? 
fi 