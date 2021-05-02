package project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Person {

	public static final String PERSON_FILEPATH = "src/Persons.csv";
	
	private String firstName;
	private String lastName;
	private int birthYear;
	private String phoneNumber;
	private double lateBalance;

	private Address addy;

	protected Map<Book, Date> map = new HashMap<>();
	

	public Person() {}

	/**
	 * Constructor that updates the first and last names and birth year.
	 * 
	 * @param firstName String updates the firstName
	 * @param lastName  String updates the lastName
	 * @param birthYear int updates the birthYear
	 */
	public Person(String firstName, String lastName, int birthYear, Address add) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthYear = birthYear;
		this.lateBalance = 0.0;
		this.setAddy(add);

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

	/**
	 * Getter for getLateBalance
	 * 
	 * @return balance owed by person instance
	 */

	public double getLateBalance() {
		return lateBalance;
	}

	/**
	 * Getter for getAddy
	 * 
	 * @return Address
	 */

	public Address getAddy() {
		return addy;
	}

	/**
	 * Setter for setAddy
	 * 
	 * @param address assigned to person
	 */
	public void setAddy(Address addy) {
		this.addy = addy;
	}

	/**
	 * Setter for setLateBalance
	 * 
	 * @param Double variable sets update balance
	 */

	public void setLateBalance(double lateBalance) {
		this.lateBalance = lateBalance;
	}

	/**
	 * Setter for setBookList
	 * 
	 * @param Book variable sets what book was taken out and the time
	 */
	public void setBookList(Book b) {
		Calendar cal = Calendar.getInstance();
		map.put(b, cal.getTime());

	}

	/**
	 * Getter for getBookList
	 * 
	 * @return Map that contains the books checked out by person
	 */

	public Map<Book, Date> getBookList() {
		return map;
	}

	/*
	 * @Override public String toString() { return "Person [firstName=" + firstName
	 * + ", lastName=" + lastName + ", birthYear=" + birthYear + ", phoneNumber=" +
	 * phoneNumber + ", lateBalance=" + lateBalance + ", map=" + map.keySet() +
	 * map.values() + "]";
	 * 
	 * }
	 */

	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName + ", birthYear=" + birthYear
				+ ", phoneNumber=" + phoneNumber + ", lateBalance=" + lateBalance + ", addy=" + addy + ", map="
				+ map.keySet() + map.values() + "]";
	}

	public static void main(String[] args) throws IOException {
		Address myAddress = new Address("100", "Staten Island", "10301", "New York");
		Person bob = new Person("Bob", "Smith", 1875, myAddress);
		bob.setPhoneNumber("555-444-3332");
		CSVHandler.write(PERSON_FILEPATH, true, bob);
		Person p2 = new Person();
		System.out.println("Person p2 before being assigned from CSVHandler.getPerson() " + p2);
		p2 = CSVHandler.getPerson(PERSON_FILEPATH, bob.getPhoneNumber());
		
		System.out.println("Person p2 after being assigned from CSVHandler.getPerson() " + p2);

	}

}