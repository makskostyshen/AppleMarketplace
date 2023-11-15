package com.example.applemarketplace.port.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/index")
public class IndexController {

    @GetMapping
    public ResponseEntity<Void> getApplicationState() {
        return ResponseEntity.ok().build();
    }
}
