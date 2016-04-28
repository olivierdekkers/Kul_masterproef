import java.util.Random;

public class ExponentialStrategie extends StrategieBase{
	
	public double playGamma;
	
	public ExponentialStrategie() {
		cost = 50;
		playGamma = 0.01;
		nextTimeStamp = 0.0;
		benefit = 0.0;
		gamma = 0;
		calcLeakage();
	}
	
	public ExponentialStrategie(double i ,  double k,double gamma){
		cost = k;
		playGamma = 1/i;
		nextTimeStamp = 0.0;
		benefit = 0.0;
		if (gamma >0)
		this.gamma = 1/gamma;
		else 
			gamma = 0;
		calcLeakage();
	}

	public double getPlayGamma() {
		return playGamma;
	}

	public ExponentialStrategie(ExponentialStrategie player1,double playgamma) {
		cost = player1.cost;
		playGamma = 1/playgamma;
		nextTimeStamp = 0.0;
		benefit = 0.0;
		this.gamma = player1.gamma;
		calcLeakage();
	}

	public ExponentialStrategie(ExponentialStrategie player) {
		cost = player.cost;
		playGamma = player.playGamma;
		nextTimeStamp = 0.0;
		benefit = 0.0;
		this.gamma = player.gamma;
		calcLeakage();
	}

	public void setPlayGamma(double playGamma) {
		this.playGamma = 1/playGamma;
	}

	public void setPeriode(int periode) {
		this.playGamma = periode;
	}

	public void calcNextTime() {
		Random rand = new Random();
	    double temp =   Math.log(1-rand.nextDouble())/(-playGamma);
		if(nextTimeStamp == 0.0){
			nextTimeStamp = temp;
		}else{
			nextTimeStamp = nextTimeStamp + temp;
		}
	}
	
	
}
