package ru.semykin.fr.test.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.semykin.fr.test.dto.PollDto;
import ru.semykin.fr.test.dto.UserResponseDto;
import ru.semykin.fr.test.dto.UserResultDto;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserControllerService {

    private final PollService pollService;

    private final UserResponseService userResponseService;

    public List<PollDto> findAllActivePollsDtoByUserId(Long userId) {
        final List<PollDto> activePolls = pollService.findAllActiveDtoPolls();
        final List<UserResponseDto> userResponses = userResponseService.findAllUserResponseByUserId(userId);
        final List<Long> pollsID = new ArrayList<>();
        for (var userResponseDto : userResponses) {
            Long id = userResponseDto.getPollId();
            pollsID.add(id);
        }
        for (Long id : pollsID) {
            activePolls.removeIf(x -> x.getId().equals(id));
        }
        return activePolls;
    }

    public List<PollDto> findAllActivePolls() {
        return pollService.findAllActiveDtoPolls();
    }


    public UserResponseDto saveUserResponse(Long userId, Long pollId, UserResponseDto userResponseDto) {
        return userResponseService.saveUserResponseDto(userId, pollId, userResponseDto);
    }

    public UserResultDto findUserResponseByPollId(Long userId, Long pollId) {
        final UserResponseDto userResponseDto = userResponseService.findOneUserResponseOnPollByUserId(userId, pollId);
        final PollDto pollDto = pollService.findOnePollDtoById(pollId);
        final UserResultDto userResultDto = new UserResultDto();
        userResultDto.setPollDto(pollDto);
        userResultDto.setUserResponseDto(userResponseDto);
        return userResultDto;
    }

    public List<UserResultDto> findAllUserResponseByUserId(Long userId) {
        final List<UserResponseDto> userResponsesDto = userResponseService.findAllUserResponseByUserId(userId);
        final List<UserResultDto> userResultsDto = new ArrayList<>();
        for (var userResponseDto : userResponsesDto) {
            PollDto pollDto = pollService.findOnePollDtoById(userResponseDto.getPollId());
            UserResultDto userResultDto = new UserResultDto();
            userResultDto.setUserResponseDto(userResponseDto);
            userResultDto.setPollDto(pollDto);
            userResultsDto.add(userResultDto);
        }
        return userResultsDto;
    }

}
