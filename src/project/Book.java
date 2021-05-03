package project;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Book {

	public static final String BOOK_FILEPATH = "src/books.csv";

	private String title;
	private String author;
	private String publisher;
	private String genre;
	private String stringQuantity;
	private int intQuantity;

	/**
	 * Default constructor for Book class.
	 * 
	 */
	public Book() {

	}

	/**
	 * Constructor that takes in 4 fields and assigns them to the private state
	 * data.
	 * 
	 * @param title     Title of book.
	 * @param author    Author of book.
	 * @param genre     Genre of book.
	 * @param publisher Publisher of book.
	 */
	public Book(String title, String author, String genre, String publisher) {
		super();
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.publisher = publisher;
	}

	/**
	 * Constructor that takes in 5 fields and assigns them to the private state
	 * data. This constructor takes in a String quantity and sets variable
	 * stringQuantity to it.
	 * 
	 * @param title     Title of book.
	 * @param author    Author of book.
	 * @param genre     Genre of book.
	 * @param publisher Publisher of book.
	 * @param quantity  String quantity, meant to be used/set as "Quantity" for the
	 *                  header of the CSV.
	 */
	public Book(String title, String author, String genre, String publisher, String quantity) {
		super();
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.publisher = publisher;
		this.stringQuantity = quantity;
	}

	/**
	 * Constructor that takes 5 fields and assigns them to the private state data.
	 * This constructor takes in a int of quantity that will temporarily represent
	 * the quantity of this book that is available in the library. This is to assist
	 * CSVHandler methods when removing or updatating quantity of a book.
	 * 
	 * @param title       Title of book.
	 * @param author      Author of book.
	 * @param genre       Genre of book.
	 * @param publisher   Publisher of book.
	 * @param intQuantity int quantity, meant to be used/set as "Quantity" for the
	 *                    header of the CSV.
	 */
	public Book(String title, String author, String genre, String publisher, int intQuantity) {
		super();
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.publisher = publisher;
		this.intQuantity = intQuantity;
	}

	/**
	 * Getter for title
	 * 
	 * @return String title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Setter for title
	 * 
	 * @param title String sets title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Getter for author
	 * 
	 * @return String author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Sets author
	 * 
	 * @param author String sets author
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * Getter for Publisher
	 * 
	 * @return String publisher
	 */
	public String getPublisher() {
		return publisher;
	}

	/**
	 * Setter for publisher
	 * 
	 * @param publisher String sets publisher
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	/**
	 * Getter for genre
	 * 
	 * @return String genre
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * Setter for genre
	 * 
	 * @param genre String sets genre
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	/**
	 * Getter for stringQuantity
	 * 
	 * @return String stringQuantity
	 */
	public String getStringQuantity() {
		return stringQuantity;
	}

	/**
	 * Setter for intQuantity
	 * 
	 * @param stringQuantity String sets stringQuantity
	 */
	public void setStringQuantity(String stringQuantity) {
		this.stringQuantity = stringQuantity;
	}

	/**
	 * Getter for intQuantity
	 * 
	 * @return int: intQuantity
	 */
	public int getIntQuantity() {
		return intQuantity;
	}

	/**
	 * Setter for intQuantity
	 * 
	 * @param intQuantity int: Sets intQuantity
	 */
	public void setIntQuantity(int intQuantity) {
		this.intQuantity = intQuantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result + ((publisher == null) ? 0 : publisher.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (genre == null) {
			if (other.genre != null)
				return false;
		} else if (!genre.equals(other.genre))
			return false;
		if (publisher == null) {
			if (other.publisher != null)
				return false;
		} else if (!publisher.equals(other.publisher))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", publisher=" + publisher + ", genre=" + genre + "]";
	}

	/**
	 * Turns a book into a CSV
	 * 
	 * @return String a books data in a CSV state.
	 */
	public String toCSV() {
		StringBuilder sb = new StringBuilder();
		String test = new String("aaaaa");
		sb.append(title);
		sb.append(',');
		sb.append(author);
		sb.append(',');
		sb.append(publisher);
		sb.append(',');
		sb.append(genre);
		sb.append('\n');

		return sb.toString();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Book b = new Book("title", "author", "publisher", "genre");
//		System.out.println(b.toCSV());
		Book b = new Book("Data Smart", "Foreman, John", "data_science", "Wiley");
		Book b2 = new Book("Signal and the Noise, The", "Silver, Nate", "data_science", "Penguin");
		try {
			CSVHandler.searchForBook(BOOK_FILEPATH, "Machine Learning For hackers");
			Address myAddress = new Address("100", "Staten Island", "10301", "New York");

			Person bob = new Person("Bob", "Smith", 1875, myAddress);
			bob.setPhoneNumber("555-444-3332");

			Book b1 = new Book();
			System.out.println("b1 before searchAndCheckoutBook: " + b1);
			b1 = CSVHandler.searchAndCheckoutBook(BOOK_FILEPATH, "   ",false);  //changed****
			System.out.println("b1 after searchAndCheckoutBook: " + b1);

//			CSVHandler.addNewBook(BOOK_FILEPATH, b);
//			CSVHandler.searchForBook(BOOK_FILEPATH, b);
//			CSVHandler.removeBook(BOOK_FILEPATH, b);
//			CSVHandler.searchForBook(BOOK_FILEPATH, b);
//			CSVHandler.addNewBook(BOOK_FILEPATH, b);
//			CSVHandler.searchForBook(BOOK_FILEPATH, b);

//			CSVHandler.searchForBook(BOOK_FILEPATH, b2);
//			CSVHandler.updateQuantity(BOOK_FILEPATH, b2, true);
//			CSVHandler.updateQuantity(BOOK_FILEPATH, b2, true);
//			CSVHandler.updateQuantity(BOOK_FILEPATH, b2, false);
//			CSVHandler.searchForBook(BOOK_FILEPATH, b2);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
