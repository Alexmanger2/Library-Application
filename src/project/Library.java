
package project;

import java.util.Scanner;


public class Library {

	private boolean check = true;
	public Library() {
		//run();
		ask();
	}
	
	
	public void takeOut(Person p) {
		
		String title;
		String author;
		String genre;
		String publisher;
		
		//Book b = new Book("Data Smart", "Foreman, John", "data_science", "Wiley");
		
		System.out.println("Enter the Title/Author/Genre/Publisher");
		
		Scanner pickTitle = new Scanner(System.in);
		title = pickTitle.nextLine();
		
		Scanner pickauthor = new Scanner(System.in);
		author = pickauthor .nextLine();
		
		Scanner pickgenre= new Scanner(System.in);
		genre = pickgenre.nextLine();
		
		Scanner pickpublisher = new Scanner(System.in);
		publisher = pickpublisher.nextLine();
		
			Book take = new Book(title,author,genre,publisher);
			
			Borrow checkout = new Borrow();
			
	        checkout.borrowBook(p,take);

		
	}
	
	public void putBack(Person p) {
		
		Return checkin = new Return();
		System.out.println("Choose what book to return");
		System.out.println(p.getBookList().toString());
		System.out.println("");
		String title;
		String author;
		String genre;
		String publisher;
		
		//Book b = new Book("Data Smart", "Foreman, John", "data_science", "Wiley");
		
		System.out.println("Enter the Title/Author/Genre/Publisher");
		
		Scanner pickTitle = new Scanner(System.in);
		title = pickTitle.nextLine();
		
		Scanner pickauthor = new Scanner(System.in);
		author = pickauthor .nextLine();
		
		Scanner pickgenre= new Scanner(System.in);
		genre = pickgenre.nextLine();
		
		Scanner pickpublisher = new Scanner(System.in);
		publisher = pickpublisher.nextLine();
		
		
		Book back = new Book(title,author,genre,publisher);
		
		
		checkin.returnBook(p, back);
		
		
		
	}
	
	
	
	public void yes(Registration reg)
	{
		//displays the current list of people
		//  System.out.println(reg.getPersonList());
		  
		  System.out.println("What is your phone number?");
		  String phoneNumber;
		  Scanner pNum = new Scanner(System.in);
		  phoneNumber = pNum.nextLine();
		  
		  
//		  
//		  if(reg.checkForValidCard() == true) {
//			  reg.displayCardInfo();
//			  System.out.println("");
			
		  
		  
			  for(Person element :  reg.getPersonList()) {
				 if( element.getPhoneNumber().equals( phoneNumber) ) {
					 
				//	 System.out.println("This is the phone" + element + "This is the phone number" + phoneNumber);
					 
					 Person p = element;
					 

					  if(reg.checkForValidCard() == true) {
						  reg.displayCardInfo(p);
						  System.out.println("");	
				
					  while(true) {
						  String answer = "";  
						  System.out.println("Do you want to take out or return a book?(1: borrow | 2: return | 3: switch user | 4: Exit) ");
						  Scanner ans = new Scanner(System.in);
						  answer = ans.nextLine();
						  
						  
						  if(answer.equals("1"))
						  takeOut(p);
						  if(answer.equals("2"))
					      putBack(p);
						  if(answer.equals("3"))
					      run(reg);
						  if(answer.equals("4")) {
						  //break;
					      System.out.println("Exiting program.....");		  
						  System.exit(0);
						  }
					  }
					 
					 
					 	  
						  
				 }
					  
				  
				  
			 // }
			 
			  
			 
			  
			  
			   
	}}
			  System.out.println("Phone number not found, please try again");
			  ask();  
	
	}
	
	
	public void ask() {
		
		Registration register = new Registration();
		run(register);
	}
	
	public void no(Registration reg)
	{
		
		
		  Scanner firstName = new Scanner(System.in);
		  Scanner lastName = new Scanner(System.in);
		  Scanner birthday = new Scanner(System.in);
		  Scanner number = new Scanner(System.in);
		  Scanner streetName = new Scanner(System.in);
		  Scanner userCity = new Scanner(System.in);
		  Scanner userState = new Scanner(System.in);
		  Scanner phoneNum = new Scanner(System.in);
		  Scanner optionAnswer = new Scanner(System.in);
		  
		  String first;
		  String last;
		  String birth;
		  String num;
		  String street;
		  String city;
		  String state;
		  String phone;
		  String option;
		  
		  
		  System.out.println("Enter your first name: "); 
		  first = firstName.nextLine();   
		  
		  System.out.println("Enter your last name: "); 
		  last = lastName.nextLine();  
		  
		  System.out.println("Enter your birth year: "); 
		  birth = birthday.nextLine(); 
		       
		  System.out.println("Enter your street number: "); 
		  num = number.nextLine();
		  System.out.println("Enter your street name: "); 
		  street = streetName.nextLine();
		  System.out.println("Enter your city: "); 
		  city = userCity.nextLine();
		  System.out.println("Enter your state: "); 
		  state = userState.nextLine();
		  
		  
		  Address userAddress = new Address(num,street,city,state);
		  Person user = new Person(first,last,Integer.parseInt(birth),userAddress);
		  
		  System.out.println("Enter your phone number seperated by '-': "); 
		  phone = phoneNum.nextLine();
		  user.setPhoneNumber(phone);
		  
		  reg.register(user);
		  System.out.println("Thank you for registering, here is your card");
		  reg.displayCardInfo();
		  System.out.println("");
		  
		  //checks to see what users are in the list
		//  System.out.println(reg.getPersonList().toString());
		  
		  
		  while(true) {
			  String answer = "";  
			  System.out.println("Do you want to take out or return a book?(1: borrow | 2: return | 3: switch user | 4: Exit) ");
			  Scanner ans = new Scanner(System.in);
			  answer = ans.nextLine();
			  
			  
			  if(answer.equals("1"))
			  takeOut(user);
			  if(answer.equals("2"))
		      putBack(user);
			  if(answer.equals("3"))
		      run(reg);
			  if(answer.equals("4")) {
				//break;
				  System.out.println("Exiting program.....");		  
				  System.exit(0);
			  }
			  
			  
		  }
		  
		  
	}
	
	public void run(Registration register) {
			
		
		  Scanner firstName = new Scanner(System.in);
		  Scanner lastName = new Scanner(System.in);
		  Scanner birthday = new Scanner(System.in);
		  Scanner number = new Scanner(System.in);
		  Scanner streetName = new Scanner(System.in);
		  Scanner userCity = new Scanner(System.in);
		  Scanner userState = new Scanner(System.in);
		  Scanner phoneNum = new Scanner(System.in);
		  Scanner optionAnswer = new Scanner(System.in);
		  
		  String first;
		  String last;
		  String birth;
		  String num;
		  String street;
		  String city;
		  String state;
		  String phone;
		  String option;
		  
		  //Registration register = new Registration();
			
		  System.out.println("Do you have a library card?(yes/no): "); 
		  option = optionAnswer.nextLine();   
		  
		  
		  if(option.equalsIgnoreCase("yes")) {
			 
			  yes(register);
			  
			  }
//			  else {
//				  System.out.println("User was not found, try again");
//				  run();
//			  }
			  
		  
		  else if(option.equalsIgnoreCase("no")){
			  	no(register);
		  }
		  else {
			  System.out.println("Wrong input for valid card, try again");
			  run(register);
		  }
		
		
	}
	

	
	public static void main(String[] args) {

		Library lib = new Library();
		
	}

}