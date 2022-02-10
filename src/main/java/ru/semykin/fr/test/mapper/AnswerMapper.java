package ru.semykin.fr.test.mapper;

import org.mapstruct.Mapper;
import ru.semykin.fr.test.dto.AnswerDto;
import ru.semykin.fr.test.entity.Answer;

import java.util.List;

@Mapper
public interface AnswerMapper {

    AnswerDto toAnswerDto(Answer entity);

    Answer toAnswerEntity(AnswerDto dto);

    List<AnswerDto> toAnswerDtoList(Iterable<Answer> entityIterable);

    List<Answer> toAnswerEntityList(Iterable<AnswerDto> dtoIterable);

}
