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

import java.util.Scanner;

public class PayStationMain {
	static Scanner input = new Scanner(System.in);
	static PayStation ps;
	public static void depositCoins() throws IllegalCoinException {
		if (ps == null) {
			System.out.println("A rate strategy has not been chosen.");
			System.out.println("Please choose from the following rate strategies: A for alternating, P for progressive, and L for linear.");
			input.nextLine();
			String rateStrategy = input.nextLine();
			
			if (rateStrategy.equalsIgnoreCase("A")) 
				ps = new PayStationImpl(new AlternatingRateStrategy());
			else if (rateStrategy.equalsIgnoreCase("P"))
				ps = new PayStationImpl(new ProgressiveRateStrategy());
			else if (rateStrategy.equalsIgnoreCase("L"))
				ps = new PayStationImpl();
			else
			{
				System.out.println("Invalid rate strategy. Please try again.");
				return;
			}
		}
		
		int coin = -1;
		while (coin != 0) {
			System.out.println("Type in the amount you'd like to deposit or 0 to exit. (5, 10, or 25): ");
			coin = input.nextInt();
			if (coin == 0)
				return;
			try {
				ps.addPayment(coin);
				System.out.println("Coin accepted.");
			}
			catch (IllegalCoinException e) {
				System.out.println(e.getMessage());
			}
			
		}
	}

    public static void main(String[] args) throws IllegalCoinException {

        // running flag
        boolean running;
        //instance of paystation
        
        // we start in running state
        running = true;
        // providing no argument defaults to linear payrate 

        // main while loop for simulation
        while (running == true) {

            System.out.println("welcome to the parking meter pay station");
            System.out.println("1 : deposit coins \n2 : Display\n3 : buy ticket\n4"
                    + " : cancel\n5 : change rate strategy\n"
                    + "please make a selction by typing a number");
            //get user input as int 
            
            int userChoice = input.nextInt();
            switch (userChoice) {
                case 1:
                    depositCoins();
                    break;
                case 2:
                    // code block
                    break;
                case 3:
                    // code block
                    break;
                case 4:
                    // code block
                    break;
                case 5:
                    // code block
                    break;
                default:
                	System.out.println("you entered an invalid choice option");
                	running = false;
            }

        } // end while

    } // end main

} // endPayStationMain
