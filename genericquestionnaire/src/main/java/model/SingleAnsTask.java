package model;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SingleAnsTask extends TaskBase {


    protected final List<Answer> possibleAnswers;
    private Answer answer;

    public SingleAnsTask(int id, String questionText, List<Answer> possibleAnswers, String imgFileName) {
        super(id, questionText, imgFileName);
        this.possibleAnswers = possibleAnswers;
        this.answer = null;
    }


    @Override
    String getAnswerReprToString() {
        return "EMPTY STR SINGLE ANS Q";
    }

    @Override
    public String getQuestionType() {
        return "SingleAns";
    }


}
