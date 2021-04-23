package project;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Borrow {

	
	private boolean canBorrow = true;
	public static int bookCount = 0;
	

	
	
	public Borrow() {
		// TODO Auto-generated constructor stub
	}


	
	//--------------book status (checks to see if book is available or taken out and for how long)---------------
	public void borrowBook(Person p1 ,Book b) {
		
		
		try {
		
		if(CSVHandler.searchForBook(Book.filePath,b)) {
				
			
			//Needs to check if quantity is > 0 and when the book will be available again...
			if(this.canBorrow == true) {
				init(p1,b);
				}
			else {
				System.out.println("Can't borrow book, must pay fee");
			}
			
		}
		
		
		}
		catch(FileNotFoundException ex) {
			ex.printStackTrace();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
				
	}
	
	
	
	public void init(Person p1, Book b) {
		
		
		if(p1.getLateBalance() == 0.0) {
			if(bookCount < 3) {
				++bookCount;
				//p1.setBooksLentOut(null);
			//	bookList.add(b.getTitle());
				p1.setBookList(b);
				
			}else {
				System.out.println("You have checked out the max amount of books, please return a book before trying to rent a new book");
			}
			
			
			
		}
		else {
			this.canBorrow = false;
			System.out.println("Please pay your late fee's to borrow a new book");
		}
		
		
	}
	
	public void clearBalance() {
		this.canBorrow = true;
		
	}
	
	public static void BookReturned()
	{
		
		bookCount = bookCount - 1;
	}
	

}

