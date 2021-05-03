package project;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
//import java.io.FileNotFoundException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

public class CSVHandler {
	private final static String DELIMITER = ",";

	/**
	 * Writes book objects into a CSV or appends into an existing CSV.
	 * 
	 * @param filePath String the location of the CSV
	 * @param append   boolean true = append to CSV mode
	 * @param book     Book object that is being written to CSV
	 * @throws IOException if the named file exists but is a directory rather than a
	 *                     regular file, does not exist but cannot be created, or
	 *                     cannot be opened for any other reason
	 */
	public static void write(String filePath, boolean append, Book book) throws IOException { // true = append mode
		try (CSVPrinter printer = new CSVPrinter(new FileWriter(filePath, append), CSVFormat.EXCEL)) {
			printer.print(book.getTitle());
			printer.print(book.getAuthor());
			printer.print(book.getGenre());
			printer.print(book.getPublisher());
			// If we're not appending to CSV, we are writing the header and therefore want
			// the String "Quantity" printed.
			if (!append)
				printer.print(book.getStringQuantity());
			// If we're appending to CSV, we want the actual quantity of the book being
			// printed.
			else
				printer.print(book.getIntQuantity());

			printer.println();

			printer.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Displays a .csv file
	 * 
	 * @param filePath String filepath of the location of
	 * @throws IOException if the named file exists but is a directory rather than a
	 *                     regular file, does not exist but cannot be created, or
	 *                     cannot be opened for any other reason
	 */
	public static void displayCSV(String filePath) throws IOException {
		Reader csvData = new FileReader(filePath);
		CSVParser parser = CSVParser.parse(csvData, CSVFormat.EXCEL.withFirstRecordAsHeader());
		for (CSVRecord record : parser) {
			String title = record.get("Title");
			String author = record.get("Author");
			String genre = record.get("Genre");
			String publisher = record.get("Publisher");

			Book b = new Book(title, author, genre, publisher);
			String bCSV = b.toCSV();
			System.out.println(bCSV);
		}
		csvData.close();
		parser.close();
	}

	/**
	 * Manually add a book to the list
	 * 
	 * @param filePath String filepath of the .csv file to add the book to.
	 * @param book     Book to add to the .csv database.
	 * @throws IOException if the named file exists but is a directory rather than a
	 *                     regular file, does not exist but cannot be created, or
	 *                     cannot be opened for any other reason
	 */
	public static void addNewBook(String filePath, Book book) throws IOException {
		write(filePath, true, book);
		System.out.println("Added to CSV: " + book);
	}

	/**
	 * Removes a book from the .csv book database
	 *
	 * 
	 * @param filePath String the filepath of the location of the .csv
	 * @param book     Book the book to be removed from the .csv
	 * @throws IOException if the named file exists but is a directory rather than a
	 *                     regular file, does not exist but cannot be created, or
	 *                     cannot be opened for any other reason
	 */
	public static void removeBook(String filePath, Book book) throws IOException {
		List<Book> books = new ArrayList<Book>();

		Reader csvData = new FileReader(filePath);
		CSVParser parser = CSVParser.parse(csvData, CSVFormat.EXCEL.withFirstRecordAsHeader());
		for (CSVRecord record : parser) {
			// needs to be changed to some how work for any files records not just books.csv
			String title = record.get("Title");
			String author = record.get("Author");
			String genre = record.get("Genre");
			String publisher = record.get("Publisher");

			Book b = new Book(title, author, genre, publisher);
			if (!b.equals(book)) {
				books.add(b);
			}
			// else ignore so this book effectively gets removed from books list
			// and effectively the file
		}
		csvData.close();
		parser.close();

		// create a new empty books.csv to be populated with the books data
		CSVPrinter newBooksCSV = new CSVPrinter(new FileWriter(filePath), CSVFormat.EXCEL);

		// add header to top of csv
		// This could be done better
		Book header = new Book("Title", "Author", "Genre", "Publisher", "Quantity");

		write(filePath, false, header);
		for (Book b : books) {
			write(filePath, true, b);
		}
		newBooksCSV.close();
	}

	/**
	 * Getter for the quantity field of a CSV
	 * 
	 * @param filePath String filepath of the CSV.
	 * @param book     Book object to retrieve the quantity from within the CSV for.
	 * @return int An integer representing the stock left of the book.
	 * @throws IOException if the named file exists but is a directory rather than a
	 *                     regular file, does not exist but cannot be created, or
	 *                     cannot be opened for any other reason
	 */
	public static int getCSVQuantity(String filePath, Book book) throws IOException {
		Reader csvData = new FileReader(filePath);
		CSVParser parser = CSVParser.parse(csvData, CSVFormat.EXCEL.withFirstRecordAsHeader());
		// System.out.println("Searching for " + book.getTitle() + " by Author " +
		// book.getAuthor() + "...");
		for (CSVRecord record : parser) {
			String title = record.get("Title");
			String author = record.get("Author");
			String genre = record.get("Genre");
			String publisher = record.get("Publisher");
			String tempQuantity = record.get("Quantity");

			int quantity = Integer.parseInt(tempQuantity);

			Book b = new Book(title, author, genre, publisher, quantity);

			if (b.equals(book)) {
				csvData.close();
				return quantity;
			}
		} // END FOR LOOP
	//do something about this
	//	System.out.println("Book not found!");
		
		csvData.close();
		parser.close();

		return -1;

	}

	/**
	 * Updates the quantity depending on the passed boolean parameter. True for
	 * increase False for decrement (as long as current quantity is above zero).
	 * 
	 * @param filePath        String Location of the .csv file.
	 * @param book            Book object to update the quantity of.
	 * @param incTrueDecFalse boolean Pass true to increment quantity, and
	 *                        vice-versa
	 * @throws IOException              if the named file exists but is a directory
	 *                                  rather than a regular file, does not exist
	 *                                  but cannot be created, or cannot be opened
	 *                                  for any other reason
	 * @throws IllegalArgumentException If false is passed for inTrueDecFalse and
	 *                                  the current quantity is 0
	 * @return boolean True if update was successful, false if it was not.
	 */
	public static boolean updateQuantity(String filePath, Book book, boolean incTrueDecFalse)
			throws IOException, IllegalArgumentException {
		boolean success = false;
		List<Book> books = new ArrayList<Book>();

		Reader csvData = new FileReader(filePath);
		CSVParser parser = CSVParser.parse(csvData, CSVFormat.EXCEL.withFirstRecordAsHeader());
		for (CSVRecord record : parser) {
			String title = record.get("Title");
			String author = record.get("Author");
			String genre = record.get("Genre");
			String publisher = record.get("Publisher");
			String tempQuantity = record.get("Quantity");

			int quantity = Integer.parseInt(tempQuantity);

			Book b = new Book(title, author, genre, publisher, quantity);

			// If not the book we're looking to update then just add it to the List.
			if (!b.equals(book)) {
				books.add(b);
			}
			// else, its the book we want to increment or decrement the quantity of.
			else {
				try {
					if (incTrueDecFalse == true) {
						b.setIntQuantity(b.getIntQuantity() + 1);
//						System.out.println(
//								"Book " + b.getTitle() + ", stock has been incremented to " + b.getIntQuantity());
						success = true;
					} else if (!incTrueDecFalse && b.getIntQuantity() >= 1) {
						b.setIntQuantity(b.getIntQuantity() - 1);
//						System.out.println(
//								"Book " + b.getTitle() + ", stock has been decremented to " + b.getIntQuantity());
						success = true;
					} else
//						System.out.println("Can not checkout:\n" + b.getTitle() + " is out of stock!\n");
						throw new IllegalArgumentException(
								"Can not checkout:\n" + b.getTitle() + " is out of stock!\n");
				} catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
				}
				books.add(b);
			}

		} // end FOR each loop
		csvData.close();
		parser.close();

		// create a new empty books.csv to be populated with the books data
		CSVPrinter newBooksCSV = new CSVPrinter(new FileWriter(filePath), CSVFormat.EXCEL);

		// TODO Write the header without using a book object
		Book header = new Book("Title", "Author", "Genre", "Publisher", "Quantity");

		write(filePath, false, header);
		for (Book b : books) {
			write(filePath, true, b);
		}
		newBooksCSV.close();
		return success;
	}

	/**
	 * Searches for a book and returns a boolean value depending on whether it was
	 * found.
	 * 
	 * @param filePath String Location of the .csv file.
	 * @param book     Book object to search for.
	 * @return boolean True if book is in the CSV, false if it is not.
	 * @throws IOException           if the named file exists but is a directory
	 *                               rather than a regular file, does not exist but
	 *                               cannot be created, or cannot be opened for any
	 *                               other reason
	 * @throws FileNotFoundException if the named file does not exist,is a directory
	 *                               rather than a regular file,or for some other
	 *                               reason cannot be opened for reading.
	 */
	public static boolean searchForBook(String filePath, Book book) throws IOException, FileNotFoundException {
		Reader csvData = new FileReader(filePath);
		CSVParser parser = CSVParser.parse(csvData, CSVFormat.EXCEL.withFirstRecordAsHeader());
		System.out.println("Searching for " + book.getTitle() + " by Author " + book.getAuthor() + "...");

		for (CSVRecord record : parser) {
			String title = record.get("Title");
			String author = record.get("Author");
			String genre = record.get("Genre");
			String publisher = record.get("Publisher");

			Book b = new Book(title, author, genre, publisher);

			if (b.equals(book)) {
				System.out.println("Book found!\n");
				csvData.close();
				return true;
			}
		} // END FOR LOOP
		System.out.println("Book not found!");
		csvData.close();
		parser.close();

		return false;
	} // END searchForBook()

	/**
	 * Searches for a book and returns a boolean value depending on whether it was
	 * found.
	 * 
	 * @param filePath String Location of the .csv file.
	 * @param String   _title The title of the book to search for.
	 * @return boolean True if book is in the CSV, false if it is not.
	 * @throws IOException           if the named file exists but is a directory
	 *                               rather than a regular file, does not exist but
	 *                               cannot be created, or cannot be opened for any
	 *                               other reason
	 * @throws FileNotFoundException if the named file does not exist,is a directory
	 *                               rather than a regular file,or for some other
	 *                               reason cannot be opened for reading.
	 */
	public static boolean searchForBook(String filePath, String _title) throws IOException, FileNotFoundException {
		Reader csvData = new FileReader(filePath);
		CSVParser parser = CSVParser.parse(csvData, CSVFormat.EXCEL.withFirstRecordAsHeader());
		System.out.println("Searching for " + _title);

		for (CSVRecord record : parser) {
			String title = record.get("Title");
			String author = record.get("Author");
			String genre = record.get("Genre");
			String publisher = record.get("Publisher");

			Book b = new Book(title, author, genre, publisher);

			if (b.getTitle().compareToIgnoreCase(_title) == 0) {
				System.out.println("Book found!\n");
				csvData.close();
				return true;
			}
		} // END FOR LOOP
		System.out.println("Book not found!");
		csvData.close();
		parser.close();

		return false;
	} // END searchForBook()

	/**
	 * Searches for a book by its title and returns the book if it is found.
	 * 
	 * @param filePath String location of the .csv file.
	 * @param _title   String Name of title of book to search for.
	 * @return Book object will be returned if found in .csv. null returned if not
	 *         found
	 * @throws IOException           if the named file exists but is a directory
	 *                               rather than a regular file, does not exist but
	 *                               cannot be created, or cannot be opened for any
	 *                               other reason
	 * @throws FileNotFoundException if the named file does not exist,is a directory
	 *                               rather than a regular file,or for some other
	 *                               reason cannot be opened for reading.
	 */
	public static Book searchAndCheckoutBook(String filePath, String _title, boolean flag) throws IOException, FileNotFoundException { //changed*****
		Reader csvData = new FileReader(filePath);
		CSVParser parser = CSVParser.parse(csvData, CSVFormat.EXCEL.withFirstRecordAsHeader());
		if(flag == true)
		System.out.println("Searching for " + _title);

		// Checks to see if a title was passed.
		if (_title.isBlank()) {
			System.out.println("You didn't enter a title.\n");
			return null;
		}
		else if(searchForBook(Book.BOOK_FILEPATH,_title) == false) { // new method
				return null;
		}
		for (CSVRecord record : parser) {
			String title = record.get("Title");
			String author = record.get("Author");
			String genre = record.get("Genre");
			String publisher = record.get("Publisher");

			Book b = new Book(title, author, genre, publisher);

			if (b.getTitle().compareToIgnoreCase(_title) == 0) {
				if(flag == true)
				System.out.println("Book found in library. Checking for Stock...\n");
				csvData.close();

				// If book is in stock, return the book
				if (updateQuantity(Book.BOOK_FILEPATH, b, false))
					return b;
				else
					return null;
			}

		} // END FOR LOOP
		csvData.close();
		parser.close();

		return null;
	} // END searchAndCheckoutBook()

	/**
	 * Returns a book to the library.
	 * 
	 * @param filePath String filepath of location of books.csv
	 * @param _title   String title of book to return
	 * @return boolean true if return successful, false if not
	 * @throws IOException           if the named file exists but is a directory
	 *                               rather than a regular file, does not exist but
	 *                               cannot be created, or cannot be opened for any
	 *                               other reason
	 * @throws FileNotFoundException if the named file does not exist,is a directory
	 *                               rather than a regular file,or for some other
	 *                               reason cannot be opened for reading.
	 */
	public static boolean returnBookToLibrary(String filePath, String _title)
			throws IOException, FileNotFoundException {
		Reader csvData = new FileReader(filePath);
		CSVParser parser = CSVParser.parse(csvData, CSVFormat.EXCEL.withFirstRecordAsHeader());

		// Checks to see if a title was passed.
		if (_title.isBlank()) {
			System.out.println("You didn't enter a title.\n");
			return false;
		}

		for (CSVRecord record : parser) {
			String title = record.get("Title");
			String author = record.get("Author");
			String genre = record.get("Genre");
			String publisher = record.get("Publisher");

			Book b = new Book(title, author, genre, publisher);

			// Checks to see if book title matches passed title parameter to match books.
			if (b.getTitle().compareToIgnoreCase(_title) == 0) {
				// If book can successfully decrement stock, return true for successful return.
				if (updateQuantity(Book.BOOK_FILEPATH, b, true)) {
					csvData.close();
					parser.close();
					return true;
				} else {
					csvData.close();
					parser.close();
					return false;
				}
			}

		} // END FOR LOOP
		csvData.close();
		parser.close();

		return false;
	} // END returnBookToLibrary()

	/**
	 * Method searches for a book and retrieves it from the .csv. It will return a
	 * book object based off of the passed in title.
	 * 
	 * @param filePath String filepath of location of books.csv
	 * @param _title   String title of book to return
	 * @return Book object if book with mathing title was found in database or null
	 *         if none was found.
	 * @throws IOException           if the named file exists but is a directory
	 *                               rather than a regular file, does not exist but
	 *                               cannot be created, or cannot be opened for any
	 *                               other reason
	 * @throws FileNotFoundException if the named file does not exist,is a directory
	 *                               rather than a regular file,or for some other
	 *                               reason cannot be opened for reading.
	 */
	public static Book getBookFromLib(String filePath, String _title) throws IOException, FileNotFoundException {
		Reader csvData = new FileReader(filePath);
		CSVParser parser = CSVParser.parse(csvData, CSVFormat.EXCEL.withFirstRecordAsHeader());

		Book nullTitleBook = new Book();
		nullTitleBook.setTitle("null");
		
		// Checks to see if a title was passed.
		if (_title.isBlank()) {
			System.out.println("You didn't enter a title.\n");
			return nullTitleBook;
		}

		for (CSVRecord record : parser) {
			String title = record.get("Title");
			String author = record.get("Author");
			String genre = record.get("Genre");
			String publisher = record.get("Publisher");

			Book b = new Book(title, author, genre, publisher);

			if (b.getTitle().compareToIgnoreCase(_title) == 0) {
				csvData.close();
				return b;
			}
		} // END FOR LOOP
		csvData.close();
		parser.close();

		return nullTitleBook;
	} // END getBookFromLib()

	public static void main(String[] args) {

		/*
		 * CSVHandler test
		 */

		// display pre-edit
//		try {
//			displayCSV(filePath);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		// create book to add to csv
		// Book b = new Book("title", "author", "publisher", "genre");

		// add book to csv file
//		try {
//			write(filePath, b);
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		/*
		 * //display new resulting file try { displayCSV(filePath); } catch (IOException
		 * e) { // TODO Auto-generated catch block e.printStackTrace(); } /* //remove
		 * the added book try { removeEntry(filePath, b); } catch (IOException e) { //
		 * TODO Auto-generated catch block e.printStackTrace(); }
		 */

		// display the new resulting csv file
//		try {
//			displayCSV(filePath);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}

}