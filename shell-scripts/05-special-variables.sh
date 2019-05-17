#!/bin/bash 

### Special variables are 0 to n , *, @ , # , $
echo $0  ## Name of the script 
echo $1  ## First argument parsed to the script 

echo $* ## All arguments parsed
echo $@ ## All arguments parsed

echo $# ## Number of arguments parsed

echo $$  ## PID of this script 