package ru.semykin.fr.test.service;

import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import ru.semykin.fr.test.dto.AnswerDto;
import ru.semykin.fr.test.dto.PollDto;
import ru.semykin.fr.test.dto.QuestionDto;
import ru.semykin.fr.test.entity.Answer;
import ru.semykin.fr.test.entity.Poll;
import ru.semykin.fr.test.entity.Question;
import ru.semykin.fr.test.exception.NotFoundException;
import ru.semykin.fr.test.mapper.AnswerMapper;
import ru.semykin.fr.test.mapper.PollMapper;
import ru.semykin.fr.test.mapper.QuestionMapper;
import ru.semykin.fr.test.repository.PollRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PollService {

    private final PollRepository repository;

    private static final PollMapper pollMapper = Mappers.getMapper(PollMapper.class);
    private static final QuestionMapper questionMapper = Mappers.getMapper(QuestionMapper.class);
    private static final AnswerMapper answerMapper = Mappers.getMapper(AnswerMapper.class);

    public List<PollDto> findAllPollsDto() {
        final List<Poll> polls = repository.findAll();
        if (polls.isEmpty()) {
            throw new NotFoundException();
        }
        final List<PollDto> pollsDto = new ArrayList<>();
        for (Poll poll : polls) {
            PollDto pollDto = fromPollEntityToPollDto(poll);
            pollsDto.add(pollDto);
        }
        return pollsDto;
    }

    public PollDto findOnePollDtoById(Long id) {
        final Poll poll = findOnePollEntityById(id);
        return fromPollEntityToPollDto(poll);
    }

    private PollDto fromPollEntityToPollDto(Poll poll) {
        final PollDto pollDto = pollMapper.toPollDto(poll);
        final List<QuestionDto> questions = questionMapper.toQuestionDtoList(poll.getQuestions());
        pollDto.setQuestions(questions);
        for (int i = 0; i < poll.getQuestions().size(); i++) {
            List<Answer> answers = poll.getQuestions().get(i).getAnswers();
            pollDto.getQuestions().get(i).setAnswers(answerMapper.toAnswerDtoList(answers));
        }
        return pollDto;
    }

    private Poll fromPollDtoToPollEntity(PollDto pollDto) {
        final Poll poll = pollMapper.toPollEntity(pollDto);
        final List<QuestionDto> questionsDto = pollDto.getQuestions();
        final List<Question> questions = questionMapper.toQuestionEntityList(questionsDto);
        questions.forEach(x -> x.setPoll(poll));
        poll.setQuestions(questions);
        for (int i = 0; i < poll.getQuestions().size(); i++) {
            var answers = poll.getQuestions().get(i).getAnswers();
            List<AnswerDto> answersDto = pollDto.getQuestions().get(i).getAnswers();
            answers = answerMapper.toAnswerEntityList(answersDto);
            Question question = poll.getQuestions().get(i);
            answers.forEach(x -> x.setQuestion(question));
            poll.getQuestions().get(i).setAnswers(answers);
        }
        return poll;
    }

    public Poll findOnePollEntityById(Long id) {
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }

    public PollDto savePoll(PollDto pollDto) {
        Poll poll = fromPollDtoToPollEntity(pollDto);
        final Long id = repository.save(poll).getId();
        pollDto.setId(id);
        return pollDto;
    }

    public PollDto updatePoll(PollDto pollDto) {
        final Long id = pollDto.getId();
        final Poll poll = findOnePollEntityById(id);
        final LocalDateTime start = poll.getTimeStart();
        pollDto.setTimeStart(start);
        return savePoll(pollDto);
    }

    public void deletedPoll(Long id) {
        repository.deleteById(id);
    }

    public List<Poll> findAllActiveEntityPolls() {

        return repository.findAllByTimeEndAfter(LocalDateTime.now());
    }

    public List<PollDto> findAllActiveDtoPolls() {
        final List<Poll> polls = findAllActiveEntityPolls();
        final List<PollDto> pollsDto = new ArrayList<>();
        for (Poll poll : polls) {
            PollDto pollDto = fromPollEntityToPollDto(poll);
            pollsDto.add(pollDto);
        }
        return pollsDto;
    }
}
