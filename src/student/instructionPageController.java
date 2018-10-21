package student;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
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
        Platform.runLater(() -> {
            try {
                getQuestions();
            } catch (SQLException e) {}
            setQuestions();
        });
    }

    public void getSubjectName(String subName) {
        this.subName = subName;
    }

    public Vector<Question> questions = new Vector<Question>();

    void getQuestions() throws SQLException {
        Connect connect = new Connect();
        Connection connection = connect.getConnection();
        String query = "SELECT * FROM `question` WHERE (subject = '"+ subName +"')";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);
        Question q1 = new Question();
        while(rs.next()) {
            q1.getQuestion(rs.getString("data"));
            q1.getAns(rs.getInt("ans"));
            q1.getOptions(rs.getString("option1"), rs.getString("option2"), rs.getString("option3"), rs.getString("option4"));
            questions.addElement(q1);
            this.totalQuestions++;
        }
        System.out.println(subName);
        System.out.println("total questions "+totalQuestions);
        totalDisplay.setText("Number of Questions: " + totalQuestions);
        statement.close();
        connection.close();
    }

    void setQuestions() {
        VBox vbox = control.questionVbox;
        vbox.setSpacing(10);
        Label questionLabel[] = new Label[totalQuestions];
        RadioButton questionBtn[] = new RadioButton[4*totalQuestions];
        ToggleGroup tg[] = new ToggleGroup[totalQuestions];
        for(int i=0; i<totalQuestions; i++) {
            questionLabel[i] = new Label(questions.elementAt(i).question);
            questionBtn[(i*4)+0] = new RadioButton(questions.elementAt(i).option1);
            questionBtn[(i*4)+1] = new RadioButton(questions.elementAt(i).option2);
            questionBtn[(i*4)+2] = new RadioButton(questions.elementAt(i).option3);
            questionBtn[(i*4)+3] = new RadioButton(questions.elementAt(i).option4);
            tg[i] = new ToggleGroup();
            questionBtn[(i*4)+0].setToggleGroup(tg[i]);
            questionBtn[(i*4)+1].setToggleGroup(tg[i]);
            questionBtn[(i*4)+2].setToggleGroup(tg[i]);
            questionBtn[(i*4)+3].setToggleGroup(tg[i]);
            vbox.getChildren().addAll(questionLabel[i], questionBtn[(i*4)+0], questionBtn[(i*4)+1], questionBtn[(i*4)+2], questionBtn[(i*4)+3]);
        }
    }

    @FXML
    private void startTest() {
        testPageController control = fxmlLoader.<testPageController>getController();
        control.startTimer();
        rootPane.getChildren().setAll(testPane);
    }

}

class Question {
    String question;
    int ans;
    String option1;
    String option2;
    String option3;
    String option4;

    void getQuestion(String question) {
        this.question = question;
    }

    void getAns(int ans) {
        this.ans = ans;
    }

    void getOptions(String option1, String option2, String option3, String option4){
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
    }
}