package ru.semykin.fr.test.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.semykin.fr.test.dto.AnswerDto;
import ru.semykin.fr.test.dto.PollDto;
import ru.semykin.fr.test.dto.QuestionDto;
import ru.semykin.fr.test.service.UsersAnswersService;

import java.util.List;

@RestController
@RequestMapping(value = "/public" , produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class UserController {

    private final UsersAnswersService usersAnswersService;

    @GetMapping(value = "/polls")
    public List<PollDto> getActivePolls(@RequestParam Long userId) {
        return usersAnswersService.getAllActivePoll(userId);
    }

    @GetMapping(value = "/questions")
    public List<QuestionDto> getAllQuestions(@RequestParam Long userId, @RequestParam Long pollId) {
        return usersAnswersService.getAllQuestions(userId, pollId);
    }

    @PostMapping(value = "/questions")
    public void saveUserAnswers(@RequestParam Long userId, @RequestBody List<AnswerDto> answers, @RequestParam Long pollId) {
        usersAnswersService.saveAnswers(userId, answers, pollId);
    }


}
