package ru.semykin.fr.test.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

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

    @Immutable
    private LocalDateTime timeStart;

    private LocalDateTime timeEnd;

    private String description;

    @OneToMany(mappedBy = "poll", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Question> questions;


}
