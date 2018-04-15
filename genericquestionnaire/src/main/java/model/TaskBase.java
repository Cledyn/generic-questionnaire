package model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;

@Setter
@Getter
@ToString
public abstract class TaskBase {

    protected final int id;
    protected final String questionText;
    protected final String imgFileName;
    protected long answerTime;

    public TaskBase(int id, String questionText, String imgFileName) {
        this.id = id;
        this.questionText = questionText;
        this.imgFileName = imgFileName;
        this.answerTime = 0L;
    }

    public String getFormattedQuestionText() {
        return id + " ." + questionText;
    }

    public void setAnswerStartTime() {
        Instant instant = Instant.now();
        this.answerTime = instant.toEpochMilli();
    }

    public void calcAnswerTime() {
        this.answerTime = Instant.now().toEpochMilli() - this.answerTime;
    }

    abstract String getAnswerReprToString();

    public abstract String getQuestionType();

}
