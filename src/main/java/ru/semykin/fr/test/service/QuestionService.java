package ru.semykin.fr.test.service;

import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import ru.semykin.fr.test.dto.QuestionDto;
import ru.semykin.fr.test.entity.Question;
import ru.semykin.fr.test.exception.NotFoundException;
import ru.semykin.fr.test.mapper.QuestionMapper;
import ru.semykin.fr.test.repository.QuestionRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class QuestionService {

    private final QuestionRepository repository;

    private static final QuestionMapper questionMapper = Mappers.getMapper(QuestionMapper.class);

    public List<QuestionDto> getAllQuestionDtoByPollId(Long pollId) {
        List<Question> questions = repository.findAllByPollId(pollId);
        return questionMapper.toQuestionDtoList(questions);
    }

    public QuestionDto getOneQuestionById(Long id) {
        Question question = repository.findById(id).orElseThrow(NotFoundException::new);
        return questionMapper.toQuestionDto(question);
    }

    public QuestionDto addNewQuestion(QuestionDto questionDto) {
        Question question = questionMapper.toQuestionEntity(questionDto);
        Long id = repository.save(question).getId();
        questionDto.setId(id);
        return questionDto;
    }

    public QuestionDto updateQuestion(QuestionDto questionDto) {
        Question question = questionMapper.toQuestionEntity(questionDto);
        repository.save(question);
        return questionDto;
    }

    public void deletedQuestion(Long id) {
        repository.deleteById(id);
    }

}
