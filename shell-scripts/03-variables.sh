#!/bin/bash

# Variables are used to declare content one time and can be used multiple times.
# Syntax : VAR=DATA
# Notes:
# 1. No data types in bash shell by default 
# 2. DATA is always considered as a string.

NAME=Raghu
COURSE=DevOps

## You can access a variable by preceeding $ character before VARIABLE NAME 
# TO access NAME variable we use $NAME

echo $NAME 

# Notes:
# 1. Variable names should have only a-z,A-Z and 0-9 and underscore(_)
# 2. Variable should not start with number

## Command substution

# Example with out command susbtution 
echo Today date is 2019-03-16 

# To declare variable by executing a command 
# VAR=$(COMMAND)

DATE=$(date +%F)
echo Today date is $DATE

echo "Topic Name = $TOPIC"
# On command line you have to declare variable as environment variable 
# export TOPIC=Shell