
package project;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class Return {

	boolean remove;
	int count = 0;
	boolean check = true;

	public Return() {

	}

	public void returnBook(Person p1, Book b) {

		Book rBook = new Book();
		Book holdBook = b;
		for (Map.Entry<Book, Date> date : p1.map.entrySet()) {

			if (date.getKey().equals(b)) {

				rBook = b;
				this.remove = true;
				this.check = false;

				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.MONTH, -3);
				if (cal.getTime().compareTo(date.getValue()) > 0) {
					p1.setLateBalance(1.0);
					System.out.println("\n" + "You returned the book" + rBook
							+ " late, fee has been added to account".toUpperCase());
					System.out.println("");

				}
				if (cal.getTime().compareTo(date.getValue()) == 0) {

					p1.setLateBalance(0.0);
					System.out.println("\n" + "You returned " + rBook + " on time".toUpperCase());

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
				}
				if (cal.getTime().compareTo(date.getValue()) < 0) {
					p1.setLateBalance(0.0);
					System.out.println("\n" + "You returned " + rBook + " on time".toUpperCase());

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
				}

			}

		}

		if (this.check == true) {
			System.out.println("You did not checkout this book: " + holdBook.getTitle().toUpperCase());
			System.out.println("");
		}
		this.check = true;

		if (this.remove == true) {
			p1.map.remove(rBook);
			// Borrow.BookReturned();
			try {

				CSVHandler.updateQuantity(Book.BOOK_FILEPATH, b, true);
				rBook = new Book();
				this.remove = false;

			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}