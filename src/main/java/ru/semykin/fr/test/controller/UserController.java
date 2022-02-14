package ru.semykin.fr.test.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.semykin.fr.test.dto.PollDto;
import ru.semykin.fr.test.dto.UserResponseDto;
import ru.semykin.fr.test.dto.UserResultDto;
import ru.semykin.fr.test.service.UserControllerService;

import java.util.List;

import static ru.semykin.fr.test.util.ApplicationConstant.*;

@RestController
@RequestMapping(value = URL_PUBLIC, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class UserController {

    private final UserControllerService userControllerService;

    @GetMapping(value = URL_POLLS_USER_ID)
    @ApiOperation(API_ALL_ACTIVE_USER_POLL)
    public List<PollDto> findAllActivePollsByUserId(@ApiParam(API_USER_ID)
                                                    @PathVariable Long userId) {
        return userControllerService.findAllActivePollsDtoByUserId(userId);
    }

    @GetMapping(value = URL_POLLS)
    @ApiOperation(API_ALL_ACTIVE_POLLS)
    public List<PollDto> findAllActivePolls() {
        return userControllerService.findAllActivePolls();
    }

    @PostMapping(value = URL_POLLS)
    @ApiOperation(API_SAVE_USER_RESPONSE)
    public UserResponseDto saveUserResponse(@ApiParam(API_USER_ID)
                                            @RequestParam Long userId,
                                            @ApiParam(API_POLL_ID)
                                            @RequestParam Long pollId,
                                            @ApiParam(API_USER_RESPONSE)
                                            @RequestBody UserResponseDto userResponseDto) {
        return userControllerService.saveUserResponse(userId, pollId, userResponseDto);

    }

    @GetMapping(value = URL_POLLS_RESULT_USER_ID)
    @ApiOperation(API_FIND_USER_RESPONSE)
    public UserResultDto findUserResponseByPollId(@ApiParam(API_POLL_ID)
                                                  @PathVariable Long pollId,
                                                  @ApiParam(API_USER_ID)
                                                  @RequestParam Long userId) {
        return userControllerService.findUserResponseByPollId(userId, pollId);
    }

    @GetMapping(value = URL_POLLS_RESULT)
    @ApiOperation(API_FIND_ALL_USER_RESPONSE)
    public List<UserResultDto> findAllUserResponseByUserId(@ApiParam(API_USER_ID)
                                                           @RequestParam Long userId) {
        return userControllerService.findAllUserResponseByUserId(userId);
    }


}
