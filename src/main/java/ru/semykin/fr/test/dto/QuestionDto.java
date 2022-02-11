package ru.semykin.fr.test.dto;

import lombok.Getter;
import lombok.Setter;
import ru.semykin.fr.test.entity.QuestionType;

import java.util.List;

@Getter
@Setter
public class QuestionDto {

    private Long id;

    private String title;

    private QuestionType type;

    private List<AnswerDto> answers;

}
