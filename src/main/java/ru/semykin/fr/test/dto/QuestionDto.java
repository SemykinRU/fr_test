package ru.semykin.fr.test.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuestionDto {

    private Long id;

    private String title;

    private String type;

    private List<AnswerDto> answers;

}
