package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.MultiAnsTask;
import model.TaskBase;
import model.SingleAnsTask;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerBaseWithBtn implements Initializable {

    final String COLUMN_SEPARATOR = ",";
    public Button nextBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    protected void saveCurrentAnswer(String path, TaskBase task) {
        throw new NotImplementedException();
    }

    @FXML
    private void rebuildNextViewForQuestion(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        getNextQuestionLayout(stage);
    }

    private void getNextQuestionLayout(Stage stage) throws IOException {
        if (!ApplicationMain.isCurrentTheLastQuestion()) {
            TaskBase nextQuestion = ApplicationMain.getNextQuestion();
            if (nextQuestion instanceof SingleAnsTask) {
                System.out.println("Prepare data on SingleAnsTask view");
                rebuildSceneForNextQuestion("/singleAnsQuestionScene.fxml", stage, nextQuestion);
            } else if (nextQuestion instanceof MultiAnsTask) {
                System.out.println("Prepare data on MultiAnsTask view.");
                rebuildSceneForNextQuestion("/multiAnsQuestionScene.fxml", stage, nextQuestion);
            }
        } else {
            System.out.println("End of questions. Exiting...");
            rebuildSceneForNextQuestion("/endView.fxml", stage, null);
        }
    }

    private void rebuildSceneForNextQuestion(String viewName, Stage stage, TaskBase nextQuestion) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(viewName));
        Parent root = loader.load();
        if (nextQuestion != null) {
            ControllerBaseWithBtn controller = loader.getController();
            controller.rebuild(nextQuestion);
        }
        stage.setScene(new Scene(root));
        stage.show();
    }


    void rebuild(TaskBase question) {
        throw new NotImplementedException();
    }
}
