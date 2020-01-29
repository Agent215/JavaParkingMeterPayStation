package paystation.domain;

public class ProgressiveRateStrategy implements RateStrategy{
	private int insertedSoFar;
	
	
	
	public void resetInserted() {
		insertedSoFar = 0;
	}
	
	// return type needed to be double to accurately calculate when insertedSoFar is greater than $2.00 
	public double calculateTime(int moneyInserted) {
		insertedSoFar += moneyInserted;
		if (insertedSoFar >= 150)
		{
			return (moneyInserted / 5) * 2;
		}
		else if (insertedSoFar >= 200) {
			return (double)((moneyInserted / 5) * 1.5);
		}
		else {
			return (moneyInserted / 5);
		}
	}

	
}
