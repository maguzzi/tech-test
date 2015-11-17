# tech-test
#Synopsis
This project performs the calculation of the details of a shopping cart. It takes as input a file containing one or more shopping carts, along with their entries, and produces as output the same shopping cart updated with price summed up with taxes, and prints the total of the applied taxes and the overall total.
Each entries contains the quantity, the import status, the good category, the description, and the unit price.
#Demonstration
To demonstrate the project, and end to end test has been developed (while the code coverage being up to 94%). The test takes the input file and store its content into the model, computes the prices with taxes for each single good, the total taxes, the overall total and marshals the updated model into an output file. An expected output file and the produced file are then read and compared line by line. The marshalling of the model allows also to test against correct number formatting for each shopping cart, each one with different locales.
#Architecture
The project leverages the factory + command pattern to select the appropriate calculation method for each shopping cart and each entry. Additional factories have been built to decouple the calculation methods (different taxes), the calculation process (how to compute single prices and rounding), and the tax chooser (different taxes have been put for different locales). 
