/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: <Justin Bugeja> <jbugeja> <758397>
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.newdawn.slick.SlickException;

public class ReadIn {  

	/** Reads in the dat file containing the ArrayList
	 * @return <Units> units*/
	public static ArrayList<Units> readUnits() 
			throws FileNotFoundException, SlickException {
	     // call the file to read
        Scanner scanner = new Scanner(new File("data/units.dat"));
        double x,y;
        String unitN;
        String line;
        String[] lineVector;
        ArrayList <Units> units = new ArrayList<Units>();

        while (scanner.hasNext()) {
        	
        	
        	 line = scanner.nextLine(); //read one line

             //separate all values by comma
             lineVector = line.split(",");
             
             //Store everything read into separate variables
             unitN = lineVector[0];
             x=Double.parseDouble(lineVector[1]);
             y=Double.parseDouble(lineVector[2]);
             
             //Creates a new unit depending on what is read in
             if (unitN.equals("PrinceAldric")) {
            	 PrinceAldric princeAldric = new PrinceAldric(x,y);
            	 units.add(princeAldric);
             }
             if (unitN.equals("Elvira")) {
            	 Elvira elvira = new Elvira(x,y);
            	 units.add(elvira);
             }
             if (unitN.equals("Garth")) {
            	 Garth garth = new Garth(x,y);
            	 units.add(garth);
             }
             if (unitN.equals("GiantBat")) {
            	 PassiveMonsters gBat = new PassiveMonsters(x,y, "GiantBat");
            	 units.add(gBat);
             }
             if (unitN.equals("Zombie")) {
            	 AggressiveMonsters zombie = new AggressiveMonsters(x,y, "Zombie");
            	 units.add(zombie);
             }
             if (unitN.equals("Bandit")) {
            	 AggressiveMonsters bandit = new AggressiveMonsters(x,y, "Bandit");
            	 units.add(bandit);
             }
             if (unitN.equals("Skeleton")) {
            	 AggressiveMonsters skeleton = new AggressiveMonsters(x,y, "Skeleton");
            	 units.add(skeleton);
             }
             if (unitN.equals("Draelic")) {
            	 AggressiveMonsters draelic = new AggressiveMonsters(x,y, "Draelic");
            	 units.add(draelic);
            	 
             }

            
        }
        scanner.close(); //Close the scanner
		return units;
	}
		
}


