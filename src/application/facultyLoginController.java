package application;

import connectivity.Connect;
import faculty.dashboardController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class facultyLoginController implements Initializable {

    @FXML
    private Label warningLabel;
    @FXML
    private TextField facultyId;
    @FXML
    private PasswordField passwordText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void facultyLogin() throws SQLException, IOException {

        Connect connect = new Connect();
        Connection connection = connect.getConnection();

        if(facultyId.getText().isEmpty() || passwordText.getText().isEmpty()) {
            warningLabel.setText("*Username/Password cannot be empty");
            return;
        }
        String password = null;
        String facultyid = null;
        String facultyName = null;
        Statement statement = null;
        String query = "SELECT * FROM `faculty` WHERE `facultyId` LIKE '" + facultyId.getText() +"'";
        try{
            statement = connection.createStatement();
        }
        catch(NullPointerException e){
            warningLabel.setText("*Database not connected");
        }
        ResultSet rs = statement.executeQuery(query);
        while(rs.next()) {
            password = rs.getString("password");
            facultyName = rs.getString("Name");
        }
        if(!passwordText.getText().equals(password)) {
            warningLabel.setText("*Username and/or Password is incorrect");
            return;
        }
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/faculty/dashboard.fxml"));
        AnchorPane root1 = fxmlLoader.load();
        dashboardController control = fxmlLoader.<dashboardController>getController();
        control.getName(facultyName);
        Stage stage = new Stage();
        stage.setTitle("Student Prerequisite Test");
        stage.setScene(new Scene(root1));
        stage.show();
        statement.close();
        warningLabel.getScene().getWindow().hide();
    }

}