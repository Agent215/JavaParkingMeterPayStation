# PayStation Rate Strategies and Main Program

## General Design

We chose the fourth option from the class slides. This utilizes an interface for rate strategy that the specific rate strategies implement.  There are constructors for the paystation to create them with a specific rate strategy, and also a setter method to change at run time. The main class has some wrapper methods for the payStations built in functionality that the main method then call.
### UML Diagram

![UML DIAGRAM](https://github.com/laserball32/02-McShane-Schultz-TeamEffort-/raw/staging/Option4%20(1).png)

## Requirements
This software should be able to Implement a simulation of a pay station for a parking meter. <br>
It should be able to handle the following cases: <br>
- accept 5, 10 and 25 cent coins for payment
- display should show correct time
- print receipt after purchase with correct information
- if transaction is canceled receipt should print correct amount of money returned and correct denominations 
- Should be able to switch between any one of the following three pay rate strategies : <br>
  1. Linear
  2. Progressive 
  3. Alternating 
  
The main method will simulate the display the end user would see. And allow the following actions to be taken any time during runTime: <br>
- Deposit Coins
- Display
- Buy Ticket
- Cancel
- Change Rate Strategy

## Team Work

### Abraham Schultz
- I noticed that provided starter code has some issues with its implementation of the empty method, and the add payment method. Also the tests for both those methods did not actually reflect the requirements. So i fixed them and updated the tests accordingly.
- I used Draw.io to create various iterations of the UML class diagram for the project. As well as creating the final UML diagram.
- Implemented the alternate rate strategy
- In collaboration with Dan I helped with the design of the Junit testing methods for the various rate strategies. 
- Created the setter method for the change rate strategy.
- Created the change rate strategy wrapper method for the main.
- Added a wrapper for the buy method to the main.
- Did some manual testing user input for main.

### Daniel Mcshane

## Testing
