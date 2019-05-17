#!/bin/bash

ADD() {
    echo $a + $b | bc
}

a=$1
b=$2
ADD 

## Declare variable in main program and you can access them in function.

B() {
    b=20
}

B 
echo B= $b

## Declare variables in function and you can access them in main program.

C() {
  a=30
}

a=20
C 
echo a in main program = $a 

## Variables can be overriden in function which are declared in main program and vice-versa

# Special variable of function are different with main program, Function has its own special variables which are parsed to function

D() {
    echo First Argument in function = $1 
    # Only $1-$n and $*, $@, $# are special variables for function 
    ## But $0 and $$ will not change inside a fucntion
}

echo First Argument in main program = $1
D abc