import java.util.Scanner;

public class SmallestNum {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int next, smallest = Integer.MAX_VALUE;
		while (in.hasNext()){
			next = in.nextInt();
			if(next < smallest) {
				smallest = next;
			}
		
		}
		System.out.println(smallest);
	}
	}
}
