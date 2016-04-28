import java.awt.font.NumericShaper.Range;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class hillclimber {
	public static double hillclimber(ExponentialStrategie Player1, ExponentialStrategie Player2, FileWriter writer) throws InterruptedException, ExecutionException{
		double best = -Double.MAX_VALUE;
		double searchRange = 500.0;
		double start = Player1.getPlayGamma();
		Boolean changed = true;
		int steps = 0;
		while (searchRange >=25 && steps < 50) {
			List<Input> inputs = new LinkedList<Input>();
			if((start - searchRange/2.0) > 0){
				for(double i = start-searchRange/2.0; i < (start + searchRange/2.0) ; i+=searchRange/500){
					ExponentialStrategie newPlayer1 = new ExponentialStrategie(Player1,i);
					ExponentialStrategie newPlayer2 = new ExponentialStrategie(Player2);
					inputs.add(new Input(newPlayer1,newPlayer2));
				}
			}
			else{
				for(double i = Player1.cost; i < 2000+Player1.cost  ; i+=searchRange/500){
					ExponentialStrategie newPlayer1 = new ExponentialStrategie(Player1,i);
					ExponentialStrategie newPlayer2 = new ExponentialStrategie(Player2);
					inputs.add(new Input(newPlayer1,newPlayer2));
				}
			}
			List<Output> outputs =Simulator.processInputs(inputs);
			/*for(Output output : outputs){
				//System.out.println( output.benefit1-output.getCost1()/1000000*(1000000/output.getPlayrate1()));
				Simulator.add_to_csv(writer,output);
			}*/
			//System.out.println(outputs.toString());
			Output bestplayrate = Output.getmaxP1(outputs); 

		//	System.out.println("best " + bestplayrate.playrate1);
		//	System.out.println("best " + (bestplayrate.benefit1-bestplayrate.getCost1()/1000000*1000000*bestplayrate.getPlayrate1()));
		//	System.out.println("best " + 1/bestplayrate.playgammap1);
			double temp = bestplayrate.benefit1-bestplayrate.getCost1()/1000000*(1000000/bestplayrate.getPlayrate1());
			if(temp > best){
				best = temp;
				changed = false;
				start = 1/bestplayrate.playgammap1;
				searchRange = 2000.0;
			}
			else
				changed = true;
			if(changed)
				searchRange = searchRange / 2;
			
			++steps;
			inputs.clear();
		}
		return start;
	}
}
