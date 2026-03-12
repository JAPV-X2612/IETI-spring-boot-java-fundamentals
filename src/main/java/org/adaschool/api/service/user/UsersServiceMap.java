package org.adaschool.api.service.user;

import org.adaschool.api.repository.user.User;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * In-memory implementation of UsersService
 *
 * @author Jesús Pinzón
 * @version 1.0
 * @since 2026-03-12
 */
@Service
public class UsersServiceMap implements UsersService {

    private final Map<String, User> users = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public User save(User user) {
        String id = user.getId();
        if (id == null || id.isEmpty()) {
            id = String.valueOf(idGenerator.getAndIncrement());
            User newUser = new User(id, user.getName(), user.getLastName(), user.getEmail(), "");
            newUser.setPasswordHash(user.getId());
            users.put(id, newUser);
            return newUser;
        }
        users.put(id, user);
        return user;
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public List<User> all() {
        return new ArrayList<>(users.values());
    }

    @Override
    public void deleteById(String id) {
        users.remove(id);
    }

    @Override
    public User update(User user, String userId) {
        users.put(userId, user);
        return user;
    }
}
