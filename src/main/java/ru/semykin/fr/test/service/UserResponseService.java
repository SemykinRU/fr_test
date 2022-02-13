package ru.semykin.fr.test.service;

import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import ru.semykin.fr.test.dto.UserResponseDto;
import ru.semykin.fr.test.entity.Poll;
import ru.semykin.fr.test.entity.User;
import ru.semykin.fr.test.entity.UserAnswer;
import ru.semykin.fr.test.entity.UserResponse;
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

    private final UserResponseMapper userResponseMapper = Mappers.getMapper(UserResponseMapper.class);

    private final UserAnswerMapper userAnswerMapper = Mappers.getMapper(UserAnswerMapper.class);

    public UserResponseDto saveUserResponseDto(Long userId, Long pollId, UserResponseDto userResponseDto) {
        final UserResponse userResponse = userResponseMapper.toUserResponseEntity(userResponseDto);
        final List<UserAnswer> userAnswer = userAnswerMapper.toUserAnswerEntityList(userResponseDto.getAnswers());
        final User user = userService.findById(userId);
        final Poll poll = pollService.findOnePollEntityById(pollId);
        userAnswer.forEach(x -> x.setUserResponse(userResponse));
        userResponse.setAnswers(userAnswer);
        userResponse.setUser(user);
        userResponse.setPoll(poll);
        userResponseRepository.save(userResponse);
        return userResponseMapper.toUserResponseDto(userResponse);
    }

    public List<UserResponseDto> findAllUserResponseByUserId(Long userId) {
        final List<UserResponse> userResponses = findAllUserResponseEntityByUserId(userId);
        final List<UserResponseDto> userResponsesDto = userResponseMapper.toUserResponseDtoList(userResponses);
        for (int i = 0; i < userResponses.size(); i++) {
            List<UserAnswer> answers = userResponses.get(i).getAnswers();
            userResponsesDto.get(i).setAnswers(userAnswerMapper.toUserAnswerDtoList(answers));
        }
        return userResponsesDto;
    }

    public List<UserResponse> findAllUserResponseEntityByUserId(Long userId) {
        return userResponseRepository.findAllByUserUserId(userId);
    }

    public UserResponseDto findUserResponseByPollId(Long userId, Long pollId) {
        final UserResponse userResponse =
                userResponseRepository.findByUserUserIdAndPollId(userId, pollId)
                        .orElseThrow(NotFoundException::new);

        return userResponseMapper.toUserResponseDto(userResponse);
    }
}
