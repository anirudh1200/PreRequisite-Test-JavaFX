package application;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static application.Main.primaryStage;

public class Controller implements Initializable {

    @FXML
    private AnchorPane rootPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void closeWindow() {
        Platform.exit();
    }

    @FXML
    private void loadStudentLogin() throws Exception {
        AnchorPane studentLoginPane = FXMLLoader.load(getClass().getResource("studdentLogin.fxml"));
        rootPane.getChildren().setAll(studentLoginPane);
    }

    @FXML
    private void loadFacultyLogin() throws Exception {
        AnchorPane facultyLoginPane = FXMLLoader.load(getClass().getResource("facultyLogin.fxml"));
        rootPane.getChildren().setAll(facultyLoginPane);
    }

    public void showAbout() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("about.fxml"));
        AnchorPane root1 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("About");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(primaryStage);
        stage.setScene(new Scene(root1));
        stage.showAndWait();
    }

}