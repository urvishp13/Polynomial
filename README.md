# Polynomial

This project is solely written in Java. Please run it using Java 8.

## Overview

This project was an assignment given to Rutgers CS112 Data Structures students in the Fall of 2014. It was redone in August 2021 with no recollection
of how the project was initially completed. The focus of this project is to perform arithmetic operations (specifically addition
and multiplication) and point evaluation using polynomials; the polynomials are generated using Linked Lists.

## Code & Test Cases

The code written can be found in the [src](https://github.com/urvishp13/Polynomial/tree/main/src/poly) folder. Look for the 
`@author Urvish Patel` tag before Classes to see the code I have written.
The code was tested using self-generated test cases that can be found in [this](https://github.com/urvishp13/Polynomial/blob/main/docs/testcases.md) 
file. If you want to generate your own polynomials to test the code, simply create a text file and follow the the instructions on 
how to format the content of your polynomial under the "Running the program" section in [problem_specs](https://github.com/urvishp13/Polynomial/blob/main/docs/problem_specs.pdf) 
file. The files for the polynomials I used are in the [data](https://github.com/urvishp13/Polynomial/tree/main/data) folder. 

## How to Test

To test the program, access the contents of this repository as appropriate for you and follow the instructions presented to you in the 
(eg. command line, console in Eclipse, etc.). 

If running the program using the command line, go into the `bin` 
folder of this repo and type `java poly.Polytest`--as the .class files are already there.

To access the polynomial files (.txt files) when running the program, you will have to backout/follow-directory-paths until you are 
in the directory of that file (eg. in the command line, you'll have to use `../data/<file_name>.txt`; in Eclipse, you'll have to use 
`data/<file_name>.txt`, etc.).