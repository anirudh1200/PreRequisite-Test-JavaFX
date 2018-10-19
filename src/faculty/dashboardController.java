package faculty;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class dashboardController implements Initializable {

    @FXML
    private Label facultyName;


    String facultyname;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            facultyName.setText(facultyname);
        });
    }

    public void getName(String facultyname) {
        this.facultyname = facultyname;
    }

}