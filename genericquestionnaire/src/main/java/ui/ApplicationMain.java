package ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.TaskBase;
import util.DataInitializer;

import java.util.List;

public class ApplicationMain extends Application {

    private final static List<TaskBase> QUESTIONS = new DataInitializer().populateQuestions(); //todo: replace with QUEUE, remove CURRENT_QUESTION_ID
    private static int CURRENT_QUESTION_ID = -1;
    public static String filePath = "results.csv";


    @Override
    public void start(final Stage primaryStage) throws Exception {
        final Parent root = FXMLLoader.load(getClass().getResource("/startView.fxml"));
        primaryStage.setTitle("Idioms - questionnaire");
        primaryStage.setScene(new Scene(root, Color.ALICEBLUE));
        primaryStage.setOnCloseRequest(event -> Platform.exit());
        primaryStage.setMaximized(true);
        primaryStage.show();

    }

    static boolean isCurrentTheLastQuestion() {
        return (QUESTIONS.size() - 1 == CURRENT_QUESTION_ID) || QUESTIONS.size() == 0;
    }

    static TaskBase getNextQuestion() {
        return QUESTIONS.get(++CURRENT_QUESTION_ID);
    }

    static TaskBase getCurrentTask() {
        return CURRENT_QUESTION_ID >= 0 ? QUESTIONS.get(CURRENT_QUESTION_ID) : null;
    }
}
