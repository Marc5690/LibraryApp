	package ef.gh;
	
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.ArrayList;
	import java.util.Collections;
	import java.util.Properties;
	
	import ab.cd.*;
	
	public class Actions {
		
		ArrayList<Book> books = new ArrayList<Book>();
	    
	    Connection connection = null;
	    Properties props = new Properties();
	    String url = "jdbc:mysql://localhost:3306/library";
	
	public void connectToDatabase(){
	 
	    props.put("user", "root");         
	    props.put("password", "PASSWORD");
	    props.put("useUnicode", "true");
	    props.put("useServerPrepStmts", "false"); 
	    props.put("characterEncoding", "UTF-8");
	    
		try {
	        connection = DriverManager.getConnection(url, props);
	    } catch (SQLException ex) {
	        System.out.println("SQLException: " + ex.getMessage());
	        System.out.println("SQLState: " + ex.getSQLState());
	        System.out.println("VendorError: " + ex.getErrorCode());
	    }
	}
	
	public void loadBooks(){
		books.removeAll(books);
		try {
			Statement statement = connection.createStatement();
			String select = "select * from book;";
			ResultSet rows;
			rows = statement.executeQuery(select);
			while(rows.next()){
				Book book = new Book(
						rows.getInt("isbn"),
						rows.getString("title"),
						rows.getString("genre"),
						rows.getInt("rating"));
				books.add(book);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	    
	public void saveBooks(){
	   	try {
			Statement statement = connection.createStatement();
			for(Book b:books){
			String select = "INSERT into book(isbn, title, genre, rating) " +
					"VALUES (" + b.getIsbn() + 
					",'" + b.getTitle() + 
					"','" + b.getGenre() + 
					"'," + b.getIsbn() + ") ON DUPLICATE KEY UPDATE " +
					"title = VALUES(title), " +
					"genre = VALUES(genre)" +
					"rating = VALUES(rating);";
			statement.executeUpdate(select);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
				
	public void ratingSort(){
		Collections.sort(books, new BookComparatorRating());
	}
	
	public void addBook(int isbn, String title,String genre, int rating){
		Book newBook = new Book(isbn, title, genre, rating);		
		books.add(newBook);
	}
	
	public void updateBook(int isbn, String title,String genre, int rating){
		for(Book book:books){
			if(book.getIsbn() == isbn){
				book.setTitle(title);
				book.setGenre(genre);
				book.setRating(rating);
			}
		}
	}
	
	public boolean removeBook(int isbn){
		Boolean removed = false;
		int exists = 0;
		try {
			Statement statement = connection.createStatement();
			String checkBook = "Select * FROM book WHERE isbn = " + isbn + ");";
			exists = statement.executeUpdate(checkBook);					
		} catch (SQLException e) {
			e.printStackTrace();
		}
	 	
		for(Book book:books){
			if(book.getIsbn() == isbn && exists > 0){
				books.remove(book);
			 	try {
					Statement statement = connection.createStatement();
					String removeBook = "DELETE FROM book WHERE isbn = " + isbn + ");";
					statement.executeUpdate(removeBook);					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				removed = true;
			}
		}
		return removed;	
	}
	
	public String allBooks(){
		String allBooks = new String();
		for(Book book: books){
			allBooks += book.toString() + "\n";
		}
		return allBooks;
	}
	
	//the titleSearch method uses the Linear search algorithm
	public ArrayList<Book> titleSearch(String title){
	ArrayList<Book> foundBooks = new ArrayList<Book>();
		for(Book b : books){
			if (b.getTitle().equals(title)){
				foundBooks.add(b);
			}
		}
		return foundBooks;
	}
	
	
	//the genreSearch method uses the Linear search algorithm
	public ArrayList<Book> genreSearch(String genre){
	ArrayList<Book> foundBooks = new ArrayList<Book>();
		for(Book b : books){
			if (b.getGenre().equals(genre)){
				foundBooks.add(b);
			}
		}
		return foundBooks;
	}
	
	//isbnSearch uses a Linear Search to return the correct videogame.
	public ArrayList<Book> isbnSearch(int isbn){
	ArrayList<Book> foundBooks = new ArrayList<Book>();
		for(Book b : books){
			if (b.getIsbn() == isbn){
				foundBooks.add(b);
			}
		}
		return foundBooks;
	}
	
	//the isbnSort method uses the Selection/Insertion sort algorithm
	public void isbnSort(){
		Collections.sort(books, new BookComparatorIsbn());
	}
	
	//the titleSort method uses the Selection/Insertion sort algorithm
		public void titleSort(){
			Collections.sort(books, new BookComparatorTitle());
		}
	}
