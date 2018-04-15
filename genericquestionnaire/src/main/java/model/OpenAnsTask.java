package model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class OpenAnsTask extends TaskBase {

    private String answer;

    public OpenAnsTask(int id, String questionText, String imgFileName) {
        super(id, questionText, imgFileName);
        this.answer = "";
    }
    @Override
    String getAnswerReprToString() {
        return null;
    }

    @Override
    public String getQuestionType() {
        return null;
    }
}
