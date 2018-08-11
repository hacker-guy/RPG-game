
public class Volume {
	
	public static double calculateVolume(double r) {
		double v = 0;
		
		v = Math.PI*r*r*r*4/3;
		
		return v;
	
	}
	
    public static void main(String[] args) {
    	double r = Double.parseDouble(args[0]);
    	
        System.out.println("Volume is: " + calculateVolume(r)); 
    }
}