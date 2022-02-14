package ru.semykin.fr.test.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.semykin.fr.test.dto.AnswerDto;
import ru.semykin.fr.test.dto.PollDto;
import ru.semykin.fr.test.dto.QuestionDto;
import ru.semykin.fr.test.service.AdminControllerService;

import java.util.List;

import static ru.semykin.fr.test.util.ApplicationConstant.*;

@RestController
@RequestMapping(value = URL_ADMIN, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class AdminController {

    private final AdminControllerService adminControllerService;

    @GetMapping(value = URL_POLLS)
    @ApiOperation(API_FIND_ALL_POLLS)
    public List<PollDto> findAllPolls() {
        return adminControllerService.findAllPollsDto();
    }

    @GetMapping(value = URL_POLLS_POLL_ID)
    @ApiOperation(API_FIND_POLL_BY_ID)
    public PollDto findOnePollById(@ApiParam(API_POLL_ID)
                                   @PathVariable Long pollId) {
        return adminControllerService.findOnePollDtoById(pollId);
    }

    @PostMapping(value = URL_POLLS)
    @ApiOperation(API_SAVE_POLL)
    public PollDto saveNewPoll(@ApiParam(API_POLL_DTO)
                               @RequestBody PollDto pollDto) {
        return adminControllerService.savePoll(pollDto);
    }

    @PutMapping(value = URL_POLLS)
    @ApiOperation(API_PUT_POLL)
    public PollDto updatePoll(@ApiParam(API_POLL_DTO)
                              @RequestBody PollDto pollDto) {
        return adminControllerService.updatePoll(pollDto);
    }

    @DeleteMapping(value = URL_POLLS)
    @ApiOperation(API_DEL_POLL)
    public void deletedPollById(@ApiParam(API_POLL_ID)
                                @RequestParam Long pollId) {
        adminControllerService.deletedPoll(pollId);
    }

    @GetMapping(value = URL_QUESTIONS)
    @ApiOperation(API_FIND_ALL_QUESTIONS_BY_POLL_ID)
    public List<QuestionDto> findAllQuestionsByPollId(@ApiParam(API_POLL_ID)
                                                      @RequestParam Long pollId) {
        return adminControllerService.findAllQuestionDtoByPollId(pollId);
    }

    @GetMapping(value = URL_QUESTIONS_ID)
    @ApiOperation(API_FIND_QUESTION_BY_ID)
    public QuestionDto findOneQuestionsById(@ApiParam(API_QUESTION_ID)
                                            @PathVariable Long qId) {
        return adminControllerService.findOneQuestionDtoById(qId);
    }

    @PostMapping(value = URL_QUESTIONS)
    @ApiOperation(API_SAVE_NEW_QUESTION)
    public QuestionDto saveNewQuestion(@ApiParam(API_POLL_ID)
                                       @RequestParam Long pollId,
                                       @ApiParam(API_QUESTION_DTO)
                                       @RequestBody QuestionDto questionDto) {
        return adminControllerService.saveQuestion(questionDto, pollId);
    }

    @PutMapping(value = URL_QUESTIONS)
    @ApiOperation(API_PUT_QUESTION)
    public QuestionDto updateQuestion(@ApiParam(API_QUESTION_DTO)
                                      @RequestBody QuestionDto questionDto) {
        return adminControllerService.updateQuestion(questionDto);
    }

    @DeleteMapping(value = URL_QUESTIONS)
    @ApiOperation(API_DEL_QUESTION)
    public void deletedQuestionById(@ApiParam(API_QUESTION_ID)
                                    @RequestParam Long qId) {
        adminControllerService.deletedQuestion(qId);
    }

    @GetMapping(value = URL_ANSWERS)
    @ApiOperation(API_FIND_ALL_ANSWERS)
    public List<AnswerDto> findAllAnswerByQuestId(@ApiParam(API_QUESTION_ID)
                                                  @RequestParam Long qId) {
        return adminControllerService.findAllAnswerDtoByQuestId(qId);
    }

    @GetMapping(value = URL_ANSWERS_ID)
    @ApiOperation(API_FIND_ANSWER)
    public AnswerDto findAnswerById(@ApiParam(API_ANSWER_ID)
                                    @PathVariable Long aId) {
        return adminControllerService.findOneAnswerDtoById(aId);
    }

    @PostMapping(value = URL_ANSWERS)
    @ApiOperation(API_SAVE_ANSWER)
    public AnswerDto saveNewAnswer(@ApiParam(API_QUESTION_ID)
                                   @RequestParam Long qId,
                                   @ApiParam(API_ANSWER_DTO)
                                   @RequestBody AnswerDto answerDto) {
        return adminControllerService.saveAnswer(answerDto, qId);
    }

    @PutMapping(value = URL_ANSWERS)
    @ApiOperation(API_PUT_ANSWER)
    public AnswerDto updateAnswers(@ApiParam(API_ANSWER_DTO)
                                   @RequestBody AnswerDto answerDto) {
        return adminControllerService.updateAnswer(answerDto);
    }

    @DeleteMapping(value = URL_ANSWERS)
    @ApiOperation(API_DEL_ANSWER)
    public void deletedAnswerById(@ApiParam(API_ANSWER_ID)
                                  @RequestParam Long aId) {
        adminControllerService.deletedAnswer(aId);
    }
}
