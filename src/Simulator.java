import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Simulator {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		try {
			
			FileWriter writer = new FileWriter("temp.csv");
			create_csv(writer);
			double i = 800;
			double j = 800;
			boolean current = true;
			//hillclimber.hillclimber(Player1,Player2,writer)
			for(int steps = 0 ; steps < 100; steps++){
					if(current){
						ExponentialStrategie Player1 = new ExponentialStrategie(i,200,0);
						ExponentialStrategie Player2 = new ExponentialStrategie(j,200,0);
						i =hillclimber.hillclimber(Player1,Player2,writer);
					}
					else{
						ExponentialStrategie Player1 = new ExponentialStrategie(i,200,0);
						ExponentialStrategie Player2 = new ExponentialStrategie(j,200,0);
						j =hillclimber.hillclimber(Player2,Player1,writer);
					}
				current = !current; 
				System.out.println("step " + steps);
				System.out.println("i " + i);
				System.out.println("j " + j);
			}
			System.out.println("donezo");
			close_writer(writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static void create_csv(FileWriter writer) {
		 //generateCsvFile(writer);
	}
	private static void generateCsvFile(FileWriter writer)
	   {
			try
			{
			    writer.append("Player1");
			    writer.append(',');
			    writer.append(',');
			    writer.append(',');
			    writer.append(',');
			    writer.append("Player2");
			    writer.append('\n');
	
			    writer.append("Benefit");
			    writer.append(',');
			    writer.append("cost");
			    writer.append(',');
			    writer.append("playrate");
			    writer.append(',');
			    writer.append("Leakage");
			    writer.append(',');
					
		        writer.append("Benefit");
			    writer.append(',');
			    writer.append("cost");
			    writer.append(',');
			    writer.append("playrate");
			    writer.append(',');
			    writer.append("Leakage");
		        writer.append('\n');
			}
			catch(IOException e)
			{
			     e.printStackTrace();
			} 
	    }
	
	public static void add_to_csv(FileWriter writer, Output output){
		try {
			writer.append(""+output.benefit1);
			writer.append(',');
		    writer.append(""+output.cost1);
		    writer.append(',');
		    writer.append(""+output.playrate1);
		    writer.append(',');
		    if(output.leakage1 !=0)
		    	writer.append(""+1/output.leakage1);
		    else
		    	writer.append(""+0);
		    writer.append(',');
				
	        writer.append(""+output.benefit2);
		    writer.append(',');
		    writer.append(""+output.cost2);
		    writer.append(',');
		    writer.append(""+output.playrate2);
		    writer.append(',');
		    if(output.leakage2 !=0)
		    	writer.append(""+1/output.leakage2);
		    else
		    	writer.append(""+0);
		    writer.append(',');
	        writer.append('\n');
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	}
	private static void close_writer(FileWriter writer){
	    try {
			writer.flush();
		    writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Output simulate(ExponentialStrategie player1,ExponentialStrategie player2){
		double player1Mean = 0.0;
		double player2Mean = 0.0;
		double player1playrate = 0.0;
		double player2playrate = 0.0;
		
		int numberofsimulations =500;
		int simulationTime = 100000;
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
				else if(player2.getLeakageTime() < player2.getTimeStamp() && player2.getLeakageTime() < player1.getTimeStamp() ){
					if(owner==1){
						player1.addBenefit(player2.getLeakageTime()-t);
					}else{
						player2.addBenefit(player2.getLeakageTime()-t);
						player1.applyCost(t);
					}
					owner = 1;
					t= player2.getLeakageTime();
					player2.calcLeakage();
				}else if (player1.getLeakageTime() == player2.getLeakageTime() && player1.getLeakageTime() < player1.getTimeStamp() && player2.getTimeStamp() > player1.getLeakageTime()) {
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
				else if(player2.getTimeStamp() < player1.getTimeStamp() && player2.getTimeStamp() < player1.getLeakageTime() && player2.getTimeStamp() < player2.getLeakageTime()){
					if(owner==1){
						player1.addBenefit(player2.getTimeStamp()-t);
					}else {
						player2.addBenefit(player2.getTimeStamp()-t);
					}
					owner = 2;
					t = player2.getTimeStamp();
					player2.calcNextTime();
					player2.applyCost(t);
				} else if (player1.getTimeStamp() == player2.getTimeStamp() && player1.getTimeStamp()< player1.getLeakageTime() && player1.getTimeStamp() < player2.getLeakageTime()){
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
		return output;
	}
	
	
	
	public static List<Output> processInputs(List<Input> inputs)
        throws InterruptedException, ExecutionException {

	    int threads = Runtime.getRuntime().availableProcessors();
	    ExecutorService service = Executors.newFixedThreadPool(threads);

	    List<Future<Output>> futures = new ArrayList<Future<Output>>();
	    for (final Input input : inputs) {
	        Callable<Output> callable = new Callable<Output>() {
	            public Output call() throws Exception {
	                Output output = new Output();
	                output = simulate(input.getPlayer1(),input.getPlayer2());
	                return output;
	            }
	        };
	        futures.add(service.submit(callable));
	    }

	    service.shutdown();

	    List<Output> outputs = new ArrayList<Output>();
	    for (Future<Output> future : futures) {
	        outputs.add(future.get());
	    }
	    return outputs;
	}
	
	
	
}

