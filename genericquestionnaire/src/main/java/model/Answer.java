package model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Answer {

    private final int number;
    private final String text;

    public String getFormattedAnswer(){
        return number+". "+text;
    }
}
