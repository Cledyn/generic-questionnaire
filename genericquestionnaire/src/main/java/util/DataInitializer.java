package util;

import com.google.common.collect.Lists;
import model.*;
import model.SingleAnsTask;
import model.TaskBase;

import java.util.List;

public class DataInitializer {

    public List<TaskBase> populateQuestions(String... args) {
        //todo: think about replacing list to queue
        List<Answer> possibleAnswers = Lists.newArrayList(new Answer(1, "Tak"), new Answer(2, "Nie"));
        List<Answer> possibleAnswers1 = Lists.newArrayList(new Answer(1, "ABC"), new Answer(2, "DEF"),
                new Answer(1, "OK!"), new Answer(2, "Nie mam zdania"));
        SingleAnsTask single1 = new SingleAnsTask(0, "Tekst pytania nr 1", possibleAnswers, "single_ans_pyt1.png");
        MultiAnsTask multi1 = new MultiAnsTask(2, "Multi ansQuestion2", possibleAnswers, null);
        MultiAnsTask multi2 = new MultiAnsTask(3, "Jakieś inne pytanie", possibleAnswers1, null);
        MultiAnsTask multi3 = new MultiAnsTask(4, "Pytanie multi: !!!!", possibleAnswers, null);
        SingleAnsTask single2 = new SingleAnsTask(1, "Pytanie otwarte - można opisac odpowiedz", possibleAnswers1, null);
        List<TaskBase> questions = Lists.newArrayList(single1, multi1, multi2, single2, multi3);
        return questions;

    }
}
