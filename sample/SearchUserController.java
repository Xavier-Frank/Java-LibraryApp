package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class SearchUserController implements Initializable {

    @FXML
    private TextField searchField;

    @FXML
    private TableView<SearchUser> searchUser;

    @FXML
    private TableColumn<SearchUser, Integer> id;

    @FXML
    private TableColumn<SearchUser,String> firstName;

    @FXML
    private TableColumn<SearchUser, String> lastName;

    @FXML
    private TableColumn<SearchUser, Integer> yearOfStudy;

    @FXML
    private TableColumn<SearchUser, String> faculty;

    ObservableList <SearchUser> users = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try(Connection connection= DataBaseConnection.getConnection();
            java.sql.Statement statement=connection.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
            ResultSet resultSet=statement.executeQuery("Select * from Users")){
            while (resultSet.next()) {
                users.add(new SearchUser(resultSet.getInt("StudentId"),resultSet.getString("FirstName"),resultSet.getString("LastName"),resultSet.getInt("Class"),resultSet.getString("Faculty")));
            }
        } catch (Exception e) {
            System.out.println(e);

        }
        //populating the tab

        id.setCellValueFactory(new PropertyValueFactory<>("StudentId"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        yearOfStudy.setCellValueFactory(new PropertyValueFactory<>("Class"));
        faculty.setCellValueFactory(new PropertyValueFactory<>("Faculty"));

        searchUser.setItems(users);


        // filtering the tableView
        FilteredList<SearchUser>  filteredList= new FilteredList<>(users,bool -> true);
        searchField.textProperty().addListener((observableValue, oldValue,newValue ) -> {
            filteredList.setPredicate(users -> {

                //if the search filed is empty display all users

                if (newValue == null || newValue.isEmpty()){
                    return true;
                }

                //compares the last name and first name of every person with filter text

                String lowerCaseFilter = newValue.toLowerCase();

                if (users.getFirstName().toLowerCase().indexOf(lowerCaseFilter) !=-1){
                    return  true ; //filter matches first name

                }else if (users.getLastName().toLowerCase().indexOf(lowerCaseFilter) !=-1){
                    return  true; //filter matches last name
                }else if (users.getFaculty().toLowerCase().indexOf(lowerCaseFilter) !=-1){
                    return  true; //filter matches faculty
                }else  if (String.valueOf(users.getStudent_id()).indexOf(lowerCaseFilter) !=-1){
                    return  true; //filter matches  student id
                }else  if (String.valueOf(users.yearOfStudy).indexOf(lowerCaseFilter) !=-1){
                    return  true; //matches year of study
                }else {
                    return true; //no match therefore returns the original list
                }


            });
                }
                );
        //sorting the filtered list

        SortedList<SearchUser> sortedList =new SortedList<>(filteredList);

        //bind sorted comparator to the table view comparator
        //otherwise sorting will not work

        sortedList.comparatorProperty().bind(searchUser.comparatorProperty());

        //set sorted list to table view
        searchUser.setItems(sortedList);




    }
}
