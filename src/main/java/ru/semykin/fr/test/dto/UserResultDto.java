package ru.semykin.fr.test.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResultDto {

    private UserResponseDto userResponseDto;

    private PollDto pollDto;
}
