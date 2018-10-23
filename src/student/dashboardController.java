package student;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import connectivity.Connect;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class dashboardController implements Initializable {

    @FXML
    private Label usernameLabel;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Label ml;
    @FXML
    private Label dsl;
    @FXML
    private Label dml;
    @FXML
    private Label ol;
    @FXML
    private Label el;
    @FXML
    private Label dl;
    @FXML
    private Button mb;
    @FXML
    private Button db;
    @FXML
    private Button dsb;
    @FXML
    private Button dmb;
    @FXML
    private Button ob;
    @FXML
    private Button eb;

    String subName;
    String username = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            usernameLabel.setText("Roll No.: " + username);
            try {
                check();
            } catch (SQLException e) {}
        });
    }

    @FXML
    private void startMaths() throws IOException {
        this.subName = "Applied Mathematics-3";
        startLoadInstruction();
    }

    @FXML
    private void startDataStructure() throws IOException {
        this.subName = "Data Structures";
        startLoadInstruction();
    }

    @FXML
    private void startDiscreteMath() throws IOException {
        this.subName = "Discrete Mathematics";
        startLoadInstruction();
    }

    @FXML
    private void startOOPM() throws IOException {
        this.subName = "Object Oriented Programming";
        startLoadInstruction();
    }

    @FXML
    private void startDLDA() throws IOException {
        this.subName = "DLDA";
        startLoadInstruction();
    }

    @FXML
    private void startECCF() throws IOException {
        this.subName = "ECCF";
        startLoadInstruction();
    }

    @FXML
    public void logout() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/FirstScene.fxml"));
        AnchorPane root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Login");
        stage.setScene(new Scene(root));
        stage.show();
        ml.getScene().getWindow().hide();
    }

    private void startLoadInstruction() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("instructionPage.fxml"));
        AnchorPane instructionPane = fxmlLoader.load();
        instructionPageController control = fxmlLoader.<instructionPageController>getController();
        control.getSubjectName(subName);
        control.getUser(username);
        rootPane.getChildren().setAll(instructionPane);
    }

    void check() throws SQLException {
        Connect connect = new Connect();
        Connection connection = connect.getConnection();
        String query = "SELECT * FROM `student` WHERE (username = '"+ username +"')";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);
        while(rs.next()){
            if(!rs.getString("Applied Mathematics-3").equals("-1")){
                ml.setText("Completed");
                mb.setDisable(true);
            }
            if(!rs.getString("DLDA").equals("-1")){
                dl.setText("Completed");
                db.setDisable(true);
            }
            if(!rs.getString("Data Structures").equals("-1")){
                dsl.setText("Completed");
                dsb.setDisable(true);
            }
            if(!rs.getString("Discrete Mathematics").equals("-1")){
                dml.setText("Completed");
                dmb.setDisable(true);
            }
            if(!rs.getString("Object Oriented Programming").equals("-1")){
                ol.setText("Completed");
                ob.setDisable(true);
            }
            if(!rs.getString("ECCF").equals("-1")){
                el.setText("Completed");
                eb.setDisable(true);
            }
        }
        statement.close();
        connection.close();
    }

    public void getUser(String username) {
        this.username = username;
    }

}