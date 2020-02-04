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

    public static void main(String[] args) {

        // running flag
        boolean running;
        //instance of paystation
        PayStation ps;
        // we start in running state
        running = true;
        // providing no argument defaults to linear payrate 
        ps = new PayStationImpl();

        // main while loop for simulation
        while (running == true) {

            System.out.println("welcome to the parking meter pay station");
            System.out.println("1 : deposit coins \n2 : Display\n3 : buy ticket\n4"
                    + " : cancel\n5 : change rate strategy\n"
                    + "please make a selction by typing a number");
            //get user input as int 
            Scanner input = new Scanner(System.in);
            int userChoice = input.nextInt();
            switch (userChoice) {
                case 1:
                    // code block
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
            }

        } // end while

    } // end main

} // endPayStationMain
