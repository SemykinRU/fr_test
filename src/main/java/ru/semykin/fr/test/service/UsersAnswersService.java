package ru.semykin.fr.test.service;

import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import ru.semykin.fr.test.dto.AnswerDto;
import ru.semykin.fr.test.dto.PollDto;
import ru.semykin.fr.test.dto.QuestionDto;
import ru.semykin.fr.test.entity.Answer;
import ru.semykin.fr.test.exception.NotFoundException;
import ru.semykin.fr.test.mapper.AnswerMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UsersAnswersService {

    private final PollService pollService;

    private final QuestionService questionService;

    private final AnswerMapper answerMapper = Mappers.getMapper(AnswerMapper.class);

    public List<PollDto> getAllActivePoll(Long userId) {
        return pollService.getAllPollsDto()
                .stream().filter(x -> x.getTimeEnd() == null || x.getTimeEnd().compareTo(LocalDateTime.now()) > 0)
                .collect(Collectors.toList());
    }

    public PollDto getOneActivePollById(Long userId, Long pollId) {
        whenNoActivePollStatus(pollId);
        return pollService.getPollDtoById(pollId);
    }

    public List<QuestionDto> getAllQuestions(Long userId, Long pollId) {
        whenNoActivePollStatus(pollId);
        return questionService.getAllQuestionDtoByPollId(pollId);
    }

    private void whenNoActivePollStatus(Long pollId) {
        PollDto pollDto = pollService.getPollDtoById(pollId);
        if (pollDto.getTimeEnd() != null && pollDto.getTimeEnd().compareTo(LocalDateTime.now()) < 0) {
            throw new NotFoundException();
        }
    }

    public void saveAnswers(Long userId, List<AnswerDto> answers, Long pollId) {
        whenNoActivePollStatus(pollId);
        List<Answer> answersEntity = answerMapper.toAnswerEntityList(answers);

    }
}
