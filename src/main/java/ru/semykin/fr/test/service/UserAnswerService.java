package ru.semykin.fr.test.service;

import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import ru.semykin.fr.test.dto.*;
import ru.semykin.fr.test.entity.UserAnswer;
import ru.semykin.fr.test.entity.UserResponse;
import ru.semykin.fr.test.exception.NotFoundException;
import ru.semykin.fr.test.mapper.UserAnswerMapper;
import ru.semykin.fr.test.mapper.UserResponseMapper;
import ru.semykin.fr.test.repository.UserAnswerRepository;
import ru.semykin.fr.test.repository.UserResponseRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserAnswerService {

    private final PollService pollService;

    private final QuestionService questionService;

    private final UserAnswerRepository userAnswerRepository;

    private final UserResponseRepository userResponseRepository;

    private final UserResponseMapper userResponseMapper = Mappers.getMapper(UserResponseMapper.class);

    private final UserAnswerMapper userAnswerMapper = Mappers.getMapper(UserAnswerMapper.class);

    public List<PollDto> getAllActivePoll(Long userId) {
        return pollService.findAllPollsDto()
                .stream().filter(x -> x.getTimeEnd() == null || x.getTimeEnd().compareTo(LocalDateTime.now()) > 0)
                .collect(Collectors.toList());
    }

    public PollDto getOneActivePollById(Long userId, Long pollId) {
        whenNoActivePollStatus(pollId);
        return pollService.findPollDtoById(pollId);
    }

    public List<QuestionDto> getAllQuestions(Long userId, Long pollId) {
        whenNoActivePollStatus(pollId);
        return questionService.findAllQuestionDtoByPollId(pollId);
    }

    private void whenNoActivePollStatus(Long pollId) {
        PollDto pollDto = pollService.findPollDtoById(pollId);
        if (pollDto.getTimeEnd() != null && pollDto.getTimeEnd().compareTo(LocalDateTime.now()) < 0) {
            throw new NotFoundException();
        }
    }

    public void saveAnswers(List<UserAnswerDto> answers, UserResponseDto userResponseDto) {
        List<UserAnswer> userAnswer = userAnswerMapper.toUserAnswerEntityList(answers);
        UserResponse userResponse = userResponseMapper.toUserResponseEntity(userResponseDto);
        userAnswerRepository.saveAll(userAnswer);
        userResponseRepository.save(userResponse);


    }
}
