package application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;

public class aboutController implements Initializable{

    @FXML
    private Label about;

    @Override
    public void initialize(URL location, ResourceBundle resources){

    }

    @FXML
    private void closeAbout() {
        about.getScene().getWindow().hide();
        return;
    }

}