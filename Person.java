package project;

import java.util.Date;

public class Person {

	
	private String firstName;
	private String lastName;
	private int dob;
	
//	private String membership;
//	
//	private String[] booksLentOut;
//	private int numberOfBooksLent;
//	private double fees = 0;
//	
	
	
	
	public Person() {
		// TODO Auto-generated constructor stub
	}

	
	public Person(String first, String last, int birth) {
		this.firstName = first;
		this.lastName = last;
		this.dob = birth;
	}
	
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getDob() {
		return dob;
	}

	public void setDob(int dob) {
		this.dob = dob;
	}



	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName + ", dob=" + dob + "]";
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Person bob = new Person("Bob", "Smith", 1875);
		
		System.out.println(bob);
			
	}

}
