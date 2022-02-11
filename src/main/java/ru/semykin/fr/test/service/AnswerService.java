package ru.semykin.fr.test.service;

import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import ru.semykin.fr.test.dto.AnswerDto;
import ru.semykin.fr.test.entity.Answer;
import ru.semykin.fr.test.entity.Question;
import ru.semykin.fr.test.exception.NotFoundException;
import ru.semykin.fr.test.mapper.AnswerMapper;
import ru.semykin.fr.test.repository.AnswerRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class AnswerService {

    private final AnswerRepository repository;

    private final QuestionService questionService;

    private static final AnswerMapper mapper = Mappers.getMapper(AnswerMapper.class);

    public List<AnswerDto> findAllAnswerDtoByQuestId(Long qId) {
        final List<Answer> answers = repository.findAllByQuestionId(qId);
        if (answers.isEmpty()) {
            throw new NotFoundException();
        }
        return mapper.toAnswerDtoList(answers);
    }

    public AnswerDto findAnswerDtoById(Long id) {
        final Answer answer = findOneEntityAnswerById(id);
        return mapper.toAnswerDto(answer);
    }

    public Answer findOneEntityAnswerById(Long id) {
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }

    public AnswerDto saveAnswer(AnswerDto answerDto, Long qId) {
        final Answer answer = mapper.toAnswerEntity(answerDto);
        final Question question = questionService.findOneQuestionEntityById(qId);
        answer.setQuestion(question);
        Long id = repository.save(answer).getId();
        answerDto.setId(id);
        return answerDto;
    }

    public AnswerDto updateAnswer(AnswerDto answerDto) {
        final Long id = answerDto.getId();
        final String title = answerDto.getTitle();
        final Answer answer = findOneEntityAnswerById(id);
        answer.setTitle(title);
        repository.save(answer);
        return answerDto;
    }

    public void deletedAnswer(Long id) {
        repository.deleteById(id);
    }

}
