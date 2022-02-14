package ru.semykin.fr.test.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.semykin.fr.test.dto.AnswerDto;
import ru.semykin.fr.test.dto.PollDto;
import ru.semykin.fr.test.dto.QuestionDto;
import ru.semykin.fr.test.service.AdminControllerService;

import java.util.List;

import static ru.semykin.fr.test.util.ApplicationConstant.URL_ADMIN;

@RestController
@RequestMapping(value = "/" + URL_ADMIN, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Api(value = "AdminController")
public class AdminController {

    private final AdminControllerService adminControllerService;

    @GetMapping(value = "/polls")
    @ApiOperation("Поиск всех опросов.")
    public List<PollDto> findAllPolls() {
        return adminControllerService.findAllPollsDto();
    }

    @GetMapping(value = "/polls/{pollId}")
    @ApiOperation("Поиск опроса по pollId - индификатор опроса.")
    public PollDto findOnePollById(@PathVariable Long pollId) {
        return adminControllerService.findOnePollDtoById(pollId);
    }

    @PostMapping(value = "/polls")
    @ApiOperation("Добавление нового опроса.")
    public PollDto saveNewPoll(@RequestBody PollDto pollDto) {
        return adminControllerService.savePoll(pollDto);
    }

    @PutMapping(value = "/polls")
    @ApiOperation("Редактирование опроса.")
    public PollDto updatePoll(@RequestBody PollDto pollDto) {
        return adminControllerService.updatePoll(pollDto);
    }

    @DeleteMapping(value = "/polls")
    @ApiOperation("Удаление опроса по pollId - индификатор опроса.")
    public void deletedPollById(@RequestParam Long pollId) {
        adminControllerService.deletedPoll(pollId);
    }

    @GetMapping(value = "/questions")
    @ApiOperation("Поиск всех вопросов в опросе по pollId - индификатор опроса.")
    public List<QuestionDto> findAllQuestionsByPollId(@RequestParam Long pollId) {
        return adminControllerService.findAllQuestionDtoByPollId(pollId);
    }

    @GetMapping(value = "/questions/{qId}")
    @ApiOperation("Поиск вопросов по qId - индификатор вопроса.")
    public QuestionDto findOneQuestionsById(@PathVariable Long qId) {
        return adminControllerService.findOneQuestionDtoById(qId);
    }

    @PostMapping(value = "/questions")
    @ApiOperation("Добавление нового вопроса в опрос по pollId - индификатор опроса.")
    public QuestionDto saveNewQuestion(@RequestParam Long pollId,
                                       @RequestBody QuestionDto questionDto) {
        return adminControllerService.saveQuestion(questionDto, pollId);
    }

    @PutMapping(value = "/questions")
    @ApiOperation("Редактирование вопроса.")
    public QuestionDto updateQuestion(@RequestBody QuestionDto questionDto) {
        return adminControllerService.updateQuestion(questionDto);
    }

    @DeleteMapping(value = "/questions")
    @ApiOperation("Удаление вопроса по qId - индификатор вопроса.")
    public void deletedQuestionById(@RequestParam Long qId) {
        adminControllerService.deletedQuestion(qId);
    }

    @GetMapping(value = "/answers")
    @ApiOperation("Получение всех ответов в вопросе по qId - игдификатор вопроса.")
    public List<AnswerDto> findAllAnswerByQuestId(@RequestParam Long qId) {
        return adminControllerService.findAllAnswerDtoByQuestId(qId);
    }

    @GetMapping(value = "/answers/{aId}")
    @ApiOperation("Получение ответа aId - игдификатор ответа.")
    public AnswerDto findAnswerById(@PathVariable Long aId) {
        return adminControllerService.findOneAnswerDtoById(aId);
    }

    @PostMapping(value = "/answers")
    @ApiOperation("Добавление нового ответа в вопрос qId - игдификатор ответа.")
    public AnswerDto saveNewAnswer(@RequestParam Long qId,
                                   @RequestBody AnswerDto answerDto) {
        return adminControllerService.saveAnswer(answerDto, qId);
    }

    @PutMapping(value = "/answers")
    @ApiOperation("Редактирование ответа.")
    public AnswerDto updateAnswers(@RequestBody AnswerDto answerDto) {
        return adminControllerService.updateAnswer(answerDto);
    }

    @DeleteMapping(value = "/answers")
    @ApiOperation("Удаление ответа по aId - индификатор ответа.")
    public void deletedAnswerById(@RequestParam Long aId) {
        adminControllerService.deletedAnswer(aId);
    }
}
