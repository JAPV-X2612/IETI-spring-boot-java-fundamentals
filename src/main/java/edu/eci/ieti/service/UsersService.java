package edu.eci.ieti.service;

import edu.eci.ieti.model.entity.User;
import java.util.List;
import java.util.Optional;

/**
 * Service interface for user management operations
 *
 * @author Jesús Pinzón
 * @version 1.0
 * @since 2026-03-12
 */
public interface UsersService {

    /**
     * Saves a user
     *
     * @param user User to save
     * @return Saved user with generated ID
     */
    User save(User user);

    /**
     * Finds a user by ID
     *
     * @param id User ID
     * @return Optional containing the user if found
     */
    Optional<User> findById(String id);

    /**
     * Retrieves all users
     *
     * @return List of all users
     */
    List<User> findAll();

    /**
     * Deletes a user by ID
     *
     * @param id User ID to delete
     */
    void deleteById(String id);

    /**
     * Updates an existing user
     *
     * @param id User ID to update
     * @param user Updated user data
     * @return Updated user or null if not found
     */
    User update(String id, User user);
}
