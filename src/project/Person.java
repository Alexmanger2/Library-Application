package project;



public class Person {

	
	private String firstName;
	private String lastName;
	private int birthYear;
	private String number;
	
//	private String membership;
//	
//	private String[] booksLentOut;
//	private int numberOfBooksLent;
//	private double fees = 0;
//	
	
	
	
	public Person() {
		// TODO Auto-generated constructor stub
	}

	/** Constructor that updates the first and last names and birth year.
	 * 
	 * @param firstName String updates the firstName
	 * @param lastName  String updates the lastName
	 * @param birthYear  int updates the birthYear
	 */
	public Person(String firstName, String lastName, int birthYear) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthYear = birthYear;
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
		return birthYear;
	}

	public void setDob(int dob) {
		this.birthYear = dob;
	}


	public String getNumber() {
		return number;
	}


	public void setNumber(String number) {
		this.number = number;
	}


	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName + ", dob=" + birthYear + ", number=" + number
				+ "]";
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Person bob = new Person("Bob", "Smith", 1875);
		bob.setNumber("555-444-3332");
		System.out.println(bob);
	}

}
