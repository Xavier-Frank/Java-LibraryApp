package sample;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AddUserController {


    @FXML
    private Label errorChecker;

    @FXML
    private Button SaveButton;

    @FXML
    private Button CloseButton;

    @FXML
    private TextField UserId;

    @FXML
    private TextField FirstName;

    @FXML
    private TextField LastName;

    @FXML
    private TextField Class;

    @FXML
    private TextField Faculty;


    @FXML
    public void SaveButtonPressed(ActionEvent event) throws SQLException {
        errorChecker.setText("");
        //connecting to database;
        try (Connection connection = DataBaseConnection.getConnection();
             java.sql.Statement statement = connection.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
             ResultSet resultSet = statement.executeQuery("Select * from Users");) {
            Boolean validDetails = true;

            while (resultSet.next()) {
                if (resultSet.getString("StudentId").equals(UserId.getText())) {
                    errorChecker.setText("User ID already exists");
                    errorChecker.setTextFill(Color.RED);
                    validDetails = false;
                    //remember to set border color to red

                }

            }
            if (UserId.getText().equals("") || FirstName.getText().equals("") || LastName.getText().equals("") || Class.getText().equals("") || Faculty.getText().equals("")) {
                errorChecker.setText("Fields required");
                validDetails = false;

            }

            /* **************************************************************************** */
//            if (UserId.getText().equals("")){
//                UserId.setBorder();
//            }
            if (validDetails) {
                String sqlString = "INSERT INTO Users(StudentId,FirstName,LastName,Class,Faculty)" + "VALUES (?,?,?,?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
                //inserting into database

                preparedStatement.setString(1, UserId.getText());
                preparedStatement.setString(2, FirstName.getText());
                preparedStatement.setString(3, LastName.getText());
                preparedStatement.setString(4, Class.getText());
                preparedStatement.setString(5, Faculty.getText());

                preparedStatement.executeUpdate();

                errorChecker.setText("Successfully added");
                errorChecker.setTextFill(Color.GREEN);
                //reset the fields to empty
                UserId.setText("");
                FirstName.setText("");
                LastName.setText("");
                Class.setText("");
                Faculty.setText("");

            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @FXML
    public void CloseButtonPressed(ActionEvent event) throws IOException {
        try {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/sample/Ereader.fxml"));
            Scene scene = new Scene(root, 900, 600);
            //     scene.getStylesheets().add(getClass().getResource("sample/application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("STUDENTS' LIBRARY ASSISTANT");
            primaryStage.show();
        }catch (IOException e){
            System.out.println(e);

}
	    }
}
