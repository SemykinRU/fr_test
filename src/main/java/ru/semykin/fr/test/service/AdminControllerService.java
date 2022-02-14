package ru.semykin.fr.test.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.semykin.fr.test.dto.AnswerDto;
import ru.semykin.fr.test.dto.PollDto;
import ru.semykin.fr.test.dto.QuestionDto;

import java.util.List;

@Service
@AllArgsConstructor
public class AdminControllerService {

    private final PollService pollService;

    private final QuestionService questionService;

    private final AnswerService answerService;


    public List<PollDto> findAllPollsDto() {
        return pollService.findAllPollsDto();
    }

    public PollDto findOnePollDtoById(Long pollId) {
        return pollService.findOnePollDtoById(pollId);
    }

    public PollDto savePoll(PollDto pollDto) {
        return pollService.savePoll(pollDto);
    }

    public PollDto updatePoll(PollDto pollDto) {
        return pollService.updatePoll(pollDto);
    }

    public void deletedPoll(Long pollId) {
        pollService.deletedPoll(pollId);
    }

    public List<QuestionDto> findAllQuestionDtoByPollId(Long pollId) {
        return questionService.findAllQuestionDtoByPollId(pollId);
    }

    public QuestionDto findOneQuestionDtoById(Long qId) {
        return questionService.findOneQuestionDtoById(qId);
    }

    public QuestionDto saveQuestion(QuestionDto questionDto, Long pollId) {
        return questionService.saveQuestion(questionDto, pollId);
    }

    public QuestionDto updateQuestion(QuestionDto questionDto) {
        return questionService.updateQuestion(questionDto);
    }

    public void deletedQuestion(Long qId) {
        questionService.deletedQuestion(qId);
    }

    public List<AnswerDto> findAllAnswerDtoByQuestId(Long qId) {
        return answerService.findAllAnswerDtoByQuestId(qId);
    }

    public AnswerDto findOneAnswerDtoById(Long aId) {
        return answerService.findOneAnswerDtoById(aId);
    }

    public AnswerDto saveAnswer(AnswerDto answerDto, Long qId) {
        return answerService.saveAnswer(answerDto, qId);
    }

    public AnswerDto updateAnswer(AnswerDto answerDto) {
        return answerService.updateAnswer(answerDto);
    }

    public void deletedAnswer(Long aId) {
        answerService.deletedAnswer(aId);
    }
}
