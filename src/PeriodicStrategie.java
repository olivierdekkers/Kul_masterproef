
public class PeriodicStrategie extends StrategieBase{
	public double periode;
	
	
	public PeriodicStrategie(double i ,  int k,double gamma){
		cost = k;
		periode = i;
		nextTimeStamp = 0.0;
		benefit = 0.0;
		this.gamma = gamma;
	}

	public double getPeriode() {
		return periode;
	}

	public void setPeriode(int periode) {
		this.periode = periode;
	}

	public void calcNextTime() {
		if(nextTimeStamp == 0.0){
			nextTimeStamp = nextTimeStamp + periode * Math.random();
		}else{
			nextTimeStamp = nextTimeStamp + periode;
		}
	}
	
	
	
}
