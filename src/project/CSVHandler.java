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
			/*
			 * would like to make this class more generic / take CSVable objects instead of
			 * book so that the same handler can work on any data we want to track with csv
			 * like the people or anything else that comes up
			 * 
			 * String from = book.toCSV(); StringTokenizer record = new StringTokenizer(from
			 * , DELIMITER);
			 * 
			 * String [] values = new String[record.countTokens()];
			 * 
			 * for(int i = 0; i < values.length; i++) { values[i] = record.nextToken(); }
			 * 
			 * for(int i = 0; i < values.length; i++) { printer.print(values[i]); }
			 * printer.println();
			 */
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
	
	/** addPerson will add a Person object to the Persons.csv file.
	 * 
	 * @param filePath String filepath of where the Persons.csv is located.
	 * @param append boolean  True for appending to csv, false to rewrite
	 * @param p0 Person object to add to .csv
	 * @throws IOException if the named file exists but is a directory rather than a
	 *                     regular file, does not exist but cannot be created, or
	 *                     cannot be opened for any other reason
	 */
	public static void addPerson(String filePath, boolean append, Person p0) throws IOException { // true = append mode
		try (CSVPrinter printer = new CSVPrinter(new FileWriter(filePath, append), CSVFormat.EXCEL.withFirstRecordAsHeader())) {
			printer.print(p0.getFirstName());
			printer.print(p0.getLastName());
			printer.print((p0.getBirthYear()));
			printer.print(p0.getPhoneNumber());
			printer.print(p0.getAddy().getStreet() );
			printer.print(p0.getAddy().getCity() );
			printer.print(p0.getAddy().getState() );
			printer.print(p0.getAddy().getZip() );

			printer.println();

			printer.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/** Displays a .csv file
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
			// needs to be changed to some how work for any files records not just books.csv
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

	/** Manually add a book to the list
	 * 
	 * @param filePath String filepath of the .csv file to add the book to.
	 * @param book Book to add to the .csv database.
	 * @throws IOException if the named file exists but is a directory rather than a
	 *                     regular file, does not exist but cannot be created, or
	 *                     cannot be opened for any other reason
	 */
	public static void addNewBook(String filePath, Book book) throws IOException {
		write(filePath, true, book);
		System.out.println("Added to CSV: " + book);
	}

	/** Removes a book from the .csv book database
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
	
	public static boolean removePerson(String filePath, Person person) throws IOException {
		List<Person> persons = new ArrayList<Person>();
		
		Reader csvData = new FileReader(filePath);
		CSVParser parser = CSVParser.parse(csvData, CSVFormat.EXCEL.withFirstRecordAsHeader());
		for (CSVRecord record : parser) {
			
			String firstName = record.get("First Name");
			String lastName = record.get("Last Name");
			String phoneNumber = record.get("Phone Number");
			int birthYear = Integer.parseInt(record.get("Birth Year"));
			String street = record.get("Street");
			String city = record.get("City");
			String state = record.get("State");
			String zip = record.get("Zipcode");
			

			Address addy = new Address(street, city, state, zip);
			Person p1 = new Person(firstName, lastName, birthYear, phoneNumber, addy);

			if (!person.equals(p1)) {
				persons.add(p1);
			}
			// else ignore so this book effectively gets removed from books list
			// and effectively the file
		}
		csvData.close();
		parser.close();

		// create a new empty books.csv to be populated with the books data
		CSVPrinter newPersonsCSV = new CSVPrinter(new FileWriter(filePath), CSVFormat.EXCEL);

		// add header to top of csv
		// This could be done better
		//Book header = new Book("Title", "Author", "Genre", "Publisher", "Quantity");

		newPersonsCSV.print("First Name");
		newPersonsCSV.print("Last Name");
		newPersonsCSV.print("Birth Year");
		newPersonsCSV.print("Phone Number");
		newPersonsCSV.print("Street");
		newPersonsCSV.print("City");
		newPersonsCSV.print("State");
		newPersonsCSV.print("Zipcode");
		newPersonsCSV.print("\n");
		//write(filePath, false, header);
		for (Person p : persons) {
			addPerson(filePath, true, p);
		}
		newPersonsCSV.close();
		return true;
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
		System.out.println("Book not found!");
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
						throw new IllegalArgumentException("Can not checkout:\n" + b + " is out of stock!\n");
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
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
	} // END searchEntry()
	
	/** Searches Persons.csv for a person.
	 * 
	 * @param filePath  String filepath for where Persons.csv is located
	 * @param phoneNum String  The phone number to be searched.
	 * @return Will return a Person object or null if their is no person with passed phoneNumber found in DB.
	 * @throws IOException if the named file exists but is a directory
	 *                               rather than a regular file, does not exist but
	 *                               cannot be created, or cannot be opened for any
	 *                               other reason
	 * @throws FileNotFoundException if the named file does not exist,is a directory
	 *                               rather than a regular file,or for some other
	 *                               reason cannot be opened for reading.
	 */
	public static Person getPersonFromCSV(String filePath, String phoneNum) throws IOException, FileNotFoundException {
		Person p1 = new Person();
		Address addy = new Address();
		
		Reader csvData = new FileReader(filePath);
		CSVParser parser = CSVParser.parse(csvData, CSVFormat.EXCEL.withFirstRecordAsHeader());
		System.out.println("Searching for person with phone#: " + phoneNum + "...");

		for (CSVRecord record : parser) {
			p1.setFirstName(record.get("First Name"));
			p1.setLastName(record.get("Last Name"));	
			p1.setPhoneNumber(record.get("Phone Number"));
			p1.setBirthYear(Integer.parseInt(record.get("Birth Year")));
			addy.setStreet(record.get("Street"));
			addy.setCity(record.get("City"));
			addy.setState(record.get("State"));
			addy.setZip(record.get("Zipcode"));
			
			p1.setAddy(addy);

			//System.out.println(p1.getPhoneNumber() + " is p1 phone# and " + phoneNum + " is passed phone Number");
			
			if (p1.getPhoneNumber().compareTo(phoneNum) == 0) {
				System.out.println("Person found!\n");
				csvData.close();
				return p1;
			}
		} // END FOR LOOP
		System.out.println("Person not found!");
		csvData.close();
		parser.close();

		// If Person is not found return null
		return null;
	} // END searchEntry()

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