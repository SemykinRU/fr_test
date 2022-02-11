package ru.semykin.fr.test.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAnswerDto {

    private Long userResponseId;

    private Long answerId;

    private String answerText;

}
