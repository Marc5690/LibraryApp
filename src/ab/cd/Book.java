package ab.cd;

import java.io.Serializable;


public class Book implements Serializable{

	private static final long serialVersionUID = 7827375254595390042L;
	

	private int isbn;
	private String title;
	private String genre;
	private int rating;
		
	public Book(int isbn, String title, String genre, int rating){
		this.isbn = isbn;
		this.title = title;
		this.genre = genre;
		this.rating = rating;
	}
		
	public String toString(){
		return "ISBN: '" + isbn + "' Title: '" + title + "' Genre: '" + genre + "' Rating: '" + rating + "'";
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}
	
	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	
}