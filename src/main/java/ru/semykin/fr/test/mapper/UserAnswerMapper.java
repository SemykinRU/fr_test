package ru.semykin.fr.test.mapper;

import org.mapstruct.Mapper;
import ru.semykin.fr.test.dto.UserAnswerDto;
import ru.semykin.fr.test.entity.UserAnswer;

import java.util.List;

@Mapper
public interface UserAnswerMapper {

    UserAnswerDto toUserAnswerDto(UserAnswer entity);

    UserAnswer toUserAnswerEntity(UserAnswerDto dto);

    List<UserAnswerDto> toUserAnswerDtoList(Iterable<UserAnswer> entityIterable);

    List<UserAnswer> toUserAnswerEntityList(Iterable<UserAnswerDto> dtoIterable);
}
