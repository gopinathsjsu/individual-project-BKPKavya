# CMPE 202 Individual Project

## Name: Kusuma Padma Kavya Bandi
## SJSU ID: 016021785

## Project Description
The goal of this project is to develop a Java application that should maintain an internal, static database. This means once we re-run the program, the changes to the data would not persist. This application should allow the user to purchase inventory items, where the user will be restricted on the quantities based on the item category. When the user input requirement is satisfied, a bill amount should be calculated else an error message including the items with incorrect quantities is to be displayed.

## Instructions
Requirements: IntelliJ IDE, Maven

* Go to repo BKPKavya and clone the repository or download the zip file.
* Open the zipped folder or the entire folder in IntelliJ by navigating to File -> Open.
* After opening the project go to Billing.java which is inside src -> main -> java -> inventory_management and run the project.
* If the java version is not compatible please download the correct version (Java 18 SDK).
* Enter the input file.
* Once the run is successful, you can check the output in Output.csv and if an error occurred you can check it in Error.txt.


## Design Patterns 

### Builder Pattern

Builder Pattern says that construct a complex object from simple objects using step-by-step approach. The process of constructing an object should be generic so that it can be used to create different representations of the same object.

### Chain of Responsibility Pattern

Chain of Responsibility is a behavioral design pattern that lets you pass requests along a chain of handlers. Upon receiving a request, each handler decides either to process the request or to pass it to the next handler in the chain.

[[https://github.com/gopinathsjsu/individual-project-BKPKavya/blob/main/images/Chain%20of%20Responsibility%20Pattern.jpg]]
