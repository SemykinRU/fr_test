package ru.semykin.fr.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.semykin.fr.test.entity.UserResponse;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserResponseRepository extends JpaRepository<UserResponse, Long> {

    List<UserResponse> findAllByUserUserId(Long userId);

    Optional<UserResponse> findByUserUserIdAndPollId(Long userId, Long pollId);

}
