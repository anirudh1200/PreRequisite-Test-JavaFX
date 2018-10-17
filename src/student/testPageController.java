package student;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class testPageController implements Initializable {

    @FXML
    VBox vbox;

    @FXML
    AnchorPane rootPane;

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
        AnchorPane facultyLoginPane = FXMLLoader.load(getClass().getResource("facultyLogin.fxml"));
        rootPane.getChildren().setAll(facultyLoginPane);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Button submit = new Button("Submit");
        vbox.getChildren().add(submit);
        submit.setOnAction(e -> {
            AlertBox.display("Finish Test", "Are you sure you want to submit?");
        });

    }

}