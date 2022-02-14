package ru.semykin.fr.test.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.semykin.fr.test.dto.PollDto;
import ru.semykin.fr.test.dto.UserResultDto;
import ru.semykin.fr.test.dto.UserResponseDto;
import ru.semykin.fr.test.service.UserControllerService;

import java.util.List;

@RestController
@RequestMapping(value = "/public", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Api(value = "UserController")
public class UserController {

    private final UserControllerService userControllerService;

    @GetMapping(value = "/polls/{userId}")
    @ApiOperation("Получение активных опросов по userId - индификатор пользователя, " +
            "если пользователь уже прошел опрос, он не попадет в список.")
    public List<PollDto> findAllActivePollsByUserId(@PathVariable Long userId) {
        return userControllerService.findAllActivePollsDtoByUserId(userId);
    }

    @GetMapping(value = "/polls")
    @ApiOperation("Получение всех активных опросов на текущий момент.")
    public List<PollDto> findAllActivePolls() {
        return userControllerService.findAllActivePolls();
    }

    @PostMapping(value = "/polls")
    @ApiOperation("Сохранение ответов пользователя на опрос. userId = индификатор пользователя, pollId = опрос.")
    public UserResponseDto saveUserAnswer(@RequestParam Long userId,
                                          @RequestParam Long pollId,
                                          @RequestBody UserResponseDto userResponseDto) {
        return userControllerService.saveUserResponse(userId, pollId, userResponseDto);

    }

    @GetMapping(value = "/polls/result/{pollId}")
    @ApiOperation("Получение детализации ответов юзера по определенному опросу pollId - опрос.")
    public UserResultDto findUserResponseByPollId(@PathVariable Long pollId,
                                                  @RequestParam Long userId) {
        return userControllerService.findUserResponseByPollId(userId, pollId);
    }

    @GetMapping(value = "/polls/result")
    @ApiOperation("Получение всех ответов пользователя по всем пройденым опросам. userId - индификатор пользователя.")
    public List<UserResultDto> findAllUserResponseByUserId(@RequestParam Long userId) {
        return userControllerService.findAllUserResponseByUserId(userId);
    }


}
