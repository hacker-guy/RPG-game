
public class main {

	public static void main(String[] args) {
		
		Student student1 = new Student();
		Student student2 = new Student();
		
		student1.setName("Johnnyboy");
		student2.setName("Johnnyboy");
		
		
		System.out.println(student1);
		
		student1.setStudentID("123");
		student2.setStudentID("123");
		
		student1.setMark1(123);
		student1.setMark1(1230);
		
		System.out.println("Student ID is: " + student1.getStudentID());
		
		System.out.println(student1.equals(student2));
		
		System.out.println(Student.studentCounter);
	
		StudentPair.getPair(student1, student2);
		
		System.out.println(StudentPair.compareMarks(student1, student2));

	}

}
