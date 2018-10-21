package student;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.Vector;
import connectivity.Connect;

public class instructionPageController implements Initializable {

    String subName;

    int totalQuestions = 0;

    @FXML
    private AnchorPane rootPane;

    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("testPage.fxml"));
    AnchorPane testPane = fxmlLoader.load();
    testPageController control = fxmlLoader.<testPageController>getController();

    @FXML
    Label totalDisplay;

    public instructionPageController() throws IOException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(()->{
            try {
                setText();
            } catch (SQLException e) {}
        });
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



    @FXML
    private void startTest() {
        testPageController control = fxmlLoader.<testPageController>getController();
        control.getSubName(subName);
        control.startTimer();
        control.start();
        rootPane.getChildren().setAll(testPane);
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