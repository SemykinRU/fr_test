package ru.semykin.fr.test.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "users_answers")
@Getter
@Setter
public class UserAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UserResponse userResponse;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Answer answer;

    private String answerText;

}
