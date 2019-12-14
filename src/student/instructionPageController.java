package student;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import connectivity.Connect;
import javafx.stage.Stage;

public class instructionPageController implements Initializable {

    @FXML
    private AnchorPane rootPane;

    String subName;
    String username;
    int totalQuestions = 0;
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("testPage.fxml"));
    AnchorPane testPane = fxmlLoader.load();
    testPageController control = fxmlLoader.<testPageController>getController();

    @FXML
    Label totalDisplay;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(()->{
            try {
                setText();
            } catch (SQLException e) {}
        });
    }

    @FXML
    private void startTest(ActionEvent event) {
        testPageController control = fxmlLoader.<testPageController>getController();
        control.getSubName(subName);
        control.getUser(username);
        control.startTimer();
        control.start();
        Stage questionsStage = new Stage();
        questionsStage.setScene(new Scene(testPane));
        questionsStage.show();
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.close();
    }

    public instructionPageController() throws IOException {
    }

    public void getSubjectName(String subName) {
        this.subName = subName;
    }

    void setText() throws SQLException {
        Connect connect = new Connect();
        Connection connection = connect.getConnection();
        String query = "SELECT * FROM `question` WHERE (subject = '"+ subName +"')";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);
        while(rs.next()) {
            this.totalQuestions++;
        }
        statement.close();
        connection.close();
        totalDisplay.setText("Number of Questions: " + totalQuestions);
    }

    public void getUser(String username) {
        this.username = username;
    }

}

class Question {
    String question;
    String ans;
    String[] option = new String[4];

    void getQuestion(String question) {
        this.question = question;
    }

    void getAns(String ans) {
        this.ans = ans;
    }

    void getOptions(String option1, String option2, String option3, String option4){
        this.option[0] = option1;
        this.option[1] = option2;
        this.option[2] = option3;
        this.option[3] = option4;
    }
}