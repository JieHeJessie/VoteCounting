
# VoteCounting_CodeChallenge

This project is used to implement the vote counting. 

## Assumptions

1. If the user does not enter a file path, the default file will be called. The test.txt file includes four candidates: Winery tour, Ten pin bowling, Movie night, and Museum visit.

2. The number of candidates up to 26, which means candidates only are sorted by A to Z.

3. If the user input is incorrect, the ballot become invalid. 

4. No result:

For example: two candidate A and B, one user input “A”, another user input “B”, it cannot calculate the winner. At this case, the system will print “A and B have the same votes”.

## Supported Platforms
The project can be run in MacOS, Windows or Linux 

## Pre-requisites
In order to easily setup and manage all the development dependencies, setting up the Java, Maven.

## Startup running the application

Run the following command to startup the application
```
git clone https://github.com/JieHeJessie/VoteCounting.git
```
## IntelliJ IDEA Instructions

Open the project 

```
cd VoteCounting/src/aconex/jessie/Main.java
```
Right-click on project
Run 'Main' (it will run with default txt file)

OR

Run Configuration and input file path in program arguments


## Testing of the application

This project used Junit to test

```
cd VoteCounting/Test
```
Right-click on Run

## Coverage of testing

```
cd VoteCounting/Test
```
Right-click on Run All 'Tests' with coverage

Output: 100% (all logic store in domain file)
