

public class oldSimulator {
	public void old_simulator(ExponentialStrategie player1,ExponentialStrategie player2){ 
		double player1Mean = 0.0;
		double player2Mean = 0.0;
		double player1playrate = 0.0;
		double player2playrate = 0.0;
		
		int numberofsimulations =1000;
		int simulationTime = 1000000;
		double t = 0.0;
		int owner = 1;
		player1.calcNextTime();
		player2.calcNextTime();
		player1.calcLeakage();
		player2.calcLeakage();
		for(int Number=0; Number<numberofsimulations; Number++){
			while(t < simulationTime){
				if(player1.getLeakageTime() < player2.getTimeStamp() && player1.getLeakageTime() < player1.getTimeStamp()){
					if(owner==1){
						player1.addBenefit(player1.getLeakageTime()-t);
						player2.applyCost(t);
					}else{
						player2.addBenefit(player1.getLeakageTime()-t);
					}
					owner = 2;
					t = player1.getLeakageTime();
					player1.calcLeakage();
				}
				if(player2.getLeakageTime() < player2.getTimeStamp() && player2.getLeakageTime() < player1.getTimeStamp() ){
					if(owner==1){
						player1.addBenefit(player2.getLeakageTime()-t);
					}else{
						player2.addBenefit(player2.getLeakageTime()-t);
						player1.applyCost(t);
					}
					owner = 1;
					t= player2.getLeakageTime();
					player2.calcLeakage();
					//player1.LeakageTimeStamp(t);
				}
				if(player1.getTimeStamp() < player2.getTimeStamp() && player1.getTimeStamp() < player1.getLeakageTime() && player1.getTimeStamp() < player2.getLeakageTime()){
					if(owner==1){
						player1.addBenefit(player1.getTimeStamp()-t);
					}else{
						player2.addBenefit(player1.getTimeStamp()-t);
					}
					owner = 1;
					t = player1.getTimeStamp();
					player1.calcNextTime();
					player1.applyCost(t);
				}
				if(player2.getTimeStamp() < player1.getTimeStamp() && player2.getTimeStamp() < player1.getLeakageTime() && player2.getTimeStamp() < player2.getLeakageTime()){
					if(owner==1){
						
						player1.addBenefit(player2.getTimeStamp()-t);
					}else {
						player2.addBenefit(player2.getTimeStamp()-t);
					}
					owner = 2;
					t = player2.getTimeStamp();
					player2.calcNextTime();
					player2.applyCost(t);
				} else if (player1.getTimeStamp() == player2.getTimeStamp()){
					if(owner==1){
						player1.addBenefit(player1.getTimeStamp()-t);
					}else{
						player2.addBenefit(player1.getTimeStamp()-t);
					}
					t = player1.getTimeStamp();
					player1.calcNextTime();
					player1.applyCost(t);
					player2.calcNextTime();
					player2.applyCost(t);
				}
			}
				player1Mean += player1.benefit/t;
				player2Mean += player2.benefit/t;
				player1playrate += player1.getActualPlayRate();
				player2playrate += player2.getActualPlayRate();
				player1.resset();
				player2.resset();
				owner = 1;
				t=0;
			}
		player1playrate = player1playrate/numberofsimulations;
		player2playrate = player2playrate/numberofsimulations;
		player1Mean = player1Mean/numberofsimulations;
		player2Mean = player2Mean/numberofsimulations;
		player1.setBenefit(player1Mean);
		player2.setBenefit(player2Mean);
		player1.setAvarageplay(player1playrate);
		player2.setAvarageplay(player2playrate);
		Output output = new Output(player1, player2);
		
		System.out.println(player1.getAvarageplay());
	}
	
	
}
