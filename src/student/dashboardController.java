package student;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class dashboardController implements Initializable {

    @FXML
    private Label usernameLabel;

    @FXML
    private AnchorPane rootPane;

    String username = null;

    @FXML
    private void startMaths() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("instructionPage.fxml"));
        AnchorPane instructionPane = (AnchorPane) fxmlLoader.load();
        instructionPageController control = fxmlLoader.<instructionPageController>getController();
        control.getSetNum(1);
        rootPane.getChildren().setAll(instructionPane);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            usernameLabel.setText(username);
        });
    }

    public void getUser(String username) {
        this.username = username;
    }

}