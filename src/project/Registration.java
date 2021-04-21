package project;


import java.util.Calendar;

public class Registration {

	
	private Person membershipType; //type of member
	
	private boolean hasCard = false;  //valid card or not
	private boolean expired = false; //expiration date
	
	private int issuedDate;  //date card was issued
	private int currentDate; // todays date
	
	private String name; // name of person
	private String num;  // phone number
	private String type; // child/adult
	
	
	public Registration() {
		
	}
	
	/**
	 * Checks to see if library card has expired or not
	 * 
	 * */
	public void checkCard() {
		
		checkExpired();
		
		if(membershipType == null || expired == true || hasCard == false ) 
			System.out.println("No Library card found");
		else 
			System.out.println("Library card found");
		
		
	}
	
	/**
	 * 
	 * private method that does calculations for expiration date
	 * 
	 * */
	private void checkExpired() {   
		todayDate();

		if(this.currentDate - this.issuedDate > 3) {
			this.expired = true;
		}
	
	}
	
	/**
	 * Registers the person for a library card. 
	 * Records the time/date of registration
	 * Checks whether person is adult or child
	 *	
	 * @param Person class instance
	 * */
	
	public void register(Person type) {
		
		this.membershipType = type;
		this.name = type.getFirstName() + " " + type.getLastName();
		this.num = type.getPhoneNumber();
		
		Calendar registered = Calendar.getInstance();
		this.issuedDate = registered.get(Calendar.YEAR);
		this.hasCard = true;
		
		int typeAge = this.getIssuedDate() - type.getBirthYear();
		if(typeAge >= 18)
			this.type = "Adult";
		else
			this.type = "Child";
		
	}
	
	
	/**
	 * 
	 *  records todays date
	 * 
	 * */
	
	public void todayDate() {
		
		Calendar date = Calendar.getInstance();
		this.currentDate = date.get(Calendar.YEAR);
	}

	
	/**
	 * Gets issued date
	 * @return date the library card was issued
	 * */
	
	public int getIssuedDate() {
		
		return this.issuedDate;
	}
	
	
	/**
	 * 
	 * Displays all the information about the person who registered
	 * for a library card in a neat fashion
	 * 
	 * */
	public void displayCardInfo() {
		
		System.out.println();
		System.out.printf("%24s \n\n", "Card Information");
		System.out.printf("%13s %17s \n", "Name:" , this.name);
		System.out.printf("%15s %14s \n", "Number:" , this.num);
		System.out.printf("%15s %6d \n", "Issued:", getIssuedDate() );
		System.out.printf("%17s %4d \n", "Exp Date:", (getIssuedDate() + 3) );
		System.out.printf("%13s %9s \n", "Type:", this.type );
	}
	
	
	public static void main(String[] args) {
	
		Registration tester = new Registration();
		Person matt = new Person("Matthew", "Smith", 1999);
		matt.setPhoneNumber("555-444-4534");
		tester.register(matt);
		tester.checkCard();
		tester.displayCardInfo();
		
		
		
		
	}

}