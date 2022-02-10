package ru.semykin.fr.test.service;

import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.semykin.fr.test.dto.PollDto;
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

    public List<QuestionDto> getAllQuestionDto (Long pollId) {
        List<Question> questions = repository.findAllByPollId(pollId);
        return questionMapper.toQuestionDtoList(questions);
    }

    public QuestionDto getQuestionById (Long id) {
        Question question = repository.findById(id).orElseThrow(NotFoundException::new);
        return questionMapper.toQuestionDto(question);
    }

    public QuestionDto addNewQuestion (QuestionDto questionDto, Long pollId) {
        Question question = questionMapper.toQuestionEntity(questionDto);
        Poll poll = pollService.getPollById(pollId);
        question.setPoll(poll);
        Long id = repository.save(question).getId();
        questionDto.setId(id);
        return questionDto;
    }

    public PollDto updateQuestion (QuestionDto questionDto, Long pollId) {
        Question question = questionMapper.toQuestionEntity(questionDto);
        repository.save(question);
        return pollService.getPollDtoById(pollId);
    }

    public void deletedQuestion (Long id) {
        repository.deleteById(id);
    }
}
