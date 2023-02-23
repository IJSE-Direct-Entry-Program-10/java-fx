package lk.ijse.dep10.fx.controller;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class ProgressSceneController {

    @FXML
    private Button btnRun;

    @FXML
    private Label lblStatus;

    @FXML
    private ProgressBar prg;

    @FXML
    void btnRunOnAction(ActionEvent event) {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                for (int i = 0; i < 1000; i++) {
                    System.out.println(i);
                    Thread.sleep(50);
                    updateMessage(String.format("%.2f", (i / 1000.0 * 100)) + "% Complete");
                    updateProgress(i, 1000.0);
                }
                System.out.println(Thread.currentThread().getName());
                return null;
            }
        };
        new Thread(task).start();

        lblStatus.textProperty().bind(task.messageProperty());
        prg.progressProperty().bind(task.progressProperty());
//        lblStatus.setText(progress + "% Complete");
//        prg.setProgress(progress);
    }

}
