package ru.semykin.fr.test.service;

import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import ru.semykin.fr.test.dto.UserAnswerDto;
import ru.semykin.fr.test.dto.UserResponseDto;
import ru.semykin.fr.test.entity.*;
import ru.semykin.fr.test.exception.NotFoundException;
import ru.semykin.fr.test.mapper.UserAnswerMapper;
import ru.semykin.fr.test.mapper.UserResponseMapper;
import ru.semykin.fr.test.repository.UserResponseRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class UserResponseService {

    private final UserResponseRepository userResponseRepository;

    private final UserService userService;

    private final PollService pollService;

    private final AnswerService answerService;

    private final UserResponseMapper userResponseMapper = Mappers.getMapper(UserResponseMapper.class);

    private final UserAnswerMapper userAnswerMapper = Mappers.getMapper(UserAnswerMapper.class);

    public UserResponseDto saveUserResponseDto(Long userId, Long pollId, UserResponseDto userResponseDto) {
        final UserResponse userResponse = userResponseMapper.toUserResponseEntity(userResponseDto);
        final List<UserAnswerDto> userAnswerDto = userResponseDto.getUserAnswers();
        final List<UserAnswer> userAnswerEntity = userAnswerMapper.toUserAnswerEntityList(userAnswerDto);
        for (int i = 0; i < userAnswerDto.size(); i++) {
            Long aId = userAnswerDto.get(i).getAnswerId();
            Answer answer = answerService.findOneEntityAnswerById(aId);
            userAnswerEntity.get(i).setAnswer(answer);
        }
        final User user = userService.findById(userId);
        final Poll poll = pollService.findOnePollEntityById(pollId);
        userAnswerEntity.forEach(x -> x.setUserResponse(userResponse));
        userResponse.setUserAnswers(userAnswerEntity);
        userResponse.setUser(user);
        userResponse.setPoll(poll);
        userResponseRepository.save(userResponse);
        return userResponseMapper.toUserResponseDto(userResponse);
    }

    public List<UserResponseDto> findAllUserResponseByUserId(Long userId) {
        final List<UserResponse> userResponses = findAllUserResponseEntityByUserId(userId);
        final List<UserResponseDto> userResponsesDto = userResponseMapper.toUserResponseDtoList(userResponses);
        for (int i = 0; i < userResponses.size(); i++) {
            List<UserAnswer> answers = userResponses.get(i).getUserAnswers();
            List<UserAnswerDto> answersDto = userAnswerMapper.toUserAnswerDtoList(answers);
            for (int j = 0; j < answers.size(); j++) {
                Answer answer = answers.get(j).getAnswer();
                Long id = answer.getId();
                answersDto.get(j).setAnswerId(id);
            }
            userResponsesDto.get(i).setUserAnswers(answersDto);
            Long pollId = userResponses.get(i).getPoll().getId();
            userResponsesDto.get(i).setUserId(userId);
            userResponsesDto.get(i).setPollId(pollId);

        }
        return userResponsesDto;
    }

    public List<UserResponse> findAllUserResponseEntityByUserId(Long userId) {
        return userResponseRepository.findAllByUserUserId(userId);
    }

    public UserResponseDto findOneUserResponseOnPollByUserId(Long userId, Long pollId) {
        final UserResponse userResponse =
                userResponseRepository.findByUserUserIdAndPollId(userId, pollId)
                        .orElseThrow(NotFoundException::new);
        final List<UserAnswer> userAnswers = userResponse.getUserAnswers();
        final List<UserAnswerDto> userAnswersDto = userAnswerMapper.toUserAnswerDtoList(userAnswers);
        for (int i = 0; i < userAnswers.size(); i++) {
            Long answerId = userAnswers.get(i).getAnswer().getId();
            userAnswersDto.get(i).setAnswerId(answerId);
        }
        final UserResponseDto userResponseDto = userResponseMapper.toUserResponseDto(userResponse);
        userResponseDto.setUserAnswers(userAnswersDto);
        userResponseDto.setUserId(userId);
        userResponseDto.setPollId(pollId);
        return userResponseDto;
    }
}
