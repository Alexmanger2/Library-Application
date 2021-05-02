package project;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Borrow {

	private boolean canBorrow = true;
//	public static int bookCount = 0;

	static final int MAX_SIZE = 3;

	public Borrow() {
		// TODO Auto-generated constructor stub
	}

	// --------------book status (checks to see if book is available or taken out
	// and for how long)---------------
	public void borrowBook(Person p1, Book b) {

		// try {

		// if(CSVHandler.searchForBook(Book.filePath,b)) {

		// Needs to check if quantity is > 0 and when the book will be available
		// again...
		if (this.canBorrow == true) {
			init(p1, b);
		}

	}

//		}

//		}
//		catch(FileNotFoundException ex) {
//			ex.printStackTrace();
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	// updateQuantity
	// getCSVQuantity
	// searchForBook
	// public static boolean updateQuantity(String filePath, Book book, boolean
	// incTrueDecFalse)
	public void init(Person p1, Book b) {

		if (p1.getLateBalance() == 0.0) {
			
					//if(CSVHandler.getCSVQuantity(Book.filePath, b) < 0) 
			try {
				if (CSVHandler.getCSVQuantity(Book.filePath, b) > 0) {

					if (p1.getBookList().size() < MAX_SIZE) {

						try {
							CSVHandler.searchForBook(Book.filePath, b);
							CSVHandler.updateQuantity(Book.filePath, b, false);

							// ++bookCount;
							p1.setBookList(b);

							System.out.println(p1.getFirstName() + " " + p1.getLastName() + " has checked out: "
									+ b.getTitle() + " by " + b.getAuthor());

							System.out.println(p1.getBookList() + "\n");
							
							System.out.println("-----------------------------------------------------------");
				/*System.out.printf("|");*/		    System.out.printf("|          %30s                 ", b.getTitle().toUpperCase(),"|");		System.out.printf("|\n");
				/*System.out.printf("|");*/			System.out.printf("|          %30s                 ", b.getAuthor().toUpperCase(), "|");	System.out.printf("|\n");
				/*System.out.printf("|");*/			System.out.printf("|          %30s                 ", b.getGenre().toUpperCase(), "|");		System.out.printf("|\n");
				/*System.out.printf("|");*/			System.out.printf("|          %30s                 ", b.getPublisher().toUpperCase(), "|");	System.out.printf("|\n");
				
							System.out.println("-----------------------------------------------------------");

						} catch (FileNotFoundException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
			
					} else {
						System.out.println(
								"You have checked out the maximum amount of books allowed, please return a book before trying to rent a new book");
					}
				}// new
				else {
					if(b.getTitle().equals(" ")) {
						System.out.println("You did not enter a book, try again");
					}else
					System.out.println("This book is currently out of stock, please rent another book");
				}
	
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			this.canBorrow = false;
			System.out.println("Please pay your late fee's to borrow a new book");
		}

	}

	public void clearBalance() {
		this.canBorrow = true;

	}

//	public static void BookReturned()
//	{
//		
//		bookCount = bookCount - 1;
//	}

}
