package project;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class BorrowFromLibrary {

	private boolean canBorrow = true;

	static final int MAX_SIZE = 3;

	// constructor
	public BorrowFromLibrary() {
	}

	/**
	 * method checks to see if user can borrow books and doesn't have fee calls init
	 * method with parameters
	 * 
	 * @param p1 Person pass person instance
	 * @param b  Book pass book instance
	 */

	public void borrowBook(Person p1, Book b) {

		if (this.canBorrow == true) {
			init(p1, b);
		}

	}
	// END of borrowBook

	/**
	 * 
	 * method checks to see if balance is == 0, if so the user can borrow book,
	 * otherwise user has to pay fee Searches through bookList and displays each
	 * book the user current rents out as well as the time it was taken out
	 * 
	 * 
	 * @param p1 person instance to get book List
	 * @param b  book instance to search for books
	 */

	public void init(Person p1, Book b) {

		if (p1.getLateBalance() == 0.0) {

			try {
				if (CSVHandler.getCSVQuantity(Book.BOOK_FILEPATH, b) > 0) {

					if (p1.getBookList().size() < MAX_SIZE) {

						try {
							CSVHandler.searchForBook(Book.BOOK_FILEPATH, b);

							p1.setBookList(b);

							System.out.println(p1.getFirstName() + " " + p1.getLastName() + " has checked out: "
									+ b.getTitle() + " by " + b.getAuthor());

							System.out.println("-----------------------------------------------------------");
							/* System.out.printf("|"); */ System.out.printf("|          %30s                 ",
									b.getTitle().toUpperCase(), "|");
							System.out.printf("|\n");
							/* System.out.printf("|"); */ System.out.printf("|          %30s                 ",
									b.getAuthor().toUpperCase(), "|");
							System.out.printf("|\n");
							/* System.out.printf("|"); */ System.out.printf("|          %30s                 ",
									b.getGenre().toUpperCase(), "|");
							System.out.printf("|\n");
							/* System.out.printf("|"); */ System.out.printf("|          %30s                 ",
									b.getPublisher().toUpperCase(), "|");
							System.out.printf("|\n");

							System.out.println("-----------------------------------------------------------");

							System.out.println("");
							System.out.printf("%27s %20s", "", "Current book List".toUpperCase() + "\n");
							Map<Book, java.util.Date> map = p1.getBookList();

							for (Entry<Book, java.util.Date> hold : p1.map.entrySet()) {

								Book c = hold.getKey();
								System.out.println(
										c.getTitle() + " by " + c.getAuthor() + " | Rented on: " + hold.getValue());

								;
							}

							System.out.println("");

						} catch (FileNotFoundException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}

					} else {
						System.out.println(
								"You have checked out the maximum amount of books allowed, please return a book before trying to rent a new book");
					}
				}

			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			this.canBorrow = false;
			System.out.println("Please pay your late fee's to borrow a new book");
		}

	}

	/** Sets the canBorrow boolean to true so that a Person object can borrow a book again.
	 * 
	 */
	public void clearBalance() {
		this.canBorrow = true;

	}
}// END OF init()
