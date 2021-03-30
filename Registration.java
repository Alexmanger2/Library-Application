package project;


import java.util.Calendar;

public class Registration {

	
	private Person membershipType;
	
//	private boolean validCard;
	private boolean hasCard = false;
	private boolean expired = false;
	
	private int issuedDate;
	private int currentDate;
	private String name;
	private String num;
	
	public Registration() {
		
	}
	

	public void checkCard() {
		
		checkExpired();
		
		if(membershipType == null || expired == true || hasCard == false ) {
	//		this.validCard = false;
			System.out.println("No Library card found");
		}
		else {
	//		this.validCard = true;
			System.out.println("Library card found");
		}
		
	}
	
	//private
	private void checkExpired() {   
		todayDate();
	
		if(this.currentDate - this.issuedDate > 3) {
			this.expired = true;
		//	System.out.println("Library card has expired");
		//	System.out.println(this.currentDate - this.issuedDate);
		}
	//	System.out.println("Library card has NOT expired");
	}
	
	public void register(Person type) {
		
		this.membershipType = type;
		this.name = type.getFirstName() + " " + type.getLastName();
		this.num = type.getNumber();
		
		Calendar registered = Calendar.getInstance();
		this.issuedDate = registered.get(Calendar.YEAR);
		this.hasCard = true;
		
		
		
//		cal1.set(Calendar.YEAR, 2020); 
//		this.iss = cal1.get(Calendar.YEAR);
		
		
	}
	
	public void todayDate() {
		
		Calendar date = Calendar.getInstance();
		this.currentDate = date.get(Calendar.YEAR);
	}

	public int getIssuedDate() {
		
		return this.issuedDate;
	}
	
	public void displayCardInfo() {
		System.out.println();
	//	System.out.println("********* Card Information *********");
		System.out.printf("%24s \n\n", "Card Information");
	//	System.out.println("Name: " + this.name);
		
		System.out.printf("%13s %17s \n", "Name:" , this.name);
	//	System.out.println("Number: " + this.num);
		System.out.printf("%15s %14s \n", "Number:" , this.num);
//		System.out.println("Date issued: " + getIssuedDate());
		System.out.printf("%15s %6d \n", "Issued:", getIssuedDate() );
//		System.out.println("Expiration date: " + (getIssuedDate() + 3) + "\n");
		System.out.printf("%17s %4d \n", "Exp Date:", (getIssuedDate() + 3) );
		
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Registration tester = new Registration();
		Person matt = new Person("Matthew", "Smith", 1999);
		matt.setNumber("555-444-4534");
		tester.register(matt);
		tester.checkCard();
		tester.displayCardInfo();
		
		
		
	

		
	}

}
