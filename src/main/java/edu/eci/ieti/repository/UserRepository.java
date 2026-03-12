package edu.eci.ieti.repository;

import edu.eci.ieti.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * JPA repository for User entity
 *
 * @author Jesús Pinzón
 * @version 1.0
 * @since 2026-03-12
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByEmail(String email);
}
