package pl.dchruscinski.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/healthcheck")
public class HealthCheckController {
    public static final Logger logger = LoggerFactory.getLogger(HealthCheckController.class);
    public static final String SIMPLE_FORMAT = "simple";
    public static final String DETAILED_FORMAT = "detailed";
    public static final String HEALTH_REDIRECT = "/actuator/health";
    public static final String INFO_REDIRECT = "/actuator/info";

    @GetMapping
    public ResponseEntity<?> getHealthCheck(@RequestParam String format, HttpServletResponse response) throws IOException {
        if (format.equalsIgnoreCase(SIMPLE_FORMAT)) {
            logger.debug("getHealthCheck(): returning simple healthcheck.");
            response.sendRedirect(HEALTH_REDIRECT);
        } else if (format.equalsIgnoreCase(DETAILED_FORMAT)) {
            logger.debug("getHealthCheck(): returning detailed healthcheck.");
            response.sendRedirect(INFO_REDIRECT);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping(path = "/message", produces = MediaType.APPLICATION_JSON_VALUE)
    // @GetMapping(path = "/message", produces = "application/json")
    public ResponseEntity<?> getCustomJsonMessage(@RequestParam String format) {
        if (format.equalsIgnoreCase(SIMPLE_FORMAT)) {
            logger.debug("getCustomJsonMessage(): returning simple message.");
            return ResponseEntity.ok(Collections.singletonMap("status", "OK"));

        } else if (format.equalsIgnoreCase(DETAILED_FORMAT)) {
            Map<String, String> jsonMessageMap = new HashMap<>();
            jsonMessageMap.put("currentTime", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            jsonMessageMap.put("status", "OK");
            logger.debug("getCustomJsonMessage(): returning detailed message.");
            return ResponseEntity.ok(jsonMessageMap);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping
    public ResponseEntity<Void> postHealthCheck() {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
    }

    @PutMapping
    public ResponseEntity<Void> putHealthCheck() {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteHealthCheck() {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
    }
}