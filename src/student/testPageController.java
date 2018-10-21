package student;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.swing.text.html.FormSubmitEvent;

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

    int interval = 1200;

    Timer timer = new Timer();

    public void startTimer() {
        this.timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if(interval > 0)
                {
                    Platform.runLater(() -> timeLabel.setText("Time Remaining: "+(interval/60) +":"+(interval%60)));
                    System.out.println(interval);
                    interval--;
                }
                else{
                    try {
                        submit();
                    } catch (IOException e) {}
                    timer.cancel();
                }
            }
        }, 1000,1000);
    }

    void changeScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
        AnchorPane dashboard = fxmlLoader.load();
        rootPane.getChildren().setAll(dashboard);
    }

    @FXML
    private void handleSubmit() throws IOException {
        boolean result = ConfirmBox.display("Submit", "Are you sure you want to submit?");
        if(result == true){
            submit();
        }
    }

    void submit() throws IOException {
        this.timer.cancel();
        evaluate();
        changeScene();
    }

    void evaluate(){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

}