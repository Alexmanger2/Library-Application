package project;

import java.util.Scanner;


public class Library {

	
	public Library() {
		run();
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
	
	
	public void run() {
			
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
		  
		  
		  
		  Registration reg = new Registration();
			
		  System.out.println("Do you have a library card?(yes/no): "); 
		  option = optionAnswer.nextLine();   
		  
		  
		  if(option.equalsIgnoreCase("yes")) {
			  
			 //needs to get info from already created user and use that prev user with registration instance...
			 // check "no" option to see how its done. Probably need to ask the first/last name and then use gets methods to set 
			  if(reg.checkForValidCard() == true) {
				  reg.displayCardInfo();
				  System.out.println("");
				  
			
				  
				 //-------   can't uncomment till above lines are fixed	  --------
				  
//				  while(true) {
//					  String answer = "";  
//					  System.out.println("Do you want to take out or return a book?(1: borrow | 2: return | 3: switch user | 4: Exit) ");
//					  Scanner ans = new Scanner(System.in);
//					  answer = ans.nextLine();
//					  
//					  
//					  if(answer.equals("1"))
//					  takeOut(user);
//					  if(answer.equals("2"))
//				      putBack(user);
//					  if(answer.equals("3"))
//				      run();
//					  if(answer.equals("4"))
//					  break;
//					  
//				  }
				  
				  
			  }
			  else {
				  System.out.println("User was not found, try again");
				  run();
			  }
			  
		  }
		  else if(option.equalsIgnoreCase("no")){
			  
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
			      run();
				  if(answer.equals("4"))
				  break;
				  
			  }
			  
			  
		  }
		  else {
			  System.out.println("Wrong input for valid card, try again");
			  run();
		  }
		
		
	}
	

	
	public static void main(String[] args) {

		Library lib = new Library();
		
	}

}
