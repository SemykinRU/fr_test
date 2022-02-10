package ru.semykin.fr.test.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.semykin.fr.test.dto.QuestionDto;
import ru.semykin.fr.test.entity.Question;

import java.util.List;

@Mapper(uses = {AnswerMapper.class})
public interface QuestionMapper {

    @Mapping(target = "answers", ignore = true)
    Question toQuestionEntity(QuestionDto dto);

    @Mapping(target = "answers", ignore = true)
    QuestionDto toQuestionDto(Question entity);

    @Mapping(target = "answers", ignore = true)
    List<Question> toQuestionEntityList(Iterable<QuestionDto> dtoIterable);

    @Mapping(target = "answers", ignore = true)
    List<QuestionDto> toQuestionDtoList(Iterable<Question> entityIterable);

}
