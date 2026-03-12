package edu.eci.ieti.controller;

import edu.eci.ieti.model.entity.User;
import edu.eci.ieti.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for user management endpoints
 *
 * @author Jesús Pinzón
 * @version 1.0
 * @since 2026-03-12
 */
@RestController
@RequestMapping("/v1/users")
public class UsersController {

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    /**
     * Creates a new user
     *
     * @param user User data
     * @return Created user with HTTP 201
     */
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = usersService.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    /**
     * Retrieves all users
     *
     * @return List of users with HTTP 200
     */
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = usersService.findAll();
        return ResponseEntity.ok(users);
    }

    /**
     * Finds a user by ID
     *
     * @param id User ID
     * @return User with HTTP 200 or HTTP 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        Optional<User> user = usersService.findById(id);
        return user.map(ResponseEntity::ok)
                   .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Finds a user by email
     *
     * @param email User email
     * @return User with HTTP 200 or HTTP 404 if not found
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        Optional<User> user = usersService.findByEmail(email);
        return user.map(ResponseEntity::ok)
                   .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Updates an existing user
     *
     * @param id User ID
     * @param user Updated user data
     * @return Updated user with HTTP 200 or HTTP 404 if not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User user) {
        Optional<User> existingUser = usersService.findById(id);
        if (!existingUser.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User updatedUser = usersService.update(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    /**
     * Deletes a user by ID
     *
     * @param id User ID
     * @return HTTP 204 if deleted or HTTP 404 if not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        Optional<User> user = usersService.findById(id);
        if (!user.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        usersService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
