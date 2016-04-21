
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
	
}
