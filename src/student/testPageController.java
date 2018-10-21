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


    Timer timer = new Timer();

    void startTimer(){
        this.timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Running");
            }
        }, 100L, 1000L);
    }

    void stopTimer(){
        this.timer.cancel();
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
            stopTimer();
            evaluate();
            changeScene();
        }
    }

    void evaluate(){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}