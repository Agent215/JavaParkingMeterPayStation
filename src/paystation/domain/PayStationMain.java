/*
A main() program should be developed to simulate the PayStation operation. 
It puts up a menu to allow a customer to select a choice:
Deposit Coins
Display
Buy Ticket
Cancel
Change Rate Strategy
 */
package paystation.domain;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.Optional;
public class PayStationMain {

    static Scanner input = new Scanner(System.in);
    static PayStation ps;
    static RateStrategy rs;
    
    
    public static void cancelTransaction() {
    	 Map<Integer, Integer> returnedCoins = ps.cancel();
    	 String message = "Returned coins: ";
    	 if (returnedCoins.get(5) != null)
    		 message += returnedCoins.get(5) + " nickels. ";
    	 if (returnedCoins.get(10) != null)
    		 message += returnedCoins.get(10) + " dimes. ";
    	 if (returnedCoins.get(25) != null)
    		 message += returnedCoins.get(25) + " quarters. ";
    		 
    	 System.out.println(message);
    }
    
    
    
    

//**********************************************************************************************************************
//**********************************************************************************************************************
    public static void depositCoins() throws IllegalCoinException {

        // check if we have yet to create a paystation rate strategy this should 
        // be called no more then once ( really not at all) we can remove once we are sure
        if (ps == null) {
            System.out.println("A rate strategy has not been chosen.");
            System.out.println("Please choose from the following rate strategies: A for alternating, P for progressive, and L for linear.");
            input.nextLine();
            String rateStrategy = input.nextLine();

            if (rateStrategy.equalsIgnoreCase("A")) {
                ps = new PayStationImpl(new AlternatingRateStrategy());
            } else if (rateStrategy.equalsIgnoreCase("P")) {
                ps = new PayStationImpl(new ProgressiveRateStrategy());
            } else if (rateStrategy.equalsIgnoreCase("L")) {
                ps = new PayStationImpl();
            } else {
                System.err.println("Invalid rate strategy. Please try again.");
                return;
            }
        }

        int coin = -1;
        while (coin != 0) {
            System.out.println("Type in the amount you'd like to deposit or 0 to exit. (5, 10, or 25): ");
            try {
            if (input.hasNextInt())
            	coin = input.nextInt();
            else {
            	input.next();
            	throw new IllegalCoinException("Not valid input.");
            }
            if (coin == 0)
                return;

                ps.addPayment(coin);
                System.out.println("Coin accepted.");
            } catch (IllegalCoinException e) {
                System.out.println(e.getMessage());
            }

        }

    } // end depositCoins
//**********************************************************************************************************************
//**********************************************************************************************************************

    public static void changeRs() {

        // prompt user
        System.out.println("Please choose from the following rate strategies: A for alternating, P for progressive, and L for linear.");
        input.nextLine();
        String rateStrategy = input.nextLine();
        if (ps == null) 
        	ps = new PayStationImpl();
        if (rateStrategy.equalsIgnoreCase("A")) {
            rs = new AlternatingRateStrategy();
            ps.setPayStrat(rs);
            System.out.println("Using Alternating Rate Stategy");
        } else if (rateStrategy.equalsIgnoreCase("P")) {
            rs = new ProgressiveRateStrategy();
            ps.setPayStrat(rs);
            System.out.println("Using Progressive Rate Strategy");
        } else if (rateStrategy.equalsIgnoreCase("L")) {
            rs = new LinearRateStrategy();
            ps.setPayStrat(rs);
            System.out.println("Using Linear Rate Strategy");
        } else {
            System.err.println("Invalid rate strategy. Please try again.");

        }

    } // end changeRs
//**********************************************************************************************************************
//**********************************************************************************************************************

    public static void main(String[] args) throws IllegalCoinException {

        int timeInMachine = 0;
        int userChoice = 0;
        // running flag
        boolean running;
        // we start in running state
        running = true;
        // main while loop for simulation
        while (running == true) {

            // make sure input buffer is reset
            userChoice = 0;
            // prompt user
            
            
            // this makes all red error messages appear on top of the console input.
            try {
				Thread.sleep(50);
			} catch (InterruptedException e1) {
				
			}
            
            
            System.out.println("welcome to the parking meter pay station");
            System.out.println("1 : deposit coins \n2 : Display\n3 : buy ticket\n4"
                    + " : cancel\n5 : choose rate strategy\n"
                    + "6 : Exit Simulation\n"
                    + "please make a selction by typing a number");

            // check if user entered an int
            try {
                userChoice = input.nextInt();
            } catch (InputMismatchException e) {
            	//skip bad input
            	input.next();
            }

            switch (userChoice) {
                case 1:
                    depositCoins();
                    break;
                case 2:
                    // code for display
                	if (ps != null) {
                		timeInMachine = ps.readDisplay();
                		System.out.printf("Time Left : %d %n", timeInMachine);
                		// we should add somthing here to maybe return a recipt or say what coins 
                		// were returned
                	}
                	else
                		System.err.println("No rate strategy was chosen.");
                    break;
                case 3:
                    // code to buy ticket
                    
                    break;
                case 4:
                    // code to cancel transaction
                	cancelTransaction();
                    break;
                case 5:
                    // code to change rate strategy
                    changeRs();
                    break;
                case 6:
                    System.out.printf("Exiting..");
                    return;

                default:
                    System.err.println("you entered an invalid choice option");
            }

        } // end while

    } // end main

} // endPayStationMain
