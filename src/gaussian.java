import java.util.Random;

public class gaussian {

		  private static Random fRandom = new Random();
		  
		  static double getGaussian(double aMean, double aVariance){
		    return aMean + fRandom.nextGaussian() * aVariance;
		  }
}
