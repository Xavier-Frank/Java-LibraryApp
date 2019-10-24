package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root=FXMLLoader.load(getClass().getResource("/sample/Ereader.fxml"));
            Scene scene = new Scene(root,900,600);
       //     scene.getStylesheets().add(getClass().getResource("sample/application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("STUDENTS' LIBRARY ASSISTANT");
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {

        launch(args);


    }
}
