#!/bin/bash



echo Hai
#exit 1  
echo BYE

## exit command by default exits with 0, unless you specify some value

# we cannot use exit command in function , because it exists the script. So functions also have exit status. But exit command will completly exit the script .. So we have alternate 'return' command to exit function with exit status 

F() {
    echo Hai
    return 2
    echo BYE
}
F 
echo Function exit status = $?