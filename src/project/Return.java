
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
					
					
					if(date.getKey() == b) {
						
						rBook = b;
						this.remove = true;
						this.check = false;
						
					Calendar cal = Calendar.getInstance();  
					cal.add(Calendar.MONTH, -3);
					if(cal.getTime().compareTo(date.getValue()) > 0) {
						p1.setLateBalance(1.0);
						System.out.println("\n" + "You returned the book" + rBook + " late, fee has been added to account");
						System.out.println("");
					
					}
					if(cal.getTime().compareTo(date.getValue()) == 0) {
						
						p1.setLateBalance(0.0);
						System.out.println("\n" + "You returned " + rBook + " on time");
						System.out.println("");
					}
					if(cal.getTime().compareTo(date.getValue()) < 0) {
						p1.setLateBalance(0.0);
						System.out.println("\n" + "You returned " + rBook + " on time");
						System.out.println("");
					}
				
				}
				
				
				}
				
				if(this.check == true) {
						System.out.println("You did not checkout this book: " + holdBook.getTitle());
						System.out.println("");	
				}
				this.check = true;
				
				
		
		if(this.remove == true) {
		p1.map.remove(rBook);
		//Borrow.BookReturned();
		try {
			
			CSVHandler.updateQuantity(Book.filePath, b, true);
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