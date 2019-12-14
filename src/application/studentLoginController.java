package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import student.dashboardController;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import connectivity.Connect;

public class studentLoginController implements Initializable {

    @FXML
    private Label warningLabel;
    @FXML
    private TextField rollNoText;
    @FXML
    private PasswordField passwordText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void studentLogin(ActionEvent event) throws SQLException, IOException {

        Connect connect = new Connect();
        Connection connection = connect.getConnection();

        if(rollNoText.getText().isEmpty() || passwordText.getText().isEmpty()) {
            warningLabel.setText("*Username/Password cannot be empty");
            return;
        }
        String password = null;
        String username = null;
        Statement statement = null;
        String query = "SELECT * FROM `student` WHERE `username` LIKE '" + rollNoText.getText() +"'";
        try{
            statement = connection.createStatement();
        }
        catch(NullPointerException e){
            warningLabel.setText("*Database not connected");
        }
        ResultSet rs = statement.executeQuery(query);
        while(rs.next()) {
            username = rs.getString("username");
            password = rs.getString("password");
        }

        if(!passwordText.getText().equals(password)) {
            warningLabel.setText("*Username and/or Password is incorrect");
            return;
        }

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/student/dashboard.fxml"));
        AnchorPane root1 = fxmlLoader.load();
        dashboardController control = fxmlLoader.<dashboardController>getController();
        control.getUser(username);
        statement.close();
        connection.close();
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.setScene(new Scene(root1));
        primaryStage.setTitle("Student");
    }

    @FXML
    private void onClickBack(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("FirstScene.fxml"));
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.setScene(new Scene(pane));
    }

}