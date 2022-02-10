package ru.semykin.fr.test.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.semykin.fr.test.dto.AnswerDto;
import ru.semykin.fr.test.dto.PollDto;
import ru.semykin.fr.test.dto.QuestionDto;
import ru.semykin.fr.test.service.AnswerService;
import ru.semykin.fr.test.service.PollService;
import ru.semykin.fr.test.service.QuestionService;

import java.util.List;

import static ru.semykin.fr.test.util.ApplicationConstant.URL_ADMIN;

@RestController
@RequestMapping(value = "/" + URL_ADMIN, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class AdminController {

    private final QuestionService questionService;

    private final PollService pollService;

    private final AnswerService answerService;


    @GetMapping(value = "/polls")
    public List<PollDto> getAllPolls() {
        return pollService.getAllPollsDto();
    }

    @GetMapping(value =  "/polls/{id}")
    public PollDto getOnePollById(@PathVariable Long id) {
        return pollService.getPollDtoById(id);
    }

    @PutMapping(value = "/polls")
    public PollDto updatePoll(@RequestBody PollDto pollDto) {
        return pollService.updatePoll(pollDto);
    }

    @PostMapping(value = "/polls")
    public PollDto addNewPoll(@RequestBody PollDto pollDto) {
        return pollService.addNewPoll(pollDto);
    }

    @DeleteMapping(value = "/polls/{id}")
    public void deletedPoll(@PathVariable Long id) {
        pollService.deletedPoll(id);
    }

    @GetMapping(value = "/polls/{pollId}/questions")
    public List<QuestionDto> getAllQuestions(@PathVariable Long pollId) {
        return questionService.getAllQuestionDto(pollId);
    }

    @PostMapping(value = "/polls/{pollId}/questions")
    public QuestionDto addNewQuestion(@RequestBody QuestionDto questionDto, @PathVariable Long pollId) {
        return questionService.addNewQuestion(questionDto, pollId);
    }

    @PutMapping(value = "/polls/{pollId}/questions")
    public PollDto updateQuestion(@RequestBody QuestionDto questionDto, @PathVariable Long pollId) {
        return questionService.updateQuestion(questionDto, pollId);
    }

    @DeleteMapping(value = "/questions/{qId}")
    public void deletedQuestion(@PathVariable Long qId) {
        questionService.deletedQuestion(qId);
    }
}
