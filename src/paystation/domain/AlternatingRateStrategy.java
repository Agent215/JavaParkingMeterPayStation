/* On Weekdays Uses Progressive Rate  */
 /* On Weekends Uses Linear Rate Strategy */
package paystation.domain;

import java.util.Calendar;
import java.util.Date;

public class AlternatingRateStrategy implements RateStrategy {

    /* we will have a private rate strategy for the alternating strategy which can switch 
       as needed */
    private RateStrategy rs;
    private Date day;
    private int moneyInserted;
    private double time;

    @Override
    public double calculateTime(int moneyIn) {

        moneyInserted = moneyIn;
        /*get current day of the week as a number */
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(day);
        System.out.println("this is the " + calendar.get(Calendar.DAY_OF_WEEK) + "th day of the week");

        /*if the day is saturday or sunday then lets change rate strategy*/
        if (Calendar.DAY_OF_WEEK == 1 || Calendar.DAY_OF_WEEK == 7) {
            rs = new LinearRateStrategy();
            time = rs.calculateTime(moneyInserted);
        } else {
            /* else if its the week */
            rs = new ProgressiveRateStrategy();
            time = rs.calculateTime(moneyInserted);
        }
        return time;
    }

}
