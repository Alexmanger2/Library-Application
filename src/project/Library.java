
package project;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class Library {


	/*
	 * 	Library constructor runs the ask method
	 * 
	 * @throws IOException           if the named file exists but is a directory
	 *                               rather than a regular file, does not exist but
	 *                               cannot be created, or cannot be opened for any
	 *                               other reason
	 * @throws FileNotFoundException if the named file does not exist,is a directory
	 *                               rather than a regular file,or for some other
	 *                               reason cannot be opened for reading.
	 * 
	 * */
	
	public Library() throws FileNotFoundException, IOException {
		// run();
		ask();
	}
	//END OF library()
	
	/**
	 * Method checks to see if the user has books rented out. If the user does, they will be returned
	 * by decrementing the Persons bookList and incrementing the quantity of the book in the database.
	 * 
	 * @param p Person instance passed into method
	 * 
	 * @throws IOException           if the named file exists but is a directory
	 *                               rather than a regular file, does not exist but
	 *                               cannot be created, or cannot be opened for any
	 *                               other reason
	 * @throws FileNotFoundException if the named file does not exist,is a directory
	 *                               rather than a regular file,or for some other
	 *                               reason cannot be opened for reading.
	 */
	
	public void takeOut(Person p) throws FileNotFoundException, IOException {

		String title;
		String author;
		String genre;
		String publisher;


		if (p.getBookList().size() == 3) {
			System.out.println(
					"You have checked out the maximum amount of books allowed, please return a book before trying to rent a new book \n");
		} else {

			System.out.println("Enter the title of the book you want the rent");

			Scanner pickTitle = new Scanner(System.in);
			title = pickTitle.nextLine();

			Book take = CSVHandler.searchAndCheckoutBook(Book.BOOK_FILEPATH, title, false); // changed***
			Borrow checkout = new Borrow();
			checkout.borrowBook(p, take);
		}

	}//END OF takeOUT

	/**
	 * Method checks to see if the user has books rented out. If the user does, they will be returned
	 * by decrementing the Persons bookList and incrementing the quantity of the book in the database.
	 * 
	 * @param p Person instance passed into method
	 * 
	 * @throws IOException           if the named file exists but is a directory
	 *                               rather than a regular file, does not exist but
	 *                               cannot be created, or cannot be opened for any
	 *                               other reason
	 * @throws FileNotFoundException if the named file does not exist,is a directory
	 *                               rather than a regular file,or for some other
	 *                               reason cannot be opened for reading.
	 */
	
	public void putBack(Person p) throws FileNotFoundException, IOException {

		Return checkin = new Return();

		if (p.getBookList().size() != 0) {
			System.out.println("Current books rented out: ");

			Map<Book, java.util.Date> map = p.getBookList();

			for (Entry<Book, java.util.Date> hold : p.map.entrySet()) {


				Book b = hold.getKey();
				System.out.println(b.getTitle());

			}
			System.out.println("");
			String title;
			String author;
			String genre;
			String publisher;
			System.out.println("Enter the title of the book you want to return");
			Scanner pickTitle = new Scanner(System.in);
			title = pickTitle.nextLine();

			Book back = CSVHandler.getBookFromLib(Book.BOOK_FILEPATH, title);

			if (back.getTitle().equals("null") && !title.equals("")) {

				System.out.println("You did not checkout " + title + "\n");
			} else
				checkin.returnBook(p, back);

		} else {

			String title;
			System.out.println("You don't currently have any books rented out");
			title = "moveOn";

			Book back = CSVHandler.getBookFromLib(Book.BOOK_FILEPATH, title);

			if (back.getTitle().equals("null") && !title.equals("") || title.equals("moveOn")) {
				if (title.equals("moveOn")) {
					System.out.println("");
				} else
					System.out.println("You did not checkout " + title + "\n");
			} else
				checkin.returnBook(p, back);
		}

	}//END OF putBACK()

	
	/**
	 * Method used for when user has a library card. The user must provide their phone number
	 * to access the features of the library
	 * 
	 * @param reg Registration instance passed into method
	 * 
	 * @throws IOException           if the named file exists but is a directory
	 *                               rather than a regular file, does not exist but
	 *                               cannot be created, or cannot be opened for any
	 *                               other reason
	 * @throws FileNotFoundException if the named file does not exist,is a directory
	 *                               rather than a regular file,or for some other
	 *                               reason cannot be opened for reading.
	 */
	public void yes(Registration reg) throws FileNotFoundException, IOException {
	

		System.out.println("What is your phone number?");
		String phoneNumber;
		Scanner pNum = new Scanner(System.in);
		phoneNumber = pNum.nextLine();



		for (Person element : reg.getPersonList()) {
			if (element.getPhoneNumber().equals(phoneNumber)) {

				

				Person p = element;

				if (reg.checkForValidCard() == true) {
					reg.displayCardInfo(p);
					System.out.println("");

					while (true) {
						String answer = "";
						System.out.println(
								"Please select an option: (1: borrow | 2: return | 3: Search for book | 4: switch user | 5: Exit) ");
						Scanner ans = new Scanner(System.in);
						answer = ans.nextLine();

						if (answer.equals("1"))
							takeOut(p);
						if (answer.equals("2"))
							putBack(p);
						if (answer.equals("3")) {
							System.out.println("What book do you want to search for?");
							String bookTitle;
							Scanner bTitle = new Scanner(System.in);
							bookTitle = bTitle.nextLine();

							if (CSVHandler.searchForBook(Book.BOOK_FILEPATH, bookTitle) == true) {
								Book c = CSVHandler.getBookFromLib(Book.BOOK_FILEPATH, bookTitle);
								int hold = CSVHandler.getCSVQuantity(Book.BOOK_FILEPATH, c);
								System.out.println("There are " + hold + " copies remaining of " + bookTitle + "\n");
							}
						}
						if (answer.equals("4"))
							run(reg);
						if (answer.equals("5")) {
							// break;
							System.out.println("Exiting program.....");
							System.exit(0);
						}
					}

				}


			}
		}
		System.out.println("Phone number not found, please try again");
		ask();

	}//END OF YES()

	
	
	/*
	 * 	method creates Registration instance that will be used for all future users to access
	 * registration info for each user. Calls the run method with this instance that will give the user
	 * the ability to have access to future library functionality.
	 * 
	 * @throws IOException           if the named file exists but is a directory
	 *                               rather than a regular file, does not exist but
	 *                               cannot be created, or cannot be opened for any
	 *                               other reason
	 * @throws FileNotFoundException if the named file does not exist,is a directory
	 *                               rather than a regular file,or for some other
	 *                               reason cannot be opened for reading.
	 * 
	 * */
	
	public void ask() throws FileNotFoundException, IOException {

		Registration register = new Registration();
		run(register);
	}
	//END OF ask()
	
	
	/**
	 * Method used for when user does not have a library card. Registers the user for a 
	 * library card and allows them to access the library features
	 * 
	 * @param reg Registration instance passed into method
	 * 
	 * @throws IOException           if the named file exists but is a directory
	 *                               rather than a regular file, does not exist but
	 *                               cannot be created, or cannot be opened for any
	 *                               other reason
	 * @throws FileNotFoundException if the named file does not exist,is a directory
	 *                               rather than a regular file,or for some other
	 *                               reason cannot be opened for reading.
	 */
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
		 // birth = birthday.nextLine(); 
		  
		 //make sure birthday is an integer value
		  while(!birthday.hasNextInt()) {
			  System.out.println("Please enter an number for birth year "); 
			  birth = birthday.nextLine(); 

			  
		  }
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
		// System.out.println(reg.getPersonList().toString());
		  
		  
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
		  
		  
	}//END OF NO
	
	
	/**
	 * Method checks whether or not the user has a library card
	 * 
	 * @param reg Registration instance passed into method
	 * 
	 * @throws IOException           if the named file exists but is a directory
	 *                               rather than a regular file, does not exist but
	 *                               cannot be created, or cannot be opened for any
	 *                               other reason
	 * @throws FileNotFoundException if the named file does not exist,is a directory
	 *                               rather than a regular file,or for some other
	 *                               reason cannot be opened for reading.
	 */

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
		 

		else if (option.equalsIgnoreCase("no")) {
			no(register);
		} else {
			System.out.println("Wrong input for valid card, try again");
			run(register);
		}

	}//END OF RUN()

	public static void main(String[] args) throws FileNotFoundException, IOException {

		Library lib = new Library();

	}

}