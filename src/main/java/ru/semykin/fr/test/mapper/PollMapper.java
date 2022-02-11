package ru.semykin.fr.test.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.semykin.fr.test.dto.PollDto;
import ru.semykin.fr.test.entity.Poll;

import java.util.List;

@Mapper
public interface PollMapper {

    @Mapping(target = "questions", ignore = true)
    PollDto toPollDto(Poll entity);

    @Mapping(target = "questions", ignore = true)
    Poll toPollEntity(PollDto dto);

    @Mapping(target = "questions", ignore = true)
    List<PollDto> toPollDtoList(Iterable<Poll> entity);

    @Mapping(target = "questions", ignore = true)
    List<Poll> toPollEntityList(Iterable<PollDto> dto);

}
