package ru.semykin.fr.test.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserResponseDto {

    private Long id;

    private LocalDateTime createDate;

    private Long userId;

    private Long pollId;

}
