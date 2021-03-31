package project;

public class Person {

	private String firstName;
	private String lastName;
	private int birthYear;
	private String phoneNumber;

//	private String membership;
//	
//	private String[] booksLentOut;
//	private int numberOfBooksLent;
//	private double fees = 0;
//	

	public Person() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor that updates the first and last names and birth year.
	 * 
	 * @param firstName String updates the firstName
	 * @param lastName  String updates the lastName
	 * @param birthYear int updates the birthYear
	 */
	public Person(String firstName, String lastName, int birthYear) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthYear = birthYear;
	}

	/**
	 * Getter for firstName
	 * 
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Setter method for firstName
	 * 
	 * @param firstName String Is used to set the firstName of the Person
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Getter for LastName
	 * 
	 * @return String lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Setter for lastName
	 * 
	 * @param lastName String sets the lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Getter for birthYear
	 * 
	 * @return int birthYear
	 */
	public int getBirthYear() {
		return birthYear;
	}

	/**
	 * Setter for birthYear
	 * 
	 * @param birthYear int sets the year of birth
	 */
	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	/**
	 * Getter for phoneNumber
	 * 
	 * @return String Returns the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Setter for phoneNumber
	 * 
	 * @param phoneNumber String sets phoneNumber
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName + ", birthYear=" + birthYear
				+ ", phoneNumber=" + phoneNumber + "]";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Person bob = new Person("Bob", "Smith", 1875);
		bob.setPhoneNumber("555-444-3332");
		System.out.println(bob);
	}

}
