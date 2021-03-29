package project;


import java.util.Calendar;

public class Registration {

	
	private String membershipType;
	
	private boolean validCard;
	private boolean hasCard = false;
	private boolean expired = false;
	
	private int issuedDate;
	private int currentDate;
	
	
	
	public Registration() {
		// TODO Auto-generated constructor stub
	}
	

	public void checkCard() {
		
		checkExpired();
		
		if(membershipType == null || expired == true || hasCard == false ) {
			this.validCard = false;
			System.out.println("No Library card found");
		}
		else {
			this.validCard = true;
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
	
	public void register() {
	
		Calendar cal1 = Calendar.getInstance();
		this.issuedDate = cal1.get(Calendar.YEAR);
		this.hasCard = true;
		
		
		
//		cal1.set(Calendar.YEAR, 2020); 
//		this.iss = cal1.get(Calendar.YEAR);
		
		
	}
	
	public void todayDate() {
		
		Calendar cal = Calendar.getInstance();
		this.currentDate = cal.get(Calendar.YEAR);
	}

	public int getIssuedDate() {
		
		return this.issuedDate;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Registration tester = new Registration();
		
		//tester.register();
		tester.checkCard();
		
		
		
	

		
	}

}
