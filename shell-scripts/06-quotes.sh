#!/bin/bash

# Quotes are used to disable the speciality of special character in the inputs provided to commands / scripts.
# Other than a-z, 0-9 and _ (Underscore), All are special characters..
# Single Quotes 
### In Single quotes there are no special characters 

# Double Quotes 
### Couple of characters are special varaibles 
### $ -> Access variables 
### ` -> Execute commands
echo "Today date is `date +%F`"

# I personally prefer to use double quotes
## Some times these special characters should be diabled in double quotes 

echo "Apple = \$100"

echo "Hai "" Hello"