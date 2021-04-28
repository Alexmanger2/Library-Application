package project;

public class Library {

	public Library() {
	}

	public static void main(String[] args) {
		
		
	
		Address myAddress = new Address("100", "Smith", "Staten Island", "New York");
		String filePath = "src/books.csv";
		Person bobby = new Person("Bob", "Smith", 1875, myAddress);
		Return checkin = new Return();
		
		Book b = new Book("Data Smart", "Foreman, John", "data_science", "Wiley");
        Book b2 = new Book("Signal and the Noise, The", "Silver, Nate", "data_science", "Penguin");
        Book c = new Book("Trial, The", "Kafka, Frank", "fiction", "Random House");
        Book c2 = new Book("Orientalism", "Said, Edward", "history", "Penguin");
        
 
		Borrow checkout = new Borrow();
		
		checkout.borrowBook(bobby,b);
	//	checkout.borrowBook(bobby,b2);
	//	checkout.borrowBook(bobby,c);
	
		//	System.out.println(bobby.getBookList().size());

	//	bobby.getBookList().clear();
	//	checkin.returnBook(bobby , b2);
	//	checkout.borrowBook(bobby,c2);
	//	checkout.borrowBook(bobby , c);
		
	
		
	//	checkin.returnBook(bobby , b);

//		checkin.returnBook(bobby , b2);
//		checkout.borrowBook(bobby,c2);
//		checkout.borrowBook(bobby , c);
//
//		checkin.returnBook(bobby , b);

		
//		System.out.println(bobby.getBookList());
		
	}

}
