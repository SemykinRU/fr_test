package ru.semykin.fr.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.semykin.fr.test.entity.Question;

import java.util.List;


@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findAllByPollId(Long pollId);
}
