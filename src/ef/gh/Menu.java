package ef.gh;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import ab.cd.Book;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;//control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Menu extends Application{
	public static void main(String[] args){
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		final Actions action = new Actions();
		final Alert alert = new Alert(AlertType.INFORMATION);
		
		Button loadBtn = new Button();
	    Button listBtn = new Button();
	    Button exitButton = new Button();
	    Button ratingSortButton = new Button();
	    Button addButton = new Button();
	    Button removeButton = new Button();
	    Button updateButton = new Button();
	    Button saveButton = new Button();
	    Button searchByTitleButton = new Button();
	    Button searchByGenreButton = new Button();
	    Button searchByIsbnButton = new Button();
	    Button sortByIsbnButton = new Button();
	    Button sortByTitleButton = new Button();	    
	    	    
	    primaryStage.initStyle(StageStyle.DECORATED);
	    primaryStage.setTitle("Welcome to the Library App!");
	    loadBtn.setText("Load database");
        listBtn.setText("List Books");
        exitButton.setText("Exit");
        ratingSortButton.setText("Top Three");
	    addButton.setText("Add Book");
	    removeButton.setText("Remove Book");
	    updateButton.setText("Update Book");
	    saveButton.setText("Save Database");
	    searchByTitleButton.setText("Title Search");
	    searchByGenreButton.setText("Genre Search");
	    searchByIsbnButton.setText("ISBN Search");
	    sortByIsbnButton.setText("ISBN Sort");
	    sortByTitleButton.setText("Title Sort");
	    
	action.connectToDatabase();
               
    loadBtn.setOnAction(new EventHandler<ActionEvent>() {
    	@Override
    	public void handle(ActionEvent event) {
        	action.loadBooks();
        	alert.setTitle("Load Complete");
            alert.setContentText("Books have been loaded from the database");
            alert.show();
    	}
    });
        
    listBtn.setOnAction(new EventHandler<ActionEvent>() {
    	@Override
    	public void handle(ActionEvent event) {
           	JOptionPane.showMessageDialog(null, action.allBooks());
        }
    });
	        
    exitButton.setOnAction(new EventHandler<ActionEvent>() {
	    @Override
        public void handle(ActionEvent event) {
    	   	System.exit(0);
       	}
    });
        
    ratingSortButton.setOnAction(new EventHandler<ActionEvent>() {
	    @Override
        public void handle(ActionEvent event) {
	    	action.isbnSort();
       	}
    });
	    
	addButton.setOnAction(new EventHandler<ActionEvent>() {
	    @Override
        public void handle(ActionEvent event) {
    	   	String isbn = JOptionPane.showInputDialog(null, "ISBN:");
    		String title = JOptionPane.showInputDialog(null, "Title:");
    		String genre = JOptionPane.showInputDialog(null, "Genre:");
    		String rating = JOptionPane.showInputDialog(null, "Rating:");
    		
    		int parsedIsbn = Integer.parseInt(isbn);
    		int parsedRating = Integer.parseInt(rating);
    		action.addBook(parsedIsbn, title, genre, parsedRating);
    	    alert.setTitle("Save Complete");
            alert.setContentText("Book has been created, but not saved to the database.");
            alert.show();
       	}
    });
	    
	removeButton.setOnAction(new EventHandler<ActionEvent>() {
	    @Override
        public void handle(ActionEvent event) {
	    	int parsedIsbn = Integer.parseInt(JOptionPane.showInputDialog(null, "ISBN:"));
	    	if(action.removeBook(parsedIsbn)){
	    		alert.setTitle("Delete Successful!");
	            alert.setContentText("Book has been removed");
	          
	    	}
	    	else{
	    		alert.setTitle("Delete Failed!");
	            alert.setContentText("Book has could not be removed");
	          
	    	}
    	      alert.show();
       	}
    });
	    
	updateButton.setOnAction(new EventHandler<ActionEvent>() {
	    @Override
        public void handle(ActionEvent event) {
	    	String isbn = JOptionPane.showInputDialog(null, "ISBN:");
    		String title = JOptionPane.showInputDialog(null, "Title:");
    		String genre = JOptionPane.showInputDialog(null, "Genre:");
    		String rating = JOptionPane.showInputDialog(null, "Rating:");
    		
    		int parsedIsbn = Integer.parseInt(isbn);
    		int parsedRating = Integer.parseInt(rating);
    		
	    	action.updateBook(parsedIsbn, title, genre, parsedRating);
    	    alert.setTitle("Update Complete");
            alert.setContentText("Book has been updated, but not saved to the database.");
            alert.show();
       	}
    });
	    
	saveButton.setOnAction(new EventHandler<ActionEvent>() {
	    @Override
        public void handle(ActionEvent event) {
	    	 action.saveBooks();
    	   	 alert.setTitle("Save Complete");
             alert.setContentText("Books have been saved to the database");
             alert.show();
       	}
    });
	    
	searchByTitleButton.setOnAction(new EventHandler<ActionEvent>() {
	    @Override
        public void handle(ActionEvent event) {
	    	String title = JOptionPane.showInputDialog(null, "Title:");
		    ArrayList<Book> foundBooks = action.titleSearch(title);
		    	
		    	if(foundBooks.size() > 0){
		    		alert.setTitle("Following book(s) were found:");
	            	alert.setContentText(foundBooks.toString());
	            	alert.show();
		    	}
		    	else{
		    		alert.setTitle("No books found.");
		        	alert.setContentText("A book with that title could not be found.");
		        	alert.show();
		    	}
	       	}
      	
    });
	    
    searchByGenreButton.setOnAction(new EventHandler<ActionEvent>() {
	    @Override
        public void handle(ActionEvent event) {
	   	String genre = JOptionPane.showInputDialog(null, "Genre:");
	    ArrayList<Book> foundBooks = action.genreSearch(genre);
	    	
	    	if(foundBooks.size() > 0){
	    		alert.setTitle("Following book(s) were found:");
            	alert.setContentText(foundBooks.toString());
            	alert.show();
	    	}
	    	else{
	    		alert.setTitle("No books found.");
	        	alert.setContentText("A book with that genre could not be found.");
	        	alert.show();
	    	}
       	}
    });
	    
    searchByIsbnButton.setOnAction(new EventHandler<ActionEvent>() {
	    @Override
        public void handle(ActionEvent event) {
	    String isbn = JOptionPane.showInputDialog(null, "ISBN:");
	    int parsedIsbn = Integer.parseInt(isbn);
	    ArrayList<Book> foundBooks = action.isbnSearch(parsedIsbn);
	    
	    	if(foundBooks.size() > 0){
	    		alert.setTitle("Following book(s) were found:");
	    		alert.setContentText(foundBooks.toString());
	    		alert.show();
	    	}
	    	else{
	    		alert.setTitle("No books found.");
	    		alert.setContentText("A book with that ISBN could not be found.");
	    		alert.show();
	    	}
	    	}
    });
	    
    sortByIsbnButton.setOnAction(new EventHandler<ActionEvent>() {
	    @Override
        public void handle(ActionEvent event) {
          action.isbnSort();
    	}
    });
	    
    sortByTitleButton.setOnAction(new EventHandler<ActionEvent>() {
	    @Override
        public void handle(ActionEvent event) {
            action.titleSort();
    	}
    });
        
    GridPane grid = new GridPane();
    grid.setAlignment(Pos.CENTER);
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(25, 25, 25, 25));
    grid.add(loadBtn, 0,0);
    grid.add(saveButton,2,0);
    grid.add(listBtn, 0, 1);
    grid.add(removeButton, 2, 1);
    grid.add(addButton, 0, 2);
    grid.add(updateButton, 2, 2);
   	grid.add(ratingSortButton, 0, 3);
   	grid.add(sortByIsbnButton, 2, 3);
   	grid.add(sortByTitleButton, 0, 4);
   	grid.add(searchByIsbnButton, 2, 4);
   	grid.add(searchByTitleButton, 0, 5);
   	grid.add(searchByGenreButton, 2, 5);
   	grid.add(exitButton, 0, 6);
    Scene scene = new Scene(grid, 750, 500);
    primaryStage.setScene(scene);
    primaryStage.show();
	}
}
