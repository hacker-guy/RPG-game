import java.util.Scanner;

public class Workshop7 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the degree of difficulty: ");
		double diff = input.nextDouble();
		System.out.println("Please enter seven scores: ");
		int i;
		int high = 0;
		int low = 7;
		double[] scores = new double[7];
		for (i = 0; i < 7; i++) {
			scores[i] = input.nextDouble();
			if (scores[i] > high) {
				high = i;
			}
			if (scores[i] < low) {
				low = i;
			}
		}
		input.close();
		double sum = 0;
		for (i = 0; i < 7; i++) {
			if (i != high && i != low) {
				sum += scores[i];
			}
		}
		double result = sum*diff;
		
		System.out.println("Your score is: " + result);
	}
}
