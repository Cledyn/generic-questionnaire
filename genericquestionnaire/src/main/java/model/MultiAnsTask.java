package model;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class MultiAnsTask extends TaskBase {

    protected final List<Answer> possibleAnswers;
    private List<Answer> answer;

    public MultiAnsTask(int id, String questionText, List<Answer> possibleAnswers, String imgFileName) {
        super(id, questionText, imgFileName);
        this.possibleAnswers = possibleAnswers;
        this.answer = Lists.newArrayList();
    }

    @Override
    String getAnswerReprToString() {
        return "EMPTY SYR IN MULTIANS Q";
    }

    @Override
    public String getQuestionType() {
        return "MultiAns";
    }
}
