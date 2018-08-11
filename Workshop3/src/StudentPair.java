
public class StudentPair {

	public static String name1;
	public static String name2;
	public static Integer mark1;
	public static Integer mark2;
	
	public static void getPair(Student student1, Student student2) {
		name1 = student1.getName();
		name2 = student2.getName();
		
	}
	public static String compareMarks(Student student1, Student student2) {
		mark1 = student1.getMark1();
		mark2 = student2.getMark1();
		if (mark1 > mark2) {
			return name1 + "'s mark is " + (mark1 - mark2) + "greater"
					+ " than " + name2  + "'s mark";
		} else {
			return name2 + "'s mark is " + (mark2 - mark1) + "greater"
					+ " than " + name1  + "'s mark";
		}
	}

}
