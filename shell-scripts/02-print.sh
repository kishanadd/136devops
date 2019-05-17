#!/bin/bash 

## Printing can be done with two commands 1. echo and 2. printf command. printf command has lot of syntaxes and where as echo command is pretty simple. SO we prefer to use echo command.
#

echo Hello

# Some cases we need to print multiple lines 
echo Hello 
echo Hai 
echo Hey 

# instead of using multiple echo commands, we can give that in single echo command using escape sequeneces.
# Following escape sequences are to be discussed 
# 1. New line (\n)
# 2. New tab (\t)
# 3. New color (\e)

## Using new line (\n)
echo Hai\nBye

## Note: When you use escape sequences always use double quotes 
echo "Hai\nBye"

## Note: TO enable escape sequences we have to use -e option in echo command.
echo -e "Hai\nBye"

## Using tab spaces 
echo -e "Hai\tBye"
### To have multiple tab spaces 
echo -e "Hai\t\t\tBye"

# Using COlor codes (\e)
# Syntax: \e[<COL>m 
# COlors are of two type, Foreground(Font), Background(Screen)
#   Color           Foreground          Background
# Red               31                  41
# Green             32                  42
# Yellow            33                  43
# Blue              34                  44
# Magenta           35                  45
# Cyan              36                  46

# WE can give custom background and foreground colors as well
# Syntax: \e[BG;FGmTEXT
echo -e "\e[33;41mYellow on Red"
echo Hello World 

## Colors get followed , When we enable color and it is our responsibilty to disable color as well. TO disable color we have color code zero (0)

echo -e "\e[35;42mMagenta on Green\e[0m"
echo Hello World 