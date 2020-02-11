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
- Collaboration could not have been any better. We both did equal amounts of work. 
- I wrote a majority of the new tests with close collaboration with Abraham, as he did most of the modification/addition to the methods in the starter code. 
- We both worked on the writing part, and we both revised every aspect of the project (testing, start code methods, etc). 



## Testing
Our tests consist of component testing. We test each rate strategy for correct, and we perform sanity checking on other values. 
No part of the program needed to be changed during testing. However, the test for the alternating rate strategy almost warranted a change so that testing could be more exact... the test works differently depending on what day it is. We didn't want to modify any method outside the tests specifically so the tests could work. All tests are manual tests. We worked together on the tests and the methods being tested together in lab. All tests were written after the implementation was completed.
