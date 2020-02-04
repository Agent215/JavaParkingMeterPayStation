/* On Weekdays Uses Progressive Rate  */
 /* On Weekends Uses Linear Rate Strategy 

    Right now this does not handle the case where someone buys time right before the weekend starts
    or if they buy time sunday night. 
    So if they buy time sunday night at 11:59 pm it will still charge them for a linear rate.
    this could be fixed by checking what time of day it is, then covering that case.*/
package paystation.domain;

import java.util.Calendar;
import java.util.Date;

public class AlternatingRateStrategy implements RateStrategy {

    /* we will have a private rate strategy for the alternating strategy which can switch 
       as needed 
     */
    private RateStrategy rs;
    private Date day = new Date();
    private int moneyInserted;
    private double time;

    @Override
    public double calculateTime(int moneyIn) {
    	
        moneyInserted = moneyIn;
        /*get current day of the week as a number */
        Calendar calendar = Calendar.getInstance();
        //calendar.setTime(day);
        calendar.setTime(day);
        
        System.out.println("this is the " + calendar.get(Calendar.DAY_OF_WEEK) + "th day of the week");

        /*if the day is saturday or sunday then lets change rate strategy*/
        if (calendar.get(Calendar.DAY_OF_WEEK) == 1 || calendar.get(Calendar.DAY_OF_WEEK) == 7) {
            rs = new LinearRateStrategy();
            time = rs.calculateTime(moneyInserted);
        } else {
            /* else if its the week */
            rs = new ProgressiveRateStrategy();
            time = rs.calculateTime(moneyInserted);
        }
        return time;
    } // end calculateTime

} // end AlternatingRateStrategy
