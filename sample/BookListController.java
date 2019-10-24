package sample;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class BookListController implements Initializable {
	 	@FXML
	    private TableView<Book> booklist;
	 
	 	@FXML
	    private TableColumn<Book, String> bookId;

	    @FXML
	    private TableColumn<Book, String> bookTitle;

	    @FXML
	    private TableColumn<Book, String> bookCategory;

	    @FXML
	    private TableColumn<Book, String> bookLevel;

	    @FXML
	    private TableColumn<Book, String> bookDescription;
	    @FXML
	    private Button CloseButton;
	    
	    //create observable list (books)
	    ObservableList<Book> books =FXCollections.observableArrayList();

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			try (Connection connection= DataBaseConnection.getConnection();
				 java.sql.Statement statement=connection.createStatement(ResultSet.CONCUR_READ_ONLY,ResultSet.TYPE_SCROLL_INSENSITIVE);
				 ResultSet resultSet=statement.executeQuery("Select * from Books");){
				
				while (resultSet.next()) {
					
					//getting details to be displayed from the database
					
					books.add(new Book(resultSet.getString("Book_Id"),resultSet.getString("Book_Title"),resultSet.getString("Book_Category"),resultSet.getString(" Book_Level"),resultSet.getString(" Description")));
				}
				
			} catch (Exception e) {
				System.out.println(e);
			}
			//populating the table
			
			bookId.setCellValueFactory(new PropertyValueFactory<>("Book_Id"));
			bookTitle.setCellValueFactory(new PropertyValueFactory<>("Book_Title"));
			bookCategory.setCellValueFactory(new PropertyValueFactory<>("Book_Category"));
			bookLevel.setCellValueFactory(new PropertyValueFactory<>("Book_Level"));
			bookDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
			
			booklist.setItems(books); //setting items to the table
		}
		public void CloseButtonPressed(ActionEvent event) {
			
			
		}
}
			
			
				
			
					
			
	    
	    
	    

