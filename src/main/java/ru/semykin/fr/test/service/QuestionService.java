package ru.semykin.fr.test.service;

import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import ru.semykin.fr.test.dto.AnswerDto;
import ru.semykin.fr.test.dto.QuestionDto;
import ru.semykin.fr.test.entity.Answer;
import ru.semykin.fr.test.entity.Poll;
import ru.semykin.fr.test.entity.Question;
import ru.semykin.fr.test.entity.QuestionType;
import ru.semykin.fr.test.exception.NotFoundException;
import ru.semykin.fr.test.mapper.AnswerMapper;
import ru.semykin.fr.test.mapper.QuestionMapper;
import ru.semykin.fr.test.repository.QuestionRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class QuestionService {

    private final QuestionRepository repository;

    private final PollService pollService;

    private static final QuestionMapper questionMapper = Mappers.getMapper(QuestionMapper.class);

    private static final AnswerMapper answerMapper = Mappers.getMapper(AnswerMapper.class);

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
        final Question question = questionMapper.toQuestionEntity(questionDto);
        final Poll poll = pollService.findPollEntityById(pollId);
        question.setPoll(poll);
        final Long id = repository.save(question).getId();
        questionDto.setId(id);
        return questionDto;
    }

    public QuestionDto updateQuestion(QuestionDto questionDto) {
        final Long id = questionDto.getId();
        final QuestionType type = questionDto.getType();
        final String title = questionDto.getTitle();
        final List<AnswerDto> answers = questionDto.getAnswers();
        final Question question = findOneQuestionEntityById(id);
        question.setTitle(title);
        question.setType(type);
        if (!answers.isEmpty()) {
            final List<Answer> entityAnswers = answerMapper.toAnswerEntityList(answers);
            question.setAnswers(entityAnswers);
        }
        repository.save(question);
        return questionDto;
    }

    public void deletedQuestion(Long id) {
        repository.deleteById(id);
    }

}
