package com.cognizant.playlistService.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class PlayListController {

    @GetMapping
    public ResponseEntity<?> getAllEntries() {

        return ResponseEntity.ok(null);
    }
}
