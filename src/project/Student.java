package project;

public class Student extends Person{

	
	private String school;
	private int grade;
	//private Person emergencyContact;
	
	public Student() {
		
	}

	public Student(String school, int grade) {
		this.school = school;
		this.grade = grade;
	}
	
	public static void main(String[] args) {
		
		Student st = new Student();
		System.out.println();
	}

}
