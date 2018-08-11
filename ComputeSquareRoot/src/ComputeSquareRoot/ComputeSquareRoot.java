package ComputeSquareRoot;
import java.util.Scanner;

public class ComputeSquareRoot {

	
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		double n = in.nextDouble();
		double r=0.0, guess = n/2, eps = 0.01;
		
		
		while (Math.abs(guess/r-1) > eps) {
			r = n/guess;
			guess = (guess+r)/2;
		}
		System.out.print(guess);
		
	}
	
}
