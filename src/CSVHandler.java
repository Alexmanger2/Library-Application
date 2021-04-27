package project;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.io.Reader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;  
import org.apache.commons.csv.CSVPrinter;  


public class CSVHandler {
	private final static String DELIMITER = ",";

	public static void write(String filePath, boolean append, Book book) throws IOException 
	{	//true = append mode
		try(CSVPrinter printer = new CSVPrinter(new FileWriter(filePath, append), CSVFormat.EXCEL))
		{
			/*
			 * would like to make this class more generic / take CSVable objects instead of book so that the same handler 
			 * can work on any data we want to track with csv like the people or anything else that comes up 
			 * 
			String from = book.toCSV();
			StringTokenizer record = new StringTokenizer(from , DELIMITER);
			
			String [] values = new String[record.countTokens()];
			
			for(int i = 0; i < values.length; i++) {
				values[i] = record.nextToken();
			}
			
			for(int i = 0; i < values.length; i++) {
				printer.print(values[i]);
			}
			printer.println();
			*/
			printer.print(book.getTitle());
			printer.print(book.getAuthor());
			printer.print(book.getGenre());
			printer.print(book.getPublisher());
			printer.println();
			
			printer.close();
		} catch (IOException ex) {
		     ex.printStackTrace();
		 }
	}

	public static void displayCSV(String filePath) throws IOException
	{
		Reader csvData = new FileReader(filePath);
		CSVParser parser = CSVParser.parse(csvData, CSVFormat.EXCEL.withFirstRecordAsHeader() );
		 for (CSVRecord record : parser) {
				//needs to be changed to some how work for any files records not just books.csv
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

	
	public static void addNewBook(String filePath, Book book) throws IOException {
		write(filePath, true, book);
	}
	
	public static void removeBook(String filePath, Book book) throws IOException
	{
		List<Book> books = new ArrayList<Book>();
		
		Reader csvData = new FileReader(filePath);
		CSVParser parser = CSVParser.parse(csvData, CSVFormat.EXCEL.withFirstRecordAsHeader() );
		 for (CSVRecord record : parser) {
				//needs to be changed to some how work for any files records not just books.csv
			    String title = record.get("Title");
			    String author = record.get("Author");
			    String genre = record.get("Genre");
			    String publisher = record.get("Publisher");
			    
			    Book b = new Book(title, author, genre, publisher);
			    if(!b.equals(book))
				{
			    	books.add(b);
				}	
				//else ignore so this book effectively gets removed from books list 
			    //and effectively the file
		 }
		csvData.close();
		parser.close();
		
		//create a new empty books.csv to be populated with the books data
		CSVPrinter newBooksCSV = new CSVPrinter(new FileWriter(filePath), CSVFormat.EXCEL);
		
		//add header to top of csv 
		//This could be done better
		Book header = new Book("Title", "Author", "Genre", "Publisher");
		
		write(filePath, false, header);
		for(Book b : books) {
			write(filePath, true, b);
		}
		newBooksCSV.close();
	}
	
	public static void updateQuantity(String filePath, Book book) throws IOException
	{
		Reader csvData = new FileReader(filePath);
		CSVParser parser = CSVParser.parse(csvData, CSVFormat.EXCEL.withFirstRecordAsHeader() );
		CSVPrinter printer = new CSVPrinter(new FileWriter(filePath), CSVFormat.EXCEL);

		for (CSVRecord record : parser) {
			//needs to be changed to some how work for any files records not just books.csv
			String title = record.get("Title");
			String author = record.get("Author");
			String genre = record.get("Genre");
			String publisher = record.get("Publisher");
			String tempQuantity = record.get("Quantity");
			
			int quantity = Integer.parseInt(tempQuantity);
			Book b = new Book(title, author, genre, publisher);
			System.out.println(b);
		   
			if(b.equals(book))
			{
				//write(filePath, b);
				printer.print(book.getTitle());
				printer.print(book.getAuthor());
				printer.print(book.getGenre());
				printer.print(book.getPublisher());
				printer.print(--quantity);
			} 
			//else ignore so this book effectively gets removed from the file
		}
		csvData.close();
		parser.close();
	}
	
	public static Boolean searchForBook(String filePath, Book book) throws IOException, FileNotFoundException {
		
		Reader csvData = new FileReader(filePath);
		CSVParser parser = CSVParser.parse(csvData, CSVFormat.EXCEL.withFirstRecordAsHeader());
		System.out.println("Searching for " + book.getTitle() + " by Author " + book.getAuthor() + "...");
		for (CSVRecord record : parser) {
			//needs to be changed to some how work for any files records not just books.csv
			String title = record.get("Title");
			String author = record.get("Author");
			String genre = record.get("Genre");
			String publisher = record.get("Publisher");
			    
			Book b = new Book(title, author, genre, publisher);
			//System.out.println(b);
		   
			if(b.equals(book))
			{
				System.out.println("Book found!\n" + book + "\n");
				csvData.close();
				return true;
			} 
		} // END FOR LOOP
		System.out.println("Book not found!");
		csvData.close();
		parser.close();
		
		return false;
	} // END searchEntry()
	
	public static void main(String[] args) {
		
		
		/*
		 * CSVHandler test  
		 */
		
		String filePath = "src/books.csv";
		
		//display pre-edit
		try {
			displayCSV(filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println();
		System.out.println();
		System.out.println();
		 
		//create book to add to csv
		Book b = new Book("title", "author", "publisher", "genre");
		
		
		//add book to csv file
		try {
			write(filePath, true, b);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		//display new resulting file
		try {
			displayCSV(filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println();
		System.out.println();
		System.out.println();
		
		
		//remove the added book

		try {
			removeBook(filePath, b);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//display the new resulting csv file
		try {
			displayCSV(filePath);
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
		System.out.println();
		System.out.println();
		System.out.println();
	}
	
}
