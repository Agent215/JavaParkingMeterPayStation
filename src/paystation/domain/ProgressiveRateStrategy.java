/*
This method charges more money per min as time bought increases
Much of this code was copied from the project description
 */
package paystation.domain;

public class ProgressiveRateStrategy implements RateStrategy {

    private int insertedSoFar;

    public void resetInserted() {
        insertedSoFar = 0;
    } // end resetInserted

    // return type needed to be double to accurately calculate when insertedSoFar is greater than $2.00 
    @Override
    public double calculateTime(int moneyInserted) {

        insertedSoFar = moneyInserted;
        if (insertedSoFar <= 150) {
            /* less than an hour (60 Min) so amount <150 */
            return (moneyInserted * 2) / 5;
        } else if (insertedSoFar < 350) {
            /* between 1st hour and 2nd hour so 350>amount >=150 */
            return (double) (((moneyInserted - 150) * .3) + 60);
        } else {
            /* greater than 2 hours so amount >= 350 */
            return (((moneyInserted - 350) / 5) + 120);
        }
    } // end calculateTime

} // ProgressiveRateStrategy
