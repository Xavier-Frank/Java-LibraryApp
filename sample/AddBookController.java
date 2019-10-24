package sample;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AddBookController {
	  @FXML
	    private TextArea Description;

	    @FXML
	    private Button AddImageButtonPressed;

	    @FXML
	    private TextField BookId;

	    @FXML
	    private TextField BookTitle;

	    @FXML
	    private TextField BookLevel;

	    @FXML
	    private TextField BookCategory;

	    @FXML
	    private Label ErrorChecker;

	    @FXML
	    private Button SaveBookButtonPressed;

	    @FXML
	    private Button CloseButtonPressed;

	    @FXML
	    private ImageView ImageViewer;
	    
	    //Image directory for storing paths
	    String imageDirectory;
	    
	    
	    public void SaveButtonPressed (ActionEvent event) {
	    	ErrorChecker.setText("");
	    	try (Connection connection= DataBaseConnection.getConnection();
				 Statement statement=connection.createStatement(ResultSet.CONCUR_READ_ONLY,ResultSet.TYPE_SCROLL_INSENSITIVE);
				 ResultSet resultSet=statement.executeQuery("Select * from Books");){
	    		Boolean detailsCorrect=true;
	    		
	    		while (resultSet.next()) {
	    			if (resultSet.getString("Book_Id").equals(BookId.getText())) {
	    				ErrorChecker.setText("Book Id exists");
	    				ErrorChecker.setTextFill(Color.RED);
	    				detailsCorrect=false;
	    			}
	    		}
	    		if (BookId.getText().equals("") || BookTitle.getText().equals("") || BookCategory.getText().equals("") || BookLevel.getText().equals("")) {
	 
	    			ErrorChecker.setText("Fields required");
	    			ErrorChecker.setTextFill(Color.RED);
	    			detailsCorrect=false;
				}
	    		if (detailsCorrect) {
	    			String sql= "INSERT INTO Books( Book_Id,Book_Title, Book_Category, Book_Level, Description,ImagePath )" + "VALUES(?,?,?,?,?,?)";
	    			PreparedStatement preparedStatement=connection.prepareStatement(sql);
	    			
	    			//Collecting data into database 
	    			preparedStatement.setString(1, BookId.getText());
	    			preparedStatement.setString(2, BookTitle.getText());
	    			preparedStatement.setString(3, BookCategory.getText());
	    			preparedStatement.setString(4, BookLevel.getText());
	    			preparedStatement.setString(5, Description.getText());
	    			preparedStatement.setString(6, imageDirectory);
	    			
	    			preparedStatement.executeUpdate();
	    			
	    			ErrorChecker.setText("Successful");
	    			ErrorChecker.setTextFill(Color.GREEN);
	    			
	    			}
	    		}
	    		catch (Exception e) {
	    			System.out.println(e);
	    		}
	    		
	    	}
	    public void AddImageButtonPressed (ActionEvent event) throws MalformedURLException {
	    	
	    	FileChooser fileChooser= new FileChooser();
	    	File selectedFile=fileChooser.showOpenDialog(ErrorChecker.getScene().getWindow());
	    	
	    	if (selectedFile !=null) {
	    		imageDirectory=selectedFile.toURI().toURL().toString();
	    		Image image=new Image(imageDirectory);
	    		ImageViewer.setImage(image);
	    	}else {
				ErrorChecker.setText("Unsuccessful");
				ErrorChecker.setTextFill(Color.RED);
			}
	    	
	    }
	    public void CloseButtonPressed( ActionEvent event) throws IOException{
//	    	try {
//				Stage primaryStage=new Stage();
//				Parent root = FXMLLoader.load(getClass().getResource("/sample/Ereader.fxml"));
//				Scene scene = new Scene(root,350,380);
//				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//				primaryStage.setScene(scene);
//				primaryStage.close();
//
//			}catch (IOException e){
//				System.out.println(e);
//			}
	    	

	    			
			
		}
				
}
