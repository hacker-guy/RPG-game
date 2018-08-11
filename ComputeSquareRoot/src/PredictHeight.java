import java.util.Scanner;

public class PredictHeight {

	public static final int male = 1;
	
	public static void main(String[] args) {
		boolean male;
		double Hmale_height, Hfemale_height, Hmother, Hfather;
		
		System.out.println("Please type your gender "
				+ "followed by Hmother then Hfather");
		Scanner in = new Scanner(System.in);
		
		if (in.next().equals("male")) {
			male = true;
		} else {
			male = false;
		}
		
		Hmother = in.nextDouble();
		Hfather = in.nextDouble();
		
		if (male) {
			Hmale_height = ((Hmother*13/12) + Hfather)/2;
			System.out.println(Hmale_height);
		}else{
			Hfemale_height = ((Hfather*12/13) + Hmother)/2;
			System.out.println(Hfemale_height);
		}
		
	}
}
