package ru.semykin.fr.test.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "user_answer")
@Getter
@Setter
public class UserAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Answer answer;

    private String answerText;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private UserResponse userResponse;

}
