package ru.semykin.fr.test.service;

import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import ru.semykin.fr.test.dto.PollDto;
import ru.semykin.fr.test.entity.Poll;
import ru.semykin.fr.test.exception.NotFoundException;
import ru.semykin.fr.test.mapper.PollMapper;
import ru.semykin.fr.test.repository.PollRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class PollService {

    private final PollRepository repository;

    private static final PollMapper mapper = Mappers.getMapper(PollMapper.class);

    public List<PollDto> getAllPollsDto() {
        List<Poll> polls = repository.findAll();
        return mapper.toPollDtoList(polls);
    }

    public PollDto getPollDtoById(Long id) {
        Poll poll = getPollById(id);
        return mapper.toPollDto(poll);
    }

    public Poll getPollById(Long id) {
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }

    public PollDto addNewPoll(PollDto pollDto) {
        Poll poll = mapper.toPollEntity(pollDto);
        Long id = repository.save(poll).getId();
        pollDto.setId(id);
        return pollDto;
    }

    public PollDto updatePoll(PollDto pollDto) {
        Poll poll = mapper.toPollEntity(pollDto);
        repository.save(poll);
        return pollDto;
    }

    public void deletedPoll(Long id) {
        repository.deleteById(id);
    }

}
