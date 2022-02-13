package ru.semykin.fr.test.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.semykin.fr.test.entity.Answer;
import ru.semykin.fr.test.entity.Poll;
import ru.semykin.fr.test.entity.Question;
import ru.semykin.fr.test.repository.PollRepository;

@SpringBootTest
class PollServiceTest {

    @Autowired
    private PollRepository pollRepository;

    @Test
    void savePoll() {
        Poll poll = new Poll();
        poll.setTitle("Новый опрос 1");

        Question question = new Question();
        question.setTitle("Вопрос 1 опроса 1");
        question.setPoll(poll);

        Answer answer = new Answer();
        answer.setTitle("Ответ вопроса");
        answer.setQuestion(question);

        question.getAnswers().add(answer);
        poll.getQuestions().add(question);
        pollRepository.save(poll);

    }
}