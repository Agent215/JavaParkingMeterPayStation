package paystation.domain;

public class LinearRateStrategy implements RateStrategy {
	// linear rate strat
	public int calculateTime(int moneyInserted) {
		
		return (moneyInserted * 2) / 5;
	}
	
}
