package org.data.traffic_01.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class TrafficController {

    @GetMapping("/greet")
    public ResponseEntity<String> greet() {
        return ResponseEntity.ok("Hello from Spring Boot!");
    }

    @PostMapping("/data")
    public ResponseEntity<String> receiveData(@RequestBody Map<String, String> requestData) {
        String name = requestData.get("name");
        return ResponseEntity.ok("Hello, " + name + "!");
    }
}
