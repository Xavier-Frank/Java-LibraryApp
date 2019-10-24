package sample;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class EreaderController  implements Initializable{
	
		@FXML
	    private Button PreviousButtonPressed;

	    @FXML
	    private Button nextButtonPressed;
	    
	    @FXML
	    private ImageView LeftImageViewer;

	    @FXML
	    private ImageView CenterImageViewer;

	    @FXML
	    private ImageView RightImageViewer;

	    @FXML
	    private Label HomeTabTitle;

	    @FXML
	    private Label LeftNameTagLabel;

	    @FXML
	    private Label CenterNameTagLabel;

	    @FXML
	    private Label RightNameTagLabel;

	    @FXML
	    private Label LeftLevelTagLabel;

	    @FXML
	    private Label CenterLevelTagLevel;

	    @FXML
	    private Label RightLevelTagLabel;

	    @FXML
	    private Label LeftCategoryLabel;

	    @FXML
	    private Label CenterCategoryLabel;

	    @FXML
	    private Label RightCategoryLabel;
	    @FXML
	    private Label LeftDesc;

	    @FXML
	    private Label CenterDesc;

	    @FXML
	    private Label RightDesc;

	    @FXML
	    private Button AddBook;

	    @FXML
	    private Button AddUser;

	    @FXML
	    private Button SearchBook;

	    @FXML
	    private Button BookList;

	    @FXML
	    private Button UserList;

	    @FXML
	    private Button SearchUser;

	    @FXML
	    void AddBookButtonPressed(ActionEvent event) {

		try {
			Stage primaryStage=new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/sample/AddBook1.fxml"));
			Scene scene = new Scene(root,358,454);
//			scene.getStylesheets().add(getClass().getResource("sample/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("ADD USER");
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
    public void AddUserButtonPressed(ActionEvent event) throws IOException {
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/sample/AddUser2.fxml"));
		Scene scene = new Scene(root, 318, 391);
		//scene.getStylesheets().add(getClass().getResource("sample/application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
    public void SearchBookButtonPressed (ActionEvent event) {
    	
		
	}
    public void SearchUserButtonPressed (ActionEvent event) throws IOException {
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/sample/searchUser.fxml"));
		Scene scene = new Scene(root, 847, 496);
		//scene.getStylesheets().add(getClass().getResource("sample/application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
    	
		
	}
    public void BookListButtonPressed (ActionEvent event) {
    	try {
			Stage primaryStage=new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/sample/BookList.fxml"));
			Scene scene = new Scene(root,847,441);
//			scene.getStylesheets().add(getClass().getResource("sample/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Student's Library Assistant");
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	
		
	}
    public void UserListButtonPressed (ActionEvent event) {
    	try {
			Stage primaryStage=new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/sample/UserList.fxml"));
			Scene scene = new Scene(root,826,460);
//			scene.getStylesheets().add(getClass().getResource("sample/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Student's Library Assistant");
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	
		
	}
    
    String bookId1;

	String bookId2;

	String bookId3;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try(Connection connection=DataBaseConnection.getConnection();
				java.sql.Statement statement=connection.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
				ResultSet resultSet=statement.executeQuery("Select * from Books")) {
			
			//setting images to the home tab
			for (int i = 0; i < 3; i++) {
				if (resultSet.next()) {
					switch (i) {
					case 0:
						bookId1=resultSet.getString("Book_Id");
						
						Image image=new Image(resultSet.getString("ImagePath"));
						LeftImageViewer.setImage(image);
						LeftNameTagLabel.setText(resultSet.getString("Book_Title"));
						LeftCategoryLabel.setText(resultSet.getString("Book_Category"));
						LeftLevelTagLabel.setText(resultSet.getString("Book_Level"));
						LeftDesc.setText(resultSet.getString("Description"));		
						break;
					case 1:
						bookId2=resultSet.getString("Book_Id");
						
						Image image1=new Image(resultSet.getString("ImagePath"));
						CenterImageViewer.setImage(image1);
						CenterNameTagLabel.setText(resultSet.getString("Book_Title"));
						CenterCategoryLabel.setText(resultSet.getString("Book_Category"));
						CenterLevelTagLevel.setText(resultSet.getString("Book_Level"));
						CenterDesc.setText(resultSet.getString("Description"));		
						break;
					case 2:
						bookId3=resultSet.getString("Book_Id");
						
						Image image2=new Image(resultSet.getString("ImagePath"));
						RightImageViewer.setImage(image2);
						RightNameTagLabel.setText(resultSet.getString("Book_Title"));
						RightCategoryLabel.setText(resultSet.getString("Book_Category"));
						RightLevelTagLabel.setText(resultSet.getString("Book_Level"));
						RightDesc.setText(resultSet.getString("Description"));	
					}
					
				}
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	//Controlling the actions of the next nd Previous Buttons
	
	int count=3;
	
	public void nextButtonPressed(ActionEvent event) {
		
		//keeps info of the current page so that when next button is pressed a viewer sees the 4th Book and so on with no repetition
		try (Connection connection=DataBaseConnection.getConnection();
				java.sql.Statement statement=connection.createStatement(ResultSet.CONCUR_READ_ONLY,ResultSet.TYPE_SCROLL_INSENSITIVE);
				ResultSet resultSet=statement.executeQuery("Select * from Books");){
			
			for (int i = 0; i < count; i++) { //for updating to the current Book in database
				resultSet.next();
			}
			
			for (int i = 0; i < count; i++) {
				if (resultSet.next()) {
					count++; //goto the 4th Book in the database
					switch (i) {
					case 0:
						bookId1=resultSet.getString("Book_Id");
						
						Image image=new Image(resultSet.getString("ImagePath"));
						LeftImageViewer.setImage(image);
						LeftNameTagLabel.setText(resultSet.getString("Book_Title"));
						LeftCategoryLabel.setText(resultSet.getString("Book_Category"));
						LeftLevelTagLabel.setText(resultSet.getString("Book_Level"));
						LeftDesc.setText(resultSet.getString("Description "));		
						break;
					case 1:
						bookId2=resultSet.getString("Book_Id");
						
						Image image1=new Image(resultSet.getString("ImagePath"));
						CenterImageViewer.setImage(image1);
						CenterNameTagLabel.setText(resultSet.getString("Book_Title"));
						CenterCategoryLabel.setText(resultSet.getString("Book_Category"));
						CenterLevelTagLevel.setText(resultSet.getString("Book_Level"));
						CenterDesc.setText(resultSet.getString("Description "));		
						break;
					case 2:
						bookId3=resultSet.getString("Book_Id");
						
						Image image2=new Image(resultSet.getString("ImagePath"));
						RightImageViewer.setImage(image2);
						RightNameTagLabel.setText(resultSet.getString("Book_Title"));
						RightCategoryLabel.setText(resultSet.getString("Book_Category"));
						RightLevelTagLabel.setText(resultSet.getString("Book_Level"));
						RightDesc.setText(resultSet.getString("Description "));	
						break;
					}		
					
				}
				
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void PreviousButtonPressed(ActionEvent event) throws SQLException {
		if (count > 3) { //goto the first Book in the database
			count -= 3; 
			}
		try (Connection connection=DataBaseConnection.getConnection();
				java.sql.Statement statement=connection.createStatement(ResultSet.CONCUR_READ_ONLY,ResultSet.TYPE_SCROLL_INSENSITIVE);
				ResultSet resultSet=statement.executeQuery("Select * from Books");){
			
			for (int i = 0; i < count; i++) {
				resultSet.next(); //move cursor to the previous page
			}
			for (int i = 0; i < 3; i++) {
				
				if (resultSet.previous()) {
					switch (i) {
					case 2:
						bookId1=resultSet.getString("Book_Id");
						
						Image image=new Image(resultSet.getString("ImagePath"));
						LeftImageViewer.setImage(image);
						LeftNameTagLabel.setText(resultSet.getString("Book_Title"));
						LeftCategoryLabel.setText(resultSet.getString("Book_Category"));
						LeftLevelTagLabel.setText(resultSet.getString("Book_Level"));
						LeftDesc.setText(resultSet.getString("Description "));		
						break;
					case 1:
						bookId2=resultSet.getString("Book_Id");
						
						Image image1=new Image(resultSet.getString("ImagePath"));
						CenterImageViewer.setImage(image1);
						CenterNameTagLabel.setText(resultSet.getString("Book_Title"));
						CenterCategoryLabel.setText(resultSet.getString("Book_Category"));
						CenterLevelTagLevel.setText(resultSet.getString("Book_Level"));
						CenterDesc.setText(resultSet.getString("Description "));		
						break;
					case 0:
						bookId3=resultSet.getString("Book_Id");
						
						Image image2=new Image(resultSet.getString("ImagePath"));
						RightImageViewer.setImage(image2);
						RightNameTagLabel.setText(resultSet.getString("Book_Title"));
						RightCategoryLabel.setText(resultSet.getString("Book_Category"));
						RightLevelTagLabel.setText(resultSet.getString("Book_Level"));
						RightDesc.setText(resultSet.getString("Description "));	
						break;		
					}
				}
			}
		}catch (Exception e) {
			System.out.println(e);
		}
		
		
	}
}
