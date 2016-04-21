

public class oldSimulator {
	public void old_simulator(ExponentialStrategie Player1,ExponentialStrategie Player2){ 
			double player1Mean = 0.0;
			double player2Mean = 0.0;
			
			int numberofsimulations =10000;
			int simulationTime = 100000;
			double t = 0.0;
			int owner = 1;
			Player1.calcNextTime();
			Player2.calcNextTime();
			Player1.calcLeakage();
			Player2.calcLeakage();
			for(int Number=0; Number<numberofsimulations; Number++){
				while(t < simulationTime){
					if(Player1.getLeakageTime() < Player2.getNextTimeStamp() && Player1.getLeakageTime() < Player1.getNextTimeStamp() ){
						if(owner==1){
							Player1.addBenefit(Player1.getLeakageTime()-t);
						}else{
							Player2.addBenefit(Player1.getLeakageTime()-t);
						}
						owner = 2;
						t = Player2.getLeakageTime();
						Player1.calcLeakage();;
						Player2.applyCost();;
						
					}
					if(Player2.getLeakageTime() < Player2.getNextTimeStamp()&& Player2.getLeakageTime()< Player1.getNextTimeStamp()){
						if(owner==1){
							Player1.addBenefit(Player2.getLeakageTime()-t);
						}else{
							Player2.addBenefit(Player2.getLeakageTime()-t);
						}
						owner = 1;
						t= Player2.getLeakageTime();
						Player2.calcLeakage();
						Player1.applyCost();
					}
					if(Player1.getNextTimeStamp() < Player2.getNextTimeStamp()){
						if(owner==1){
							Player1.addBenefit(Player1.getNextTimeStamp()-t);
						}else{
							Player2.addBenefit(Player1.getNextTimeStamp()-t);
						}
						owner = 1;
						t = Player1.getNextTimeStamp();
						Player1.calcNextTime();
						Player1.applyCost();
					} else if (Player2.getNextTimeStamp() < Player1.getNextTimeStamp()){
						if(owner==1){
							
							Player1.addBenefit(Player2.getNextTimeStamp()-t);
						}else {
							Player2.addBenefit(Player2.getNextTimeStamp()-t);
						}
						owner = 2;
						t = Player2.getNextTimeStamp();
						Player2.calcNextTime();
						Player2.applyCost();
					} else if (Player1.getNextTimeStamp() == Player2.getNextTimeStamp()){
						if(owner==1){
							Player1.addBenefit(Player1.getNextTimeStamp()-t);
						}else{
							Player2.addBenefit(Player1.getNextTimeStamp()-t);
						}
						t = Player1.getNextTimeStamp();
						Player1.calcNextTime();
						Player1.applyCost();
						Player2.calcNextTime();
						Player2.applyCost();
					}
				}
				player1Mean += Player1.benefit/t;
				player2Mean += Player2.benefit/t;
				Player1.resset();
				Player2.resset();
				owner = 1;
				t=0;
				System.out.println("Player1: " + (player1Mean/numberofsimulations));
				System.out.println("Player2: " + (player2Mean/numberofsimulations));
			}
			System.out.println("Player1: " + (player1Mean/numberofsimulations));
			System.out.println("Player2: " + (player2Mean/numberofsimulations));
	}
	
	
}
