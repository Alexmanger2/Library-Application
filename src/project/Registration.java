package project;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Registration {

	private Person membershipType; // type of member

	private boolean hasCard = false; // valid card or not
	private boolean expired = false; // expiration date

	private int issuedDate; // date card was issued
	private int currentDate; // todays date

	private String name; // name of person
	private String num; // phone number
	private String type; // child/adult

	private Address holdAddress;

	private List<Person> personList = new ArrayList<>();

	public Registration() {

	}

	/**
	 * Checks to see if library card has expired or not
	 * 
	 * @return true if it is valid and vice versa
	 */
	public boolean checkForValidCard() {

		checkExpired();

		if (membershipType == null || expired == true || hasCard == false) {
			System.out.println("No Library card found");
			return false;
		} else {
			System.out.println("Library card found");
			return true;
		}
	}

	/**
	 * 
	 * private method that does calculations for expiration date
	 * 
	 */
	private void checkExpired() {
		todayDate();

		if (this.currentDate - this.issuedDate > 3) {
			this.expired = true;
		}

	}

	/**
	 * Registers the person for a library card. Records the time/date of
	 * registration Checks whether person is adult or child
	 * 
	 * @param Person class instance
	 */

	public void register(Person type) {

		this.membershipType = type;
		this.name = type.getFirstName() + " " + type.getLastName();
		this.num = type.getPhoneNumber();
		this.holdAddress = type.getAddy();

		Calendar registered = Calendar.getInstance();
		this.issuedDate = registered.get(Calendar.YEAR);
		this.hasCard = true;

		int typeAge = this.getIssuedDate() - type.getBirthYear();
		if (typeAge >= 18)
			this.type = "Adult";
		else
			this.type = "Child";

		// new list
		personList.add(type);

	}

	// new method
	public List<Person> getPersonList() {

		return personList;

	}

	/**
	 * 
	 * records todays date
	 * 
	 */

	public void todayDate() {

		Calendar date = Calendar.getInstance();
		this.currentDate = date.get(Calendar.YEAR);
	}

	/**
	 * Gets issued date
	 * 
	 * @return date the library card was issued
	 */

	public int getIssuedDate() {

		return this.issuedDate;
	}

	/**
	 * 
	 * Displays all the information about the person who registered for a library
	 * card in a neat fashion
	 * 
	 */

	public void displayCardInfo() {

		System.out.println();
		System.out.println("--------------------------------------------------");
		System.out.printf("%24s \n\n", "Card Information");
		System.out.printf("%13s %3s %-17s \n", "Name:", " ", this.name);
		System.out.printf("%15s %1s %-14s \n", "Number:", " ", this.num);
		System.out.printf("%15s %1s %-6d \n", "Issued:", " ", getIssuedDate());
		System.out.printf("%17s %-4d \n", "Exp Date:", (getIssuedDate() + 3));
		System.out.printf("%13s %3s %-9s \n", "Type:", " ", this.type);
		System.out.printf("%17s %-6s \n", "Location:", this.holdAddress.getState());
		System.out.println("--------------------------------------------------");

//		System.out.println();
//		System.out.println("--------------------------------------------------");
//		System.out.printf("%24s \n\n", "Card Information");
//		System.out.printf("%13s %17s \n", "Name:", this.name);
//		System.out.printf("%15s %14s \n", "Number:", this.num);
//		System.out.printf("%15s %6d \n", "Issued:", getIssuedDate());
//		System.out.printf("%17s %4d \n", "Exp Date:", (getIssuedDate() + 3));
//		System.out.printf("%13s %9s \n", "Type:", this.type);
//		System.out.printf("%17s %6s \n", "Location:", this.holdAddress.getState());
//		System.out.println("--------------------------------------------------");
	}

	/**
	 * 
	 * Displays all the information about the person who registered for a library
	 * card in a neat fashion with person parameter to get specific information from
	 * another class
	 * 
	 */

	public void displayCardInfo(Person p) {

		Address myAdd = p.getAddy();

		System.out.println();
		System.out.println("--------------------------------------------------");
		System.out.printf("%24s \n\n", "Card Information");
		System.out.printf("%13s %3s %-17s \n", "Name:", " ", p.getFirstName() + " " + p.getLastName());
		System.out.printf("%15s %1s %-14s \n", "Number:", " ", p.getPhoneNumber());
		System.out.printf("%15s %1s %-6d \n", "Issued:", " ", getIssuedDate());
		System.out.printf("%17s %-4d \n", "Exp Date:", (getIssuedDate() + 3));
		System.out.printf("%13s %3s %-9s \n", "Type:", " ", this.type);
		System.out.printf("%17s %-6s \n", "Location:", myAdd.getState());
		System.out.println("--------------------------------------------------");

//		System.out.println();
//		System.out.println("--------------------------------------------------");
//		System.out.printf("%24s \n\n", "Card Information");
//		System.out.printf("%13s %17s \n", "Name:", p.getFirstName() + " " + p.getLastName());
//		System.out.printf("%15s %14s \n", "Number:", p.getPhoneNumber());
//		System.out.printf("%15s %6d \n", "Issued:", getIssuedDate());
//		System.out.printf("%17s %4d \n", "Exp Date:", (getIssuedDate() + 3));
//		System.out.printf("%13s %9s \n", "Type:", this.type);
//		System.out.printf("%17s %6s \n", "Location:", myAdd.getState());
//		System.out.println("--------------------------------------------------");
	}

	public static void main(String[] args) {
		Address myAddress = new Address("100", "Staten Island", "10301", "New York");
		Registration tester = new Registration();
		Person matt = new Person("Matthew", "Smith", 1999, myAddress);
		matt.setPhoneNumber("555-444-4534");
		tester.register(matt);
		tester.checkForValidCard();
		tester.displayCardInfo();

//		Person bill = new Person("bill", "Smith", 1999, myAddress);
//		matt.setPhoneNumber("000-000-0000");
//		tester.register(bill);
//
//		Registration tester1 = new Registration();
//		System.out.println(tester.getPersonList());
//		System.out.println(tester1.getPersonList());
	}

}