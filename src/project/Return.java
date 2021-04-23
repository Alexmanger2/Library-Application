package project;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Return {


	
	public Return() {
		// TODO Auto-generated constructor stub
		
		
		
	}

	
	
	public void returnBook(Person p1, Book b) {
		
		for (Map.Entry<Book, Date> entry : p1.map.entrySet()) {

			if(entry.getKey() == b) {
				p1.map.remove(entry.getKey());
														
				Calendar cal = Calendar.getInstance();  
				cal.add(Calendar.MONTH, -3);
				if(cal.getTime().compareTo(entry.getValue()) > 0) {
					p1.setLateBalance(1.0);
					System.out.println("You returned the book late, fee has been added to account");
				
				}
				if(cal.getTime().compareTo(entry.getValue()) == 0) {
					
					p1.setLateBalance(0.0);
					System.out.println("You returned the book on time");
				}
				if(cal.getTime().compareTo(entry.getValue()) < 0) {
					p1.setLateBalance(0.0);
					System.out.println("You returned the book on time");
				}
				
			}
		}
	}
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Person alex = new Person();
		Book b = new Book("This is the title", "Bob smith", "publisher", "Fiction");
		Book c = new Book("Data Smart", "Foreman,John", "data_science", "Wiley");
	//	Book c = new Book("This is the title", "Bob smith", "publisher", "Fiction");
		//b.setTitle("This is the title");
		Borrow checkout = new Borrow();
		checkout.borrowBook(alex,c);
	//	checkout.borrowBook(alex,c);
		
		Return checkin = new Return();
		
		checkin.returnBook(alex , c);
		
	}
	
	
	
	

}
