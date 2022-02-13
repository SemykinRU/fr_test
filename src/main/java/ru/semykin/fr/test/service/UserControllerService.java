package ru.semykin.fr.test.service;

import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import ru.semykin.fr.test.dto.PollDto;
import ru.semykin.fr.test.dto.UserResponseDto;
import ru.semykin.fr.test.entity.Poll;
import ru.semykin.fr.test.entity.UserResponse;
import ru.semykin.fr.test.mapper.PollMapper;

import java.util.List;

@Service
@AllArgsConstructor
public class UserControllerService {

    private final PollService pollService;

    private final UserResponseService userResponseService;

    private final PollMapper pollMapper = Mappers.getMapper(PollMapper.class);

    public List<PollDto> findAllActivePollsDtoByUserId(Long userId) {
        List<Poll> activePolls = pollService.findAllActiveEntityPolls();
        List<UserResponse> userResponses = userResponseService.findAllUserResponseEntityByUserId(userId);
        activePolls.removeIf(userResponses::contains);
        return pollMapper.toPollDtoList(activePolls);
    }

    public List<PollDto> findAllActivePolls() {
        return pollService.findAllActiveDtoPolls();
    }


    public UserResponseDto saveUserResponse(Long userId, Long pollId, UserResponseDto userResponseDto) {
        return userResponseService.saveUserResponseDto(userId, pollId, userResponseDto);
    }

    public UserResponseDto findUserResponseByPollId(Long userId, Long pollId) {
        return userResponseService.findUserResponseByPollId(userId, pollId);
    }

    public List<UserResponseDto> findAllUserResponseByUserId(Long userId) {
        return userResponseService.findAllUserResponseByUserId(userId);
    }

}
