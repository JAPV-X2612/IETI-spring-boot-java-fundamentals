package edu.eci.ieti.service.impl;

import edu.eci.ieti.model.entity.User;
import edu.eci.ieti.service.UsersService;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * HashMap-based implementation of UsersService
 *
 * @author Jesús Pinzón
 * @version 1.0
 * @since 2026-03-12
 */
@Service
public class UsersServiceHashMap implements UsersService {

    private final Map<String, User> users = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public User save(User user) {
        if (user.getId() == null || user.getId().isEmpty()) {
            String id = String.valueOf(idGenerator.getAndIncrement());
            User newUser = new User(id, user.getName(), user.getEmail());
            users.put(id, newUser);
            return newUser;
        }
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public void deleteById(String id) {
        users.remove(id);
    }

    @Override
    public User update(String id, User user) {
        User existingUser = users.get(id);
        if (existingUser != null) {
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            users.put(id, existingUser);
            return existingUser;
        }
        return null;
    }
}
