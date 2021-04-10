package com.cognizant.playlistService.controller;

import com.cognizant.playlistService.request.PlayListDTO;
import com.cognizant.playlistService.response.Response;
import com.cognizant.playlistService.service.PlayListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/")
public class PlayListController {

    @Autowired
    PlayListService playListService;

    @GetMapping
    public ResponseEntity<?> getPlayLists() {
        return ResponseEntity.ok(playListService.getAllPlayLists());
    }

    @PostMapping
    public ResponseEntity<?> addNewPlayList(@RequestBody PlayListDTO playListDTO){
        return new ResponseEntity<>(playListService.addPlayList(playListDTO),HttpStatus.CREATED);
    }
}
