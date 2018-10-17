package student;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
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

    int setNum;
    int totalQuestions = 0;

    @FXML
    private AnchorPane rootPane;

    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("testPage.fxml"));
    AnchorPane testPane = fxmlLoader.load();

    @FXML
    Label totalDisplay;

    public instructionPageController() throws IOException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            getQuestions();
        } catch (SQLException e) {}
        try {
            setQuestions();
        } catch (IOException e) {}

    }

    public void getSetNum(int setNum) {
        this.setNum = setNum;
    }

    public Vector<Question> questions = new Vector<Question>();

    private void getQuestions() throws SQLException {
        Connect connect = new Connect();
        Connection connection = connect.getConnection();
        String query = "SELECT * FROM `question` WHERE (setNum = 1)";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);
        Question q1 = new Question();
        while(rs.next()) {
            q1.getQuestion(rs.getString("data"));
            q1.getAns(rs.getInt("ans"));
            questions.addElement(q1);
            this.totalQuestions++;
        }
        totalDisplay.setText("Number of Questions: " + totalQuestions);
    }

    private void setQuestions() throws IOException {
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        Label questionLabel[] = new Label[totalQuestions];
        RadioButton questionBtn[] = new RadioButton[4*totalQuestions];
        for(int i=0; i<totalQuestions; i++) {
            questionLabel[i] = new Label(questions.elementAt(i).question);
            questionBtn[(i*4)+0] = new RadioButton("a");
            questionBtn[(i*4)+1] = new RadioButton("b");
            questionBtn[(i*4)+2] = new RadioButton("c");
            questionBtn[(i*4)+3] = new RadioButton("d");
            vbox.getChildren().addAll(questionLabel[i], questionBtn[(i*4)+0], questionBtn[(i*4)+1], questionBtn[(i*4)+2], questionBtn[(i*4)+3]);
        }
        this.testPane.getChildren().add(vbox);
    }

    @FXML
    private void startTest() throws IOException {
        testPageController control = fxmlLoader.<testPageController>getController();
        control.startTimer();
        rootPane.getChildren().setAll(testPane);
    }

}

class Question {
    String question;
    int ans;

    void getQuestion(String question) {
        this.question = question;
    }

    void getAns(int ans) {
        this.ans = ans;
    }

}