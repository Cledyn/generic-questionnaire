package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.TaskBase;
import model.SingleAnsTask;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

//OPEN QUESTION
public class SingleAnsQuestionController extends ControllerBaseWithBtn {
    public TextField questionTextField;
    public TextArea ansTextArea;

    @FXML
    private Button saveBtn;

    @FXML
    private ImageView questionImgView;


    @FXML
    private void saveCurrentAnswer(ActionEvent event) throws IOException {
        SingleAnsTask task = (SingleAnsTask) ApplicationMain.getCurrentTask();
        task.calcAnswerTime();
        String content = task.getId() + COLUMN_SEPARATOR + ansTextArea.getText() + COLUMN_SEPARATOR + task.getAnswerTime(); //todo: it is needed to get selected answer
        Files.write(Paths.get(ApplicationMain.filePath), (content + System.lineSeparator()).getBytes(Charset.forName("UTF-8")), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        saveBtn.setDisable(true);
        nextBtn.setDisable(false);
    }

    @Override
    void rebuild(TaskBase question) {
        nextBtn.setDisable(true);
        SingleAnsTask singleAnsQuestion = (SingleAnsTask) question;
        if (singleAnsQuestion.getImgFileName() == null) {
            questionTextField.setText(question.getFormattedQuestionText());
        } else {
            Image image = new Image("/" + singleAnsQuestion.getImgFileName());
            questionImgView.setImage(image);
            questionImgView.setVisible(true);
        }
        singleAnsQuestion.setAnswerStartTime();

        System.out.println("Done rebuilding SingleAnsTask");
    }
}
