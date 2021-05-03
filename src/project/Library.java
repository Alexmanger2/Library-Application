
package project;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class Library {

	private boolean check = true;

	public Library() throws FileNotFoundException, IOException {
		// run();
		ask();
	}

	public void takeOut(Person p) throws FileNotFoundException, IOException {

		String title;
		String author;
		String genre;
		String publisher;

		// Book b = new Book("Data Smart", "Foreman, John", "data_science", "Wiley");

	//	System.out.println("Enter the Title/Author/Genre/Publisher");
		System.out.println("Enter the title of the book you want the rent");
		
		Scanner pickTitle = new Scanner(System.in);
		title = pickTitle.nextLine();

//		Scanner pickauthor = new Scanner(System.in);
//		author = pickauthor.nextLine();
//
//		Scanner pickgenre = new Scanner(System.in);
//		genre = pickgenre.nextLine();
//
//		Scanner pickpublisher = new Scanner(System.in);
//		publisher = pickpublisher.nextLine();

//		Book take = new Book(title, author, genre, publisher);
		
		//Book take;
//		try {
			Book take = CSVHandler.searchAndCheckoutBook(Book.BOOK_FILEPATH , title );
			Borrow checkout = new Borrow();
			checkout.borrowBook(p, take);
			
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

//		Borrow checkout = new Borrow();
//
//		checkout.borrowBook(p, take);

	}

	public void putBack(Person p) throws FileNotFoundException, IOException {

		Return checkin = new Return();
		//System.out.println("Choose what book to return");
		//System.out.println(p.getBookList().toString());
		
		if(p.getBookList().size() != 0) {
		System.out.println("Current books rented out: ");
		
		Map<Book, java.util.Date> map = p.getBookList();
		
	for (Entry<Book, java.util.Date> hold : p.map.entrySet()) {
			
//			if(p.getBookList().size() == 0) {
//				System.out.println("\n No book currently rented out \n");
//			}
			
			Book b = hold.getKey();
			System.out.println(b.getTitle());
			
			//+
		//	System.out.println(hold.getKey().getTitle() //+
	                             //", Value = " + hold.getValue());
	    }
	System.out.println("");
	String title;
	String author;
	String genre;
	String publisher;
			System.out.println("Enter the title of the book you want to return");
			Scanner pickTitle = new Scanner(System.in);
			title = pickTitle.nextLine();
			
			Book back = CSVHandler.getBookFromLib(Book.BOOK_FILEPATH , title );
			
			if(back.getTitle().equals("null") && !title.equals("")) {
				
				System.out.println("You did not checkout " + title + "\n");
			}
			else
			checkin.returnBook(p, back);
	
		}
		else {
			
			String title;
			System.out.println("You don't currently have any books rented out");
			title = "moveOn";
			
			Book back = CSVHandler.getBookFromLib(Book.BOOK_FILEPATH , title );
			
			if(back.getTitle().equals("null") && !title.equals("") || title.equals("moveOn")) {
				if(title.equals("moveOn")) {
					System.out.println("");
				}
				else
				System.out.println("You did not checkout " + title + "\n");
			}
			else
			checkin.returnBook(p, back);
		}
		
//		System.out.println("");
//		String title;
//		String author;
//		String genre;
//		String publisher;

		// Book b = new Book("Data Smart", "Foreman, John", "data_science", "Wiley");

	//	System.out.println("Enter the title of the book you want to return");

	//	Scanner pickTitle = new Scanner(System.in);
	//	title = pickTitle.nextLine();

//		Scanner pickauthor = new Scanner(System.in);
//		author = pickauthor.nextLine();
//
//		Scanner pickgenre = new Scanner(System.in);
//		genre = pickgenre.nextLine();
//
//		Scanner pickpublisher = new Scanner(System.in);
//		publisher = pickpublisher.nextLine();

//		Book back = new Book(title, author, genre, publisher);

	
//			try {
				
//				Book back = CSVHandler.getBookFromLib(Book.BOOK_FILEPATH , title );
//				
//				if(back.getTitle().equals("null") && !title.equals("")) {
//					
//					System.out.println("You did not checkout " + title + "\n");
//				}
//				else
//				checkin.returnBook(p, back);
		
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
		
//		checkin.returnBook(p, back);

	}

	public void yes(Registration reg) throws FileNotFoundException, IOException {
		// displays the current list of people
		// System.out.println(reg.getPersonList());

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
						  System.out.println("Please select an option: (1: borrow | 2: return | 3: Search for book | 4: switch user | 5: Exit) ");
						  Scanner ans = new Scanner(System.in);
						  answer = ans.nextLine();
						  
						  
						  if(answer.equals("1"))
						  takeOut(p);
						  if(answer.equals("2"))
					      putBack(p);
						  if(answer.equals("3")) {
							  	System.out.println("What book do you want to search for?");
								String bookTitle;
								Scanner bTitle = new Scanner(System.in);
								bookTitle = bTitle.nextLine();
								
							  if(CSVHandler.searchForBook(Book.BOOK_FILEPATH, bookTitle) == true) {
							  Book c = CSVHandler.getBookFromLib(Book.BOOK_FILEPATH, bookTitle);
							  int hold = CSVHandler.getCSVQuantity(Book.BOOK_FILEPATH, c);
							  System.out.println("There are " + hold + " copies remaining of " + bookTitle + "\n");
							  }
						  }
						  if(answer.equals("4"))
					      run(reg);
						  if(answer.equals("5")) {
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

	public void ask() throws FileNotFoundException, IOException {

		Registration register = new Registration();
		run(register);
	}
	
	public void no(Registration reg) throws FileNotFoundException, IOException
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
			  System.out.println("Please select an option: (1: borrow | 2: return | 3: Search for book | 4: switch user | 5: Exit) ");
			  Scanner ans = new Scanner(System.in);
			  answer = ans.nextLine();
			  
			  
			  if(answer.equals("1"))
				  takeOut(user);
				  if(answer.equals("2"))
			      putBack(user);
				  if(answer.equals("3")) {
					  	System.out.println("What book do you want to search for?");
						String bookTitle;
						Scanner bTitle = new Scanner(System.in);
						bookTitle = bTitle.nextLine();
						
					 if( CSVHandler.searchForBook(Book.BOOK_FILEPATH, bookTitle) == true) {
					  Book c = CSVHandler.getBookFromLib(Book.BOOK_FILEPATH, bookTitle);
					  int hold = CSVHandler.getCSVQuantity(Book.BOOK_FILEPATH, c);
					  System.out.println("There are " + hold + " copies remaining of " + bookTitle + "\n");
				  }}
				  if(answer.equals("4"))
			      run(reg);
				  if(answer.equals("5")) {
				  //break;
			      System.out.println("Exiting program.....");		  
				  System.exit(0);
				  }
			  
			  
		  }
		  
		  
	}

	public void run(Registration register) throws FileNotFoundException, IOException {

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

		// Registration register = new Registration();

		System.out.println("Do you have a library card?(yes/no): ");
		option = optionAnswer.nextLine();

		if (option.equalsIgnoreCase("yes")) {

			yes(register);

		}
//			  else {
//				  System.out.println("User was not found, try again");
//				  run();
//			  }

		else if (option.equalsIgnoreCase("no")) {
			no(register);
		} else {
			System.out.println("Wrong input for valid card, try again");
			run(register);
		}

	}

	public static void main(String[] args) throws FileNotFoundException, IOException {

		Library lib = new Library();

	}

}