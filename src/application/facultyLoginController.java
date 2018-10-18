package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class facultyLoginController implements Initializable {

    @FXML
    private Label warningLabel;
    @FXML
    private TextField facultyId;
    @FXML
    private PasswordField passwordText;

    @FXML
    private void facultyLogin(){

        if(facultyId.getText().isEmpty() || passwordText.getText().isEmpty()) {
            warningLabel.setText("*Username/Password cannot be empty");
            return;
        }
        if(!facultyId.getText().equals("a") || !passwordText.getText().equals("a")) {
            warningLabel.setText("*Username and/or Password is incorrect");
            return;
        }
        if(facultyId.getText().equals("a") && passwordText.getText().equals("a")){
            try{
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/faculty/dashboard.fxml"));
                AnchorPane root1 = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("DashBoard");
                stage.setScene(new Scene(root1));
                stage.show();
            } catch(Exception e) {
                e.printStackTrace();
            }
            warningLabel.getScene().getWindow().hide();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}