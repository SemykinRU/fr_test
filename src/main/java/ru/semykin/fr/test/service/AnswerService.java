package ru.semykin.fr.test.service;

import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import ru.semykin.fr.test.dto.AnswerDto;
import ru.semykin.fr.test.entity.Answer;
import ru.semykin.fr.test.exception.NotFoundException;
import ru.semykin.fr.test.mapper.AnswerMapper;
import ru.semykin.fr.test.repository.AnswerRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class AnswerService {

    private final AnswerRepository repository;

    private static final AnswerMapper mapper = Mappers.getMapper(AnswerMapper.class);

    public List<AnswerDto> getAllAnswerDtoByQuestId(Long qId) {
        List<Answer> answers = repository.findAllByQuestionId(qId);
        return mapper.toAnswerDtoList(answers);
    }

    public AnswerDto getAnswerById(Long id) {
        Answer answer = repository.findById(id).orElseThrow(NotFoundException::new);
        return mapper.toAnswerDto(answer);
    }

    public AnswerDto addNewAnswer(AnswerDto answerDto) {
        Answer answer = mapper.toAnswerEntity(answerDto);
        Long id = repository.save(answer).getId();
        answerDto.setId(id);
        return answerDto;
    }

    public AnswerDto updateAnswer(AnswerDto answerDto) {
        Answer answer = mapper.toAnswerEntity(answerDto);
        repository.save(answer);
        return answerDto;
    }

    public void deletedAnswer(Long id) {
        repository.deleteById(id);
    }

}
