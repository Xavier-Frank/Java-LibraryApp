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

public class UserListController implements Initializable{
	  @FXML
	    private TableView<User>tableView;
	    @FXML
	    private TableColumn<User, Integer> studentId;

	    @FXML
	    private TableColumn<User, String> firstName;

	    @FXML
	    private TableColumn<User,String> lastName;

	    @FXML
	    private TableColumn<User, Integer> yearOfStudy;

	    @FXML
	    private TableColumn<User, String> faculty;
	    @FXML
	    private Button CloseButton;

	    ObservableList<User> users=FXCollections.observableArrayList();

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			try(Connection connection= DataBaseConnection.getConnection();
				java.sql.Statement statement=connection.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
				ResultSet resultSet=statement.executeQuery("Select * from Users")){
				while (resultSet.next()) {
					users.add(new User(resultSet.getInt("StudentId"),resultSet.getString("FirstName"),resultSet.getString("LastName"),resultSet.getInt("Class"),resultSet.getString("Faculty")));
				}
			} catch (Exception e) {
				System.out.println(e);
				
			}
			//populating the table
			
			studentId.setCellValueFactory(new PropertyValueFactory<>("StudentId"));
			firstName.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
			lastName.setCellValueFactory(new PropertyValueFactory<>("LastName"));
			yearOfStudy.setCellValueFactory(new PropertyValueFactory<>("Class"));
			faculty.setCellValueFactory(new PropertyValueFactory<>("Faculty"));
			
			tableView.setItems(users);
						
		}
	    public void CloseButtonPressed(ActionEvent event) {
	    	
			
		}
	    

}
