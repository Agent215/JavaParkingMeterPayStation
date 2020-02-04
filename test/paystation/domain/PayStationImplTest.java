/**
 * Testcases for the Pay Station system.
 *
 * This source code is from the book "Flexible, Reliable Software: Using
 * Patterns and Agile Development" published 2010 by CRC Press. Author: Henrik B
 * Christensen Computer Science Department Aarhus University
 *
 * This source code is provided WITHOUT ANY WARRANTY either expressed or
 * implied. You may study, use, modify, and distribute it for non-commercial
 * purposes. For any commercial use, see http://www.baerbak.com/
 */
package paystation.domain;

import java.util.*;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

public class PayStationImplTest {

    PayStation ps;

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setup() {
        ps = new PayStationImpl();
        ps.initCoins();
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Entering 5 cents should make the display report 2 minutes parking time.
     */
    @Test
    public void shouldDisplay2MinFor5Cents()
            throws IllegalCoinException {
        ps.addPayment(5);
        assertEquals("Should display 2 min for 5 cents",
                2, ps.readDisplay());
    }

    /**
     * Entering 25 cents should make the display report 10 minutes parking time.
     */
    @Test
    public void shouldDisplay10MinFor25Cents() throws IllegalCoinException {
        ps.addPayment(25);
        assertEquals("Should display 10 min for 25 cents",
                10, ps.readDisplay());
    }

    /**
     * Verify that illegal coin values are rejected.
     */
    @Test(expected = IllegalCoinException.class)
    public void shouldRejectIllegalCoin() throws IllegalCoinException {
        ps.addPayment(17);
    }

    /**
     * Entering 10 and 25 cents should be valid and return 14 minutes parking
     */
    @Test
    public void shouldDisplay14MinFor10And25Cents()
            throws IllegalCoinException {
        ps.addPayment(10);
        ps.addPayment(25);
        assertEquals("Should display 14 min for 10+25 cents",
                14, ps.readDisplay());
    }

    /**
     * Buy should return a valid receipt of the proper amount of parking time
     */
    @Test
    public void shouldReturnCorrectReceiptWhenBuy()
            throws IllegalCoinException {
        ps.addPayment(5);
        ps.addPayment(10);
        ps.addPayment(25);
        Receipt receipt;
        receipt = ps.buy();
        assertNotNull("Receipt reference cannot be null",
                receipt);
        assertEquals("Receipt value must be 16 min.",
                16, receipt.value());
    }

    /**
     * Buy for 100 cents and verify the receipt
     */
    @Test
    public void shouldReturnReceiptWhenBuy100c()
            throws IllegalCoinException {
        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(25);
        ps.addPayment(25);

        Receipt receipt;
        receipt = ps.buy();
        assertEquals(40, receipt.value());
    }

    /**
     * Verify that the pay station is cleared after a buy scenario
     */
    @Test
    public void shouldClearAfterBuy()
            throws IllegalCoinException {
        ps.addPayment(25);
        ps.buy(); // I do not care about the result
        // verify that the display reads 0
        assertEquals("Display should have been cleared",
                0, ps.readDisplay());
        // verify that a following buy scenario behaves properly
        ps.addPayment(10);
        ps.addPayment(25);
        assertEquals("Next add payment should display correct time",
                14, ps.readDisplay());
        Receipt r = ps.buy();
        assertEquals("Next buy should return valid receipt",
                14, r.value());
        assertEquals("Again, display should be cleared",
                0, ps.readDisplay());
    }

    /**
     * Verify that cancel clears the pay station
     */
    @Test
    public void shouldClearAfterCancel()
            throws IllegalCoinException {
        ps.addPayment(10);
        ps.cancel();
        assertEquals("Cancel should clear display",
                0, ps.readDisplay());
        ps.addPayment(25);
        assertEquals("Insert after cancel should work",
                10, ps.readDisplay());
    }

    /**
     * Verify that the canceled entry does not add to the amount returned by
     * empty.
     *
     * @throws Exception
     */
    @Test
    public void cancelShouldNotAddToEmpty() throws Exception {
        PayStationImpl instance = new PayStationImpl();
        // we add 25 cents
        int amountAdded = 25;
        instance.addPayment(amountAdded);
        // then we cancel
        instance.cancel();
        // we add 25 cents again
        instance.addPayment(amountAdded);
        // after canceling we expect result to be zero if though we added coins 
        // we never bought the time so emptying reutrns 0
        int result = instance.empty();
        assertEquals(0, result);

    }

    /**
     * Very that the empty method resets the total to zero.
     *
     * @throws Exception
     */
    @Test
    public void testEmptyZero() throws Exception {
        /* money in machine should be reset to 0 after emptied */
 /* add some fake payment to test the buy before emptying*/
        PayStationImpl inst = new PayStationImpl();
        int amountToAdd = 25;
        inst.addPayment(amountToAdd);
        inst.empty();
        int result = (int) inst.totalInMachine;
        assertEquals("this should print 0", 0, result);
    }

    /**
     * Testing cancel function returns a hash map with a single coin after a
     * single coin is inserted
     */
    @Test
    public void cancelReturnsOneCoin()
            throws IllegalCoinException {

        ps.addPayment(5);
        /* we expect to return a map with 1 nickel in it, so we get the instance of the
        * pay machine and get the count of all coins with value 5 (ie all nickels) */
        assertEquals(" this should be 1 nickel", 1, (int) ps.cancel().get(5));

    } // end cancelReturnsOneCoin

    /**
     * Testing that cancel can take in a variety of coin types and return the
     * correct denominations when called. even if they total an amount equal to
     * another denomination. IE 5 nickels canceled should give you back 5
     * nickles not 1 quarter.
     */
    @Test
    public void cancelReturnsCorrectCoins()
            throws IllegalCoinException {
        /* here we add twenty five cents*/
        ps.addPayment(5);
        ps.addPayment(5);
        ps.addPayment(5);
        ps.addPayment(10);

        /* the map should return the correct coin denominations not just one 25 cent coin*/
        Map temp = ps.cancel();

        /* we expect 3 nickels and one dime  and 0 quarters*/
        assertEquals("should return 3 nickels", 3, (int) temp.get(5));
        assertEquals("should return 1 dime", 1, (int) temp.get(10));
        assertEquals("should return 0 Quarters", 0, (int) temp.get(25));

    }//end cancelReturnsCorrectCoins()

    /**
     * Verify that cancel clears the map.
     *
     * @throws Exception
     */
    @Test
    public void testCancelClearMap() throws Exception {
        PayStationImpl instance = new PayStationImpl();
        int quarter = 25;
        int dime = 10;
        int nickle = 5;
        instance.addPayment(quarter);
        instance.addPayment(nickle);
        instance.addPayment(dime);
        instance.addPayment(quarter);
        instance.cancel();
        Map result = instance.cancel();
        Map emptyMap = new HashMap();
        emptyMap.put(0, 0);
        emptyMap.put(5, 0);
        emptyMap.put(10, 0);
        emptyMap.put(25, 0);
        assertEquals(emptyMap, result);
    }

    /**
     * Verify that buy clears the map.
     *
     * @throws Exception
     */
    @Test
    public void testBuyClearMap() throws Exception {
        PayStationImpl instance = new PayStationImpl();
        int quarter = 25;
        int dime = 10;
        int nickle = 5;
        instance.addPayment(quarter);
        instance.addPayment(nickle);
        instance.addPayment(dime);
        instance.addPayment(quarter);
        instance.buy();
        Map result = instance.cancel();
        //Test to check buy() clears map
        assertEquals("count of nickels should be 0", (int) result.get(5), 0);
        assertEquals("count of dimes should be 0", (int) result.get(10), 0);
        assertEquals("count of quarters should be 0", (int) result.get(25), 0);
    }

    /**
     * Test for reporting the money taken in since last time empty was called
     */
    @Test
    public void ShouldReportAfterMoneyEmptied()
            throws IllegalCoinException {
        /* add some fake payment to test the buy before emptying*/
        ps.addPayment(10);
        /* here we use the 10 cents to buy some time*/
        ps.buy();
        /* even though we add more money this should not be reported*/
        ps.addPayment(10);
        /* money removed from the machine should be only from what was spent.*/
        int moneyRemoved = ps.empty();
        assertEquals("this should print 10", 10, moneyRemoved);

    } // end ShouldReportAfterMoneyEmptied
    
    /**
     * Test for testing LinearRateStrategy
     */
    @Test
    public void LinearRateStrategyTest() 
    	throws IllegalCoinException {
    	
    	ps.addPayment(25);
    	ps.addPayment(10);
    	ps.addPayment(25);
    	ps.addPayment(25);
    	Receipt r = ps.buy();
    	assertEquals("Linear Rate Strategy is not returning 34 minutes.", 34, r.value());
    }
    
    
    /**
     * Test for progressive rate strategy
     */
    @Test
    public void ProgressiveRateStrategyTest() 
    	throws IllegalCoinException {
    	PayStationImpl temp = new PayStationImpl(new ProgressiveRateStrategy());
    	for (int a = 0; a<=3; a++) 
    		temp.addPayment(25);
    	Receipt r = temp.buy();
    	assertEquals("Progressive Rate Strategy is not returning 40 minutes.", 40, r.value());
    	for (int a = 0; a<=7; a++) 
    		temp.addPayment(25);
    	Receipt x = temp.buy();
    	assertEquals("Progressive Rate Strategy is not returning 60 minutes.", 60, x.value());
    	for (int a = 0; a<=8; a++) 
    		temp.addPayment(25);
    	Receipt z = temp.buy();
    	assertEquals("Progressive Rate Strategy is not returning 45 minutes.", 45, z.value());
    	
    }
    
    /**
     * Test for alternating rate strategy
     */
    @Test
    public void AlternateRateStrategyTest() 
    	throws IllegalCoinException {
    	PayStationImpl temp = new PayStationImpl(new AlternatingRateStrategy());
    	Date now = new Date();
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(now);
    	
    	if (calendar.get(Calendar.DAY_OF_WEEK) == 1 || calendar.get(Calendar.DAY_OF_WEEK) == 7) {
    		temp.addPayment(25);
    		temp.addPayment(25);
    		temp.addPayment(25);
    		temp.addPayment(25);
    		Receipt r = temp.buy();
        	assertEquals("Alternate Rate Strategy: Linear Rate Strategy is not returning 40 minutes.", 40, r.value());
    	}
    	else
    	{
    		for (int a = 0; a<=7; a++) 
        		temp.addPayment(25);
        	Receipt x = temp.buy();
        	assertEquals("Alternate Rate Strategy: Progressive Rate Strategy is not returning 60 minutes.", 60, x.value());
    	}
    	
    	
    }
    
}
