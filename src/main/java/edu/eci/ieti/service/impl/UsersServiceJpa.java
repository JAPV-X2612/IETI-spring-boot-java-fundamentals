package edu.eci.ieti.service.impl;

import edu.eci.ieti.model.entity.User;
import edu.eci.ieti.repository.UserRepository;
import edu.eci.ieti.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * JPA-based implementation of UsersService
 *
 * @author Jesús Pinzón
 * @version 1.0
 * @since 2026-03-12
 */
@Service
public class UsersServiceJpa implements UsersService {

    private final UserRepository userRepository;

    @Autowired
    public UsersServiceJpa(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public User update(String id, User user) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setName(user.getName());
                    existingUser.setEmail(user.getEmail());
                    return userRepository.save(existingUser);
                })
                .orElse(null);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
