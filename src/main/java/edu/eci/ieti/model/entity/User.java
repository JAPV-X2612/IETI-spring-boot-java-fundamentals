package edu.eci.ieti.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User entity model
 *
 * @author Jesús Pinzón
 * @version 1.0
 * @since 2026-03-12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    private String id;
    private String name;
    private String email;
}
