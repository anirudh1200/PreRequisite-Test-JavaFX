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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
    public void startMaths(ActionEvent event) throws IOException {
        this.subName = "AM";
        startLoadInstruction(event);
    }

    @FXML
    public void startDataStructure(ActionEvent event) throws IOException {
        this.subName = "DS";
        startLoadInstruction(event);
    }

    @FXML
    public void startDiscreteMath(ActionEvent event) throws IOException {
        this.subName = "DM";
        startLoadInstruction(event);
    }

    @FXML
    public void startOOPM(ActionEvent event) throws IOException {
        this.subName = "OOP";
        startLoadInstruction(event);
    }

    @FXML
    public void startDLDA(ActionEvent event) throws IOException {
        this.subName = "DLDA";
        startLoadInstruction(event);
    }

    @FXML
    public void startECCF(ActionEvent event) throws IOException {
        this.subName = "ECCF";
        startLoadInstruction(event);
    }

    @FXML
    public void logout(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/FirstScene.fxml"));
        AnchorPane root = fxmlLoader.load();
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.setScene(new Scene(root));
    }

    private void startLoadInstruction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("instructionPage.fxml"));
        AnchorPane instructionPane = fxmlLoader.load();
        instructionPageController control = fxmlLoader.<instructionPageController>getController();
        control.getSubjectName(subName);
        control.getUser(username);
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.setScene(new Scene(instructionPane));
    }

    void check() throws SQLException {
        Connect connect = new Connect();
        Connection connection = connect.getConnection();
        String query = "SELECT * FROM `student` WHERE (username = '"+ username +"')";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);
        while(rs.next()){
            if(!rs.getString("AM").equals("-1")){
                ml.setText("Completed");
                mb.setDisable(true);
            }
            if(!rs.getString("DLDA").equals("-1")){
                dl.setText("Completed");
                db.setDisable(true);
            }
            if(!rs.getString("DS").equals("-1")){
                dsl.setText("Completed");
                dsb.setDisable(true);
            }
            if(!rs.getString("DM").equals("-1")){
                dml.setText("Completed");
                dmb.setDisable(true);
            }
            if(!rs.getString("OOP").equals("-1")){
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