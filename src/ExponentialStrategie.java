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
		if(getAvarageleak() !=0.0)
			return (playGamma + 1/getAvarageleak())/2.0;
		else
			return playGamma;
	}

	public void setPlayGamma(double playGamma) {
		this.playGamma = playGamma;
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
