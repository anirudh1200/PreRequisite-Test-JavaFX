package faculty;

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
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class dashboardController implements Initializable {

    @FXML
    private Label facultyName;
    @FXML
    private Label displayTotal;
    @FXML
    private Label displayCompleted;
    @FXML
    private Label displayIncomplete;
    @FXML
    private Label displayAllFailed;
    @FXML
    private Label displayPartiallyFailed;

    String facultyname;
    int totalStudents = 0;
    int allCompletedStudents = 0;
    int allIncompleteStudents = 0;
    int allFailed = 0;
    int partialFailed = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            facultyName.setText("Name: " + facultyname);
        });
        try {
            getStatistics();
        } catch (SQLException e) {}
        displayStatistics();
    }

    @FXML
    private void refresh() throws SQLException {
        totalStudents = 0;
        allCompletedStudents = 0;
        allIncompleteStudents = 0;
        allFailed = 0;
        partialFailed = 0;
        getStatistics();
        displayStatistics();
    }

    @FXML
    public void logout() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/FirstScene.fxml"));
        AnchorPane root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Login");
        stage.setScene(new Scene(root));
        stage.show();
        facultyName.getScene().getWindow().hide();
    }

    private void getStatistics() throws SQLException {
        Connect connect = new Connect();
        Connection connection = connect.getConnection();
        String query = "SELECT * FROM `student` WHERE 1";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);
        while(rs.next()){
            totalStudents++;
            boolean completed = (!rs.getString("AM").equals("-1") && !rs.getString("DLDA").equals("-1") && !rs.getString("DS").equals("-1") && !rs.getString("DM").equals("-1") && !rs.getString("OOP").equals("-1") && !rs.getString("ECCF").equals("-1"));
            if(completed){
                allCompletedStudents++;
            }
            boolean incomplete = (rs.getString("AM").equals("-1") && rs.getString("DLDA").equals("-1") && rs.getString("DS").equals("-1") && rs.getString("DM").equals("-1") && rs.getString("OOP").equals("-1") && rs.getString("ECCF").equals("-1"));
            if(incomplete){
                allIncompleteStudents++;
            }
            boolean fail = (rs.getInt("AM")<40 && rs.getInt("DLDA")<40 && rs.getInt("DS")<40 && rs.getInt("DM")<40 && rs.getInt("OOP")<40 && rs.getInt("ECCF")<40);
            if(fail){
                allFailed++;
            }
            boolean paritalFail = (rs.getInt("AM")<40 || rs.getInt("DLDA")<40 || rs.getInt("DS")<40 || rs.getInt("DM")<40 || rs.getInt("OOP")<40 || rs.getInt("ECCF")<40);
            if(paritalFail ){
                partialFailed++;
            }
        }
        statement.close();
        connection.close();
    }

    private void displayStatistics(){
        displayTotal.setText("Total Students: " + totalStudents);
        displayCompleted.setText("Number of students who have completed all tests: " + allCompletedStudents + " (" + (allCompletedStudents*100/totalStudents) + "%)");
        displayIncomplete.setText("Number of students who have not given any test: " + allIncompleteStudents + " (" + (allIncompleteStudents*100/totalStudents) + "%)");
        displayAllFailed.setText("Number of students who have failed in all tests: " + allFailed + " (" + (allFailed*100/totalStudents) + "%)");
        int partial = partialFailed-allFailed;
        displayPartiallyFailed.setText("Number of students who have failed in 1 or more but not all: " + partial +" (" + partial*100/totalStudents + "%)");
    }

    public void getName(String facultyname) {
        this.facultyname = facultyname;
    }

}