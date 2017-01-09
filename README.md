# Advanced Object Oriented Development Project

## Ed Lasauskas
## 4th Year
## College: Galway-Mayo Institute of Technology
## Module: Advanced Object Oriented Development
## Lecturer: Dr. John Healy

### Introduction
This is a java project for advanced OO development and the purpose of this project is to learn about and do some work using ***reflection***, ***swing tables*** and ***metrics*** in java. Reflection allows us to analyse java classes and check for things like constructors & their parameters, methods, method parameters and return types, interfaces, fields... of the class. 
This project reads in a jar file and parses through its contents(classes). Once the information is parsed some computation using reflection is done on all of the classes to calculate their Afferent (incoming) couplings & Efferent (outgoing) couplings with other classes. Using the computed coupling numbers. 
We can then calculate a stability metric for each class using the formula: **Class stability = Efferent / (Afferent + Efferent)**
Jtable is used then to output a information about each class in a tabular format.

### Structure of Repository
1. The src folder for java source code
2. Uml diagram of the classes in this project 
3. Jar file being analysed(string-service)
4. Servlet-api jar(which is to be included in project build path to fix errors of http and server stuff missing, which are used in string-service jar)
5. Java docs folder.

### Runing this project
1. Download this repo
2. Create a java project in some IDE
3. Add java source code
4. Add a jar file that you want to analyse to the project solution
5. Add external jars to build path if needed
7. In runner class type in the name of the full file eg. "string-serive.jar" to pass the file to MetricImpl object. 
