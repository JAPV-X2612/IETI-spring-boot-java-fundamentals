package edu.eci.ieti.controller.health;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for health check endpoints
 *
 * @author Jesús Pinzón
 * @version 1.0
 * @since 2026-03-12
 */
@RestController
@RequestMapping("/health")
public class HealthController {

    @GetMapping
    public String checkAPI() {
        return "<h1>The API is working!</h1>";
    }
}
