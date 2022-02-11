package ru.semykin.fr.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.semykin.fr.test.entity.UserAnswer;


@Repository
public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long> {

}
