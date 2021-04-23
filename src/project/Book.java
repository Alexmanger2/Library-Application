package project;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Book {

	private String title;
	private String author;
	private String publisher;
	private String genre;

	public Book() {

	}

	public Book(String title, String author, String genre, String publisher) {
		super();
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.publisher = publisher;
	}

	// getters and setters
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

	public String getGenre() {
		return genre;
	}
	// getters and setters

	public void setGenre(String genre) {
		this.genre = genre;
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
		String filePath = "src/books.csv";
//		Book b = new Book("title", "author", "publisher", "genre");
//		System.out.println(b.toCSV());
		Book b = new Book("Data Smart", "Foreman, John", "data_science", "Wiley");
		Book b2 = new Book("Signal and the Noise, The", "Silver, Nate", "data_science", "Penguin");
		try {
			CSVHandler.searchForBook(filePath, b);
			CSVHandler.searchForBook(filePath, b2);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
