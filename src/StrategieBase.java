import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class StrategieBase {

	public double benefit;
	public double nextTimeStamp;
	public double cost;
	public double leakageTime;
	public double gamma;
	private List<Double> leakages;
	private double totalcost;
	private double  previousleak;
	private double avarageleak;
	
	public StrategieBase() {
		benefit = 0.0;
		nextTimeStamp = 0.0;
		gamma =0.0;
		leakages = new LinkedList<Double>();
		previousleak = 0;
		avarageleak = 0;
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

	public List<Double> getLeakages() {
		return leakages;
	}

	public void setLeakages(List<Double> leakages) {
		this.leakages = leakages;
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
		benefit = benefit + i;
	}

	public double getTimeStamp() {
		return nextTimeStamp;
	}

	public void setTimeStamp(int next_time_stamp) {
		this.nextTimeStamp = next_time_stamp;
	}
	

	public double getPreviousleak() {
		return previousleak;
	}

	public void setPreviousleak(double previousleak) {
		this.previousleak = previousleak;
	}

	public double getAvarageleak() {
		return avarageleak;
	}

	public void setAvarageleak(double avarageleak) {
		this.avarageleak = avarageleak;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
	
	public void applyCost(){
		totalcost= totalcost + cost;
	}

	public void resset(){
		benefit =0.0;
		nextTimeStamp = 0.0;
		leakages= new LinkedList<Double>();
		leakageTime=0.0;
		calcLeakage();
		totalcost = 0.0;
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

	public void Leak(double t) {
		avarageleak = (avarageleak + (t - previousleak) )/ 2.0;
		previousleak = t;
		
	}
}




