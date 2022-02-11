package ru.semykin.fr.test.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "poll")
@Getter
@Setter
public class Poll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private LocalDateTime timeStart;

    private LocalDateTime timeEnd;

    private String description;

    @OneToMany(mappedBy = "poll", fetch = FetchType.LAZY)
    private List<Question> questions;

}
