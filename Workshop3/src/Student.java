
public class Student {
	
	public static int studentCounter = 0;
	
	Student() {
		studentCounter++;
	}
	
	private String studentID;
	public String getStudentID() {
		return studentID;
	}
	public void setStudentID(String ID) {
		studentID = ID;
	}
	
	private String name;
	public String getName() {
		return name;
	}	
	public void setName(String namein) {
		name = namein;
	}

	private Integer mark1;
	public Integer getMark1() {
		return mark1;
	}	
	public void setMark1(Integer mark) {
		mark1 = mark;
	}
	
	public String toString() {
		return "Name is " + name;
	}
	
	public boolean equals(Student student) {
		if (this.studentID == student.studentID) {
			return true;
		}else {
			return false;
		}
	}
}
	
