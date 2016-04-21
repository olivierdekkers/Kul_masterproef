import java.util.List;

public class Output {
	double playrate1;
	double playrate2;
	double benefit1;
	double benefit2;
	double cost1;
	double cost2;
	double leakage1;
	double leakage2;
	double totalcostPlayer1;
	double totalcostPlayer2;
	double avarageleakageP1;
	double avarageleakageP2;
	
	
	public Output() {
		playrate1 = 0.0;
		playrate2=0.0;
		benefit1=0.0;
		benefit2=0.0;
		cost1=0.0;
		cost2=0.0;
		leakage1=0.0;
		leakage2 =0.0;
		totalcostPlayer1 = 0.0;
		totalcostPlayer2 = 0.0;
		
	}
	
	public Output (ExponentialStrategie player1, ExponentialStrategie player2, double mean1 , double mean2) {
		playrate1 = player1.getPlayGamma();
		playrate2 = player2.getPlayGamma();
		benefit1 = mean1;
		benefit2 = mean2;
		cost1 = player1.getCost();
		cost2 = player2.getCost();
		leakage1 = player1.getGamma();
		leakage2 = player2.getGamma();
		totalcostPlayer1 = player1.getTotalcost();
		totalcostPlayer2 = player2.getTotalcost();
		avarageleakageP1 = player1.getAvarageleak();
		avarageleakageP2 = player2.getAvarageleak();
	} 

	public double getPlayrate1() {
		return playrate1;
	}


	public void setPlayrate1(double palyrate1) {
		this.playrate1 = palyrate1;
	}


	public double getPlayrate2() {
		return playrate2;
	}


	public void setPlayrate2(double palyrate2) {
		this.playrate2 = palyrate2;
	}


	public double getBenefit1() {
		return benefit1;
	}


	public void setBenefit1(double benefit1) {
		this.benefit1 = benefit1;
	}


	public double getBenefit2() {
		return benefit2;
	}


	public void setBenefit2(double benefit2) {
		this.benefit2 = benefit2;
	}


	public double getCost1() {
		return cost1;
	}


	public void setCost1(double cost1) {
		this.cost1 = cost1;
	}


	public double getCost2() {
		return cost2;
	}


	public void setCost2(double cost2) {
		this.cost2 = cost2;
	}


	public double getLeakage1() {
		return leakage1;
	}


	public void setLeakage1(double leakage1) {
		this.leakage1 = leakage1;
	}


	public double getLeakage2() {
		return leakage2;
	}


	public void setLeakage2(double leakage2) {
		this.leakage2 = leakage2;
	}

	public static double getmaxP1(List<Output> outputs) {
		double max =-Double.MAX_VALUE;
		double playrate = 0;
		for(Output output : outputs){
			if((output.getBenefit1()-output.getCost1()/1000000*(1000000*output.getPlayrate1()))>max){
				max = output.getBenefit1()-output.getCost1()/1000000*(1000000*output.getPlayrate1());
				playrate = output.getPlayrate1();
			}
		}
		return playrate;
	}

	public static double getmaxP2(List<Output> outputs) {
		double max =-Double.MAX_VALUE;
		double playrate = 0;
		for(Output output : outputs){
			if((output.getBenefit2()-output.getCost2()/1000000*(1000000*output.getPlayrate2()))>max){
				max =output.getBenefit2()-output.getCost2()/1000000*(1000000*output.getPlayrate2());
				playrate = output.getPlayrate2();
			}
		}
		return playrate;
	}
}
