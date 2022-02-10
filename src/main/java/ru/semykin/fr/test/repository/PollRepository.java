package ru.semykin.fr.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.semykin.fr.test.entity.Poll;

@Repository
public interface PollRepository extends JpaRepository<Poll, Long> {
}
