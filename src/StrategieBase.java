import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class StrategieBase {

	public double benefit;
	public double nextTimeStamp;
	public double cost;
	public double leakageTime;
	public double gamma;
	private double totalcost;
	private double previouslay;
	private double avarageplay;
	private int numberOfPlays;
	
	public StrategieBase() {
		benefit = 0.0;
		nextTimeStamp = 0.0;
		gamma =0.0;
		previouslay = 0;
		avarageplay = 0;
	}
	
	public double getNextTimeStamp() {
		return nextTimeStamp;
	}

	public void setNextTimeStamp(double nextTimeStamp) {
		this.nextTimeStamp = nextTimeStamp;
	}

	public double getGamma() {
		return gamma;
	}

	public void setGamma(double gamma) {
		this.gamma = gamma;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getBenefit() {
		return benefit;
	}

	public void setBenefit(double benefit) {
		this.benefit = benefit;
	}

	public void addBenefit(double i) {
		benefit += i;
	}

	public double getTimeStamp() {
		return nextTimeStamp;
	}

	public void setTimeStamp(int next_time_stamp) {
		this.nextTimeStamp = next_time_stamp;
	}
	

	public double getPreviousleak() {
		return previouslay;
	}

	public void setPreviousleak(double previousleak) {
		this.previouslay = previousleak;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
	
	public void applyCost(double t){
		totalcost+= cost;
		avarageplay +=(t - previouslay);
		previouslay = t;
		numberOfPlays++;
	}

	public void resset(){
		benefit =0.0;
		nextTimeStamp = 0.0;
		leakageTime=0.0;
		calcLeakage();
		totalcost = 0.0;
		previouslay = 0.0;
		avarageplay = 0.0;
		numberOfPlays = 0;
	}

	public double getLeakageTime() {
		return leakageTime;
	}

	public void setLeakageTime(double leakage_time_stamp) {
		this.leakageTime = leakage_time_stamp;
	}
	
	public  void calcLeakage() {
		if(gamma ==0)
			leakageTime= 100000000;
		else
			leakageTime +=getNext(gamma);
	}
	public double getNext(double Gamma) {
		Random rand = new Random();
	    return  Math.log(1-rand.nextDouble())/(-Gamma);
	}
	
	
	public double getTotalcost() {
		return totalcost;
	}

	public void setTotalcost(double totalcost) {
		this.totalcost = totalcost;
	}

	public double getPreviouslay() {
		return previouslay;
	}

	public void setPreviouslay(double previouslay) {
		this.previouslay = previouslay;
	}

	public double getAvarageplay() {
		return avarageplay;
	}

	public void setAvarageplay(double avarageplay) {
		this.avarageplay = avarageplay;
	}

	public int getNumberOfPlays() {
		return numberOfPlays;
	}

	public void setNumberOfPlays(int numberOfPlays) {
		this.numberOfPlays = numberOfPlays;
	}

	public double getActualPlayRate(){
		return avarageplay/numberOfPlays; 
	}
	
}




