package ui;

import com.google.common.collect.Lists;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.Answer;
import model.MultiAnsTask;
import model.TaskBase;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;


//SELECTION QUESTION (single selection for now)
public class MultiAnsQuestionController extends ControllerBaseWithBtn {
    public TextField questionTextField;
    private MultiAnsTask currentTask = null;

    @FXML
    private Button saveBtn;

    @FXML
    private GridPane questionImgPane;

    @FXML
    private GridPane gridPane;

    @FXML
    private ToggleGroup allPossibleAns;


    @FXML
    private void saveCurrentAnswer(ActionEvent event) throws IOException {
        this.currentTask.calcAnswerTime();
        Answer selected = findSelectedAnswer();
        String content = this.currentTask.getId() + COLUMN_SEPARATOR + selected.getNumber() + COLUMN_SEPARATOR + selected.getText() + COLUMN_SEPARATOR + currentTask.getAnswerTime();
        Files.write(Paths.get(ApplicationMain.filePath), (content + System.lineSeparator()).getBytes(Charset.forName("UTF-8")), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        saveBtn.setDisable(true);
        nextBtn.setDisable(false);
    }


    @Override
    void rebuild(TaskBase task) {
        nextBtn.setDisable(true);
        MultiAnsTask multiAnsTask = (MultiAnsTask) task;
        questionTextField.setText(task.getFormattedQuestionText());
        buildAnswers(multiAnsTask.getPossibleAnswers()); //todo: ensure answers are sorted (Maybe on startup, after creating objects from file)
        System.out.println("Done rebuilding MultiAnsTask");
        this.currentTask = multiAnsTask;
        this.currentTask.setAnswerStartTime();
    }


    private void buildAnswers(List<Answer> possibleAnswers) {
        List<RadioButton> answers = Lists.newArrayList();
        possibleAnswers.forEach(answer -> {
            System.out.println("Answer text: " + answer.getFormattedAnswer());
            RadioButton rb = new RadioButton();
            rb.setText(answer.getFormattedAnswer());
            rb.setToggleGroup(allPossibleAns);
            answers.add(rb);
            gridPane.add(rb, 0, answers.size() - 1);

        });
    }

    private Answer findSelectedAnswer() {
        RadioButton selectedRb = (RadioButton) gridPane.getChildren().stream().filter(node -> {
            ToggleButton selection = (ToggleButton) node;
            return selection.isSelected();
        }).collect(Collectors.toList()).get(0);
        String rbValue = selectedRb.getText();
        Answer selected = this.currentTask.getPossibleAnswers().stream().filter(answer -> answer.getFormattedAnswer().equals(rbValue)).collect(Collectors.toList()).get(0);
        return selected;
    }
}
