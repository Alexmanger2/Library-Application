package project;

public class Book {

	private String title;
	private String author;
	private String publisher;
	private int height;
	private String genre;
	
	
	public Book() {
		
	}

	public Book(String title, String author, String publisher, int height, String genre) {
		super();
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.height = height;
		this.genre = genre;
	}

	
	//getters and setters
	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getPublisher() {
		return publisher;
	}


	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}


	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		this.height = height;
	}


	public String getGenre() {
		return genre;
	}
	//getters and setters

/*
 * idk if we need this 
 * 
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result + height;
		result = prime * result + ((publisher == null) ? 0 : publisher.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
*/

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
		if (height != other.height)
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


	public void setGenre(String genre) {
		this.genre = genre;
	}



	

	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", publisher=" + publisher + ", height=" + height
				+ ", genre=" + genre + "]";
	}

	public String toCSV() {
		StringBuilder sb = new StringBuilder();
		String test = new String("aaaaa");
		sb.append(title);
		sb.append(',');
		sb.append(author);
		sb.append(',');
		sb.append(publisher);
		sb.append(',');
		sb.append(height);
		sb.append(',');
		sb.append(genre);
		sb.append('\n');
		
		return sb.toString();
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Book b = new Book("title","author","publisher", 5, "genre");
		System.out.println(b.toCSV());
	}



}
