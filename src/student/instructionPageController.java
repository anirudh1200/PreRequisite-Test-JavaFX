package student;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
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

    String subName;
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

    private void getQuestions() throws SQLException {
        Connect connect = new Connect();
        Connection connection = connect.getConnection();
        System.out.println(subName);
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
        totalDisplay.setText("Number of Questi+ons: " + totalQuestions);
    }

    private void setQuestions() {
        Label displayName = new Label("Subject: "+subName);

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(100, 20, 50, 20));
        vbox.setSpacing(10);
        Label questionLabel[] = new Label[totalQuestions];
        RadioButton questionBtn[] = new RadioButton[4*totalQuestions];
        for(int i=0; i<totalQuestions; i++) {
            questionLabel[i] = new Label(questions.elementAt(i).question);
            questionBtn[(i*4)+0] = new RadioButton(questions.elementAt(i).option1);
            questionBtn[(i*4)+1] = new RadioButton(questions.elementAt(i).option2);
            questionBtn[(i*4)+2] = new RadioButton(questions.elementAt(i).option3);
            questionBtn[(i*4)+3] = new RadioButton(questions.elementAt(i).option4);
            vbox.getChildren().addAll(questionLabel[i], questionBtn[(i*4)+0], questionBtn[(i*4)+1], questionBtn[(i*4)+2], questionBtn[(i*4)+3]);
        }
        Button submit = new Button("Submit");
        vbox.getChildren().add(submit);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("testPage.fxml"));
        testPageController control = fxmlLoader.<testPageController>getController();
        this.testPane.getChildren().addAll(displayName, vbox);
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