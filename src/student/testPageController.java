package student;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import connectivity.Connect;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class testPageController implements Initializable {

    @FXML
    AnchorPane rootPane;
    @FXML
    Button submitBtn;
    @FXML
    VBox questionVbox;
    @FXML
    Label titleLabel;
    @FXML
    Label timeLabel;

    int totalQuestions = 0;
    String subName;
    int totalMarks=0;
    int interval = 1200;
    Timer timer = new Timer();
    int percentage;
    String username;
    public Vector<Question> questions = new Vector<Question>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(()->{
            start();
        });
    }

    @FXML
    private void handleSubmit() throws IOException, SQLException {
        boolean result = ConfirmBox.display("Submit", "Are you sure you want to submit?");
        if(result == true){
            submit();
        }
    }

    void start(){
        try {
            getQuestions();
        } catch (SQLException e) {}
        setQuestions();
    }

    public void startTimer() {
        this.timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if(interval > 0)
                {
                    Platform.runLater(() -> timeLabel.setText("Time Remaining: "+(interval/60) +":"+(interval%60)));
                    interval--;
                }
                else{
                    try {
                        submit();
                    } catch (IOException | SQLException e) {}
                    timer.cancel();
                }
            }
        }, 1000,1000);
    }

    void changeScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
        AnchorPane dashboard = fxmlLoader.load();
        dashboardController control = fxmlLoader.<dashboardController>getController();
        control.getUser(username);
        rootPane.getChildren().setAll(dashboard);
    }

    void submit() throws IOException, SQLException {
        this.timer.cancel();
        evaluate();
        saveMarks();
        changeScene();
    }

    void saveMarks() throws SQLException {
        Connect connect = new Connect();
        Connection connection = connect.getConnection();
        PreparedStatement updateEXP = connection.prepareStatement("update`student` set `"+subName+"` = '"+percentage+"'  where `username` = '"+username+"'");
        updateEXP.executeUpdate();
    }

    void getSubName(String subName){
        this.subName = subName;
    }

    void getQuestions() throws SQLException {
        Connect connect = new Connect();
        Connection connection = connect.getConnection();
        System.out.println("SUBNAME"+subName);
        String query = "SELECT * FROM `question` WHERE (subject = '"+ subName +"')";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);
        Question q1[] = new Question[20];
        int i=0;
        while(rs.next()) {
            q1[i] = new Question();
            q1[i].getQuestion(rs.getString("data"));
            q1[i].getAns(rs.getString("ans"));
            q1[i].getOptions(rs.getString("option1"), rs.getString("option2"), rs.getString("option3"), rs.getString("option4"));
            questions.addElement(q1[i]);
            this.totalQuestions++;
            i++;
        }
        statement.close();
        connection.close();
    }

    void setQuestions() {
        ScrollPane sp = new ScrollPane();
        Label questionLabel[] = new Label[totalQuestions];
        RadioButton questionBtn[] = new RadioButton[4*totalQuestions];
        ToggleGroup tg[] = new ToggleGroup[totalQuestions];
        for(int i=0; i<totalQuestions; i++) {
            System.out.println(questions.elementAt(i).ans);
            questionLabel[i] = new Label((i+1)+ ". " +questions.elementAt(i).question);
            questionBtn[(i*4)+0] = new RadioButton(questions.elementAt(i).option[0]);
            questionBtn[(i*4)+1] = new RadioButton(questions.elementAt(i).option[1]);
            questionBtn[(i*4)+2] = new RadioButton(questions.elementAt(i).option[2]);
            questionBtn[(i*4)+3] = new RadioButton(questions.elementAt(i).option[3]);
            tg[i] = new ToggleGroup();
            questionBtn[(i*4)+0].setToggleGroup(tg[i]);
            questionBtn[(i*4)+1].setToggleGroup(tg[i]);
            questionBtn[(i*4)+2].setToggleGroup(tg[i]);
            questionBtn[(i*4)+3].setToggleGroup(tg[i]);
            int finalI = i;
            tg[i].selectedToggleProperty().addListener(new ChangeListener<Toggle>()
            {
                @Override
                public void changed(ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1)
                {

                    RadioButton chk = (RadioButton)t1.getToggleGroup().getSelectedToggle(); // Cast object to radio button
                    if(chk.getText().equals("option"+questions.elementAt(finalI).ans)){
                        totalMarks++;
                    }
                }
            });
            questionVbox.getChildren().addAll(questionLabel[i], questionBtn[(i*4)+0], questionBtn[(i*4)+1], questionBtn[(i*4)+2], questionBtn[(i*4)+3]);
        }
        sp.setContent(questionVbox);
        sp.setPrefSize(100,100);
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
    }

    void evaluate() {
        percentage = totalMarks*100/totalQuestions;
    }

    public void getUser(String username) {
        this.username = username;
    }
}