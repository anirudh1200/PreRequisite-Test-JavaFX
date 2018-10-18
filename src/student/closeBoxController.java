package student;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class closeBoxController implements Initializable {

    @FXML
    private Button cancelBtn;
    @FXML
    private Button sumbitBtn;

    public void cancelAction(){
        closeWindow();
    }

    public void submit(){
        System.out.println("Subimitted");
    }

    public void closeWindow(){
        cancelBtn.getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}