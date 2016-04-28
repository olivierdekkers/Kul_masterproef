
public class Input {
	ExponentialStrategie Player1;
	ExponentialStrategie Player2;
	
	
	public Input() {
		Player1 = new ExponentialStrategie();
		Player2 = new ExponentialStrategie();
	}

	public Input(ExponentialStrategie player1, ExponentialStrategie player2){
		Player1 = player1;
		Player2 = player2;
	}
	public ExponentialStrategie getPlayer1() {
		return Player1;
	}


	public void setPlayer1(ExponentialStrategie player1) {
		Player1 = player1;
	}


	public ExponentialStrategie getPlayer2() {
		return Player2;
	}


	public void setPlayer2(ExponentialStrategie player2) {
		Player2 = player2;
	}
	
	@Override
	public String toString() {
		String string ="";
		string +="\nbenefitP1: ";
		string += Player1.getBenefit();

		string +="\nPlayrateP1: ";
		string += 1/Player1.playGamma;

		string +="\nCostP1: ";
		string += Player1.getCost();

		string +="\nLeakageP1: ";
		string += 1/Player1.getGamma();

		string +="\nbenefitP2: ";
		string += Player2.benefit;

		string +="\nPlayRateP2: ";
		string += 1/Player2.playGamma;

		string +="\nCostP2: ";
		string += Player2.getCost();

		string +="\nLeakageP2: ";
		string += 1/Player2.getGamma();
		return string;
	}
	
}
