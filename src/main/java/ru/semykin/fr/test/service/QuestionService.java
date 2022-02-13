package ru.semykin.fr.test.service;

import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import ru.semykin.fr.test.dto.QuestionDto;
import ru.semykin.fr.test.entity.Poll;
import ru.semykin.fr.test.entity.Question;
import ru.semykin.fr.test.exception.NotFoundException;
import ru.semykin.fr.test.mapper.QuestionMapper;
import ru.semykin.fr.test.repository.QuestionRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class QuestionService {

    private final QuestionRepository repository;

    private final PollService pollService;

    private static final QuestionMapper questionMapper = Mappers.getMapper(QuestionMapper.class);

    public List<QuestionDto> findAllQuestionDtoByPollId(Long pollId) {
        final List<Question> questions = repository.findAllByPollId(pollId);
        if (questions.isEmpty()) {
            throw new NotFoundException();
        }
        return questionMapper.toQuestionDtoList(questions);
    }

    public QuestionDto findOneQuestionDtoById(Long qId) {
        final Question question = findOneQuestionEntityById(qId);
        return questionMapper.toQuestionDto(question);
    }

    public Question findOneQuestionEntityById(Long qId) {
        return repository.findById(qId).orElseThrow(NotFoundException::new);
    }

    public QuestionDto saveQuestion(QuestionDto questionDto, Long pollId) {
        final Poll poll = pollService.findOnePollEntityById(pollId);
        final Question question = questionMapper.toQuestionEntity(questionDto);
        question.setPoll(poll);
        final Long id = repository.save(question).getId();
        questionDto.setId(id);
        return questionDto;
    }

    public List<QuestionDto> saveAllQuestion(List<QuestionDto> questions, Long pollId) {
        if (questions.isEmpty()) {
            throw new NotFoundException();
        }
        for (var value : questions) {
            saveQuestion(value, pollId);
        }
        return questions;
    }

    public QuestionDto updateQuestion(QuestionDto questionDto) {
        final Long id = questionDto.getId();
        final Long pollId = findOneQuestionEntityById(id).getPoll().getId();
        return saveQuestion(questionDto, pollId);
    }

    public void deletedQuestion(Long qId) {
        repository.deleteById(qId);
    }

}
