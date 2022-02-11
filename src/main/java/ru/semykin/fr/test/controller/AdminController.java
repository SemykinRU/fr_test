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

    @GetMapping(value = "/polls/{id}")
    public PollDto getOnePollById(@PathVariable Long id) {
        return pollService.getPollDtoById(id);
    }

    @PostMapping(value = "/polls")
    public PollDto addNewPoll(@RequestBody PollDto pollDto) {
        return pollService.addNewPoll(pollDto);
    }

    @PutMapping(value = "/polls")
    public PollDto updatePoll(@RequestBody PollDto pollDto) {
        return pollService.updatePoll(pollDto);
    }

    @DeleteMapping(value = "/polls}")
    public void deletedPoll(@RequestParam Long id) {
        pollService.deletedPoll(id);
    }

    @GetMapping(value = "/questions")
    public List<QuestionDto> getAllQuestionsByPollId(@RequestParam Long id) {
        return questionService.getAllQuestionDtoByPollId(id);
    }

    @GetMapping(value = "/questions/{id}")
    public QuestionDto getOneQuestionsById(@PathVariable Long id) {
        return questionService.getOneQuestionById(id);
    }

    @PostMapping(value = "/questions")
    public QuestionDto addNewQuestion(@RequestBody QuestionDto questionDto) {
        return questionService.addNewQuestion(questionDto);
    }

    @PutMapping(value = "/questions")
    public QuestionDto updateQuestion(@RequestBody QuestionDto questionDto) {
        return questionService.updateQuestion(questionDto);
    }

    @DeleteMapping(value = "/questions")
    public void deletedQuestionById(@RequestParam Long id) {
        questionService.deletedQuestion(id);
    }

    @GetMapping(value = "/answers")
    public List<AnswerDto> getAllAnswerByQuestId(@RequestParam Long id) {
        return answerService.getAllAnswerDtoByQuestId(id);
    }

    @GetMapping(value = "/answers/{id}")
    public AnswerDto getAnswerById(@PathVariable Long id) {
        return answerService.getAnswerById(id);
    }

    @PostMapping(value = "/answers")
    public AnswerDto addNewAnswer(@RequestBody AnswerDto answerDto) {
        return answerService.addNewAnswer(answerDto);
    }

    @PutMapping(value = "/answers")
    public AnswerDto updateAnswers(@RequestBody AnswerDto answerDto) {
        return answerService.updateAnswer(answerDto);
    }

    @DeleteMapping(value = "/answers")
    public void deletedAnswerById(@RequestParam Long id) {
        answerService.deletedAnswer(id);
    }
}
