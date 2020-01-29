package paystation.domain;

public class LinearRateStrategy implements RateStrategy {
	// linear rate strat
	@Override
	public double calculateTime(int moneyInserted) {
		
		return (moneyInserted * 2) / 5;
	}
	
}
