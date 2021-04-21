package project;


//import java.io.FileNotFoundException;
import java.io.Reader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;  
import org.apache.commons.csv.CSVPrinter;  


public class CSVHandler {

	public static void write(String filePath, Book book) throws IOException 
	{
		try(CSVPrinter printer = new CSVPrinter(new FileWriter(filePath), CSVFormat.EXCEL))
		{
			printer.print(book.getTitle());
			printer.print(book.getAuthor());
			printer.print(book.getGenre());
			printer.print(book.getHeight());
			printer.print(book.getPublisher());
			printer.println();
			
		} catch (IOException ex) {
		     ex.printStackTrace();
		 }
	}
	
	
	//overloaded version for persons 
	public static void write(String filePath, Person person) throws IOException 
	{
		try(CSVPrinter printer = new CSVPrinter(new FileWriter(filePath), CSVFormat.EXCEL))
		{
			printer.printRecord(person);
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
			    String height = record.get("Height");
			    String publisher = record.get("Publisher");
			    
			    int h = Integer.parseInt(height);
			    Book b = new Book(title, author, genre, h, publisher);
			    String bCSV = b.toCSV();
			    System.out.println(bCSV);
		 }
	}

	
	public static void removeEntry(String filePath, Book book) throws IOException
	{
		Reader csvData = new FileReader(filePath);
		CSVParser parser = CSVParser.parse(csvData, CSVFormat.EXCEL.withFirstRecordAsHeader() );
		for (CSVRecord record : parser) {
			//needs to be changed to some how work for any files records not just books.csv
			String title = record.get("Title");
			String author = record.get("Author");
			String genre = record.get("Genre");
			String height = record.get("Height");
			String publisher = record.get("Publisher");
			    
			int h = Integer.parseInt(height);
			Book b = new Book(title, author, genre, h, publisher);
			System.out.println(b);
		   
			if(!b.equals(book))
			{
				//write this iteration book into csv
				
				write(filePath, b);
			} 
			//else ignore so this book effectively gets removed from the file
		}
	}
	
	public static void main(String[] args) {
		
		
		/*
		 * CSVHandler test  
		 */
		
		String filePath = "src/books.csv";
		
		//display pre-edit
		try {
			displayCSV(filePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 
		//create book to add to csv
		Book b = new Book("a","b","c", 5, "d");
		
		
		//add book to csv file
		try {
			write(filePath, b);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		/*
		//display new resulting file
		try {
			displayCSV(filePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	/*	
		//remove the added book
		try {
			removeEntry(filePath, b);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		//display the new resulting csv file
		try {
			displayCSV(filePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
