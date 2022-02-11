package ru.semykin.fr.test.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.semykin.fr.test.dto.*;
import ru.semykin.fr.test.service.UserAnswerService;

import java.util.List;

@RestController
@RequestMapping(value = "/public" , produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class UserController {

    private final UserAnswerService userAnswerService;

    @GetMapping(value = "/polls")
    public List<PollDto> getActivePolls(@RequestParam Long userId) {
        return userAnswerService.getAllActivePoll(userId);
    }

    @GetMapping(value = "/questions")
    public List<QuestionDto> getAllQuestions(@RequestParam Long userId, @RequestParam Long pollId) {
        return userAnswerService.getAllQuestions(userId, pollId);
    }

    @PostMapping(value = "/questions")
    public void saveUserAnswers(@RequestBody List<UserAnswerDto> answers,
                                @RequestBody UserResponseDto userResponseDto) {
        userAnswerService.saveAnswers(answers, userResponseDto);
    }


}
