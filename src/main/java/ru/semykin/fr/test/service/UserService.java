package ru.semykin.fr.test.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.semykin.fr.test.entity.User;
import ru.semykin.fr.test.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User findById(Long userId) {
        return userRepository.findById(userId).orElse(saveUser(new User(userId)));
    }
}
