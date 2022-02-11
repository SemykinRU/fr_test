package ru.semykin.fr.test.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "users_answers")
@Getter
@Setter
public class UserAnswer {

    @Id
    private Long userResponseId;

    private Long answerId;

    private String answerText;

}
