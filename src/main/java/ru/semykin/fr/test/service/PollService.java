package ru.semykin.fr.test.service;

import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import ru.semykin.fr.test.dto.PollDto;
import ru.semykin.fr.test.dto.QuestionDto;
import ru.semykin.fr.test.entity.Poll;
import ru.semykin.fr.test.entity.Question;
import ru.semykin.fr.test.exception.NotFoundException;
import ru.semykin.fr.test.mapper.PollMapper;
import ru.semykin.fr.test.mapper.QuestionMapper;
import ru.semykin.fr.test.repository.PollRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class PollService {

    private final PollRepository repository;

    private static final PollMapper mapper = Mappers.getMapper(PollMapper.class);

    private static final QuestionMapper questionMapper = Mappers.getMapper(QuestionMapper.class);

    public List<PollDto> findAllPollsDto() {
        final List<Poll> polls = repository.findAll();
        if (polls.isEmpty()) {
            throw new NotFoundException();
        }
        return mapper.toPollDtoList(polls);
    }

    public PollDto findPollDtoById(Long id) {
        final Poll poll = findPollEntityById(id);
        return mapper.toPollDto(poll);
    }

    public Poll findPollEntityById(Long id) {
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }

    public PollDto savePoll(PollDto pollDto) {
        final Poll poll = mapper.toPollEntity(pollDto);
        final Long id = repository.save(poll).getId();
        pollDto.setId(id);
        return pollDto;
    }

    public PollDto updatePoll(PollDto pollDto) {
        final Long id = pollDto.getId();
        final Poll poll = findPollEntityById(id);
        final String description = pollDto.getDescription();
        final String title = pollDto.getTitle();
        final LocalDateTime start = pollDto.getTimeStart();
        final LocalDateTime end = pollDto.getTimeEnd();
        final List<QuestionDto> questions = pollDto.getQuestions();

        poll.setDescription(description);
        poll.setTitle(title);
        if (poll.getTimeStart() == null) {
            poll.setTimeStart(start);
        }
        poll.setTimeEnd(end);
        if (!questions.isEmpty()) {
            final List<Question> entityQuestions = questionMapper.toQuestionEntityList(questions);
            poll.setQuestions(entityQuestions);
        }
        repository.save(poll);
        return pollDto;
    }

    public void deletedPoll(Long id) {
        repository.deleteById(id);
    }

}
