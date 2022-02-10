package ru.semykin.fr.test.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import ru.semykin.fr.test.entity.Question;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PollDto {

    private Long id;

    private String title;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Europe/Moscow")
    private LocalDateTime timeStart;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Europe/Moscow")
    private LocalDateTime timeEnd;

    private String description;

    private List<QuestionDto> questions;

}
