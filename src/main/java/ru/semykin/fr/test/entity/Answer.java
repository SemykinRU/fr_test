package ru.semykin.fr.test.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "answer")
@Getter
@Setter
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    private Question question;

}
