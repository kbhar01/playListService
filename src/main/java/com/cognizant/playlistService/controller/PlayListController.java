package com.cognizant.playlistService.controller;

import com.cognizant.playlistService.request.PlayListDTO;
import com.cognizant.playlistService.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/")
public class PlayListController {

    List<PlayListDTO> list = new ArrayList<>();

    @GetMapping
    public ResponseEntity<?> getPlayLists() {
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<?> addNewPlayList(@RequestBody PlayListDTO playListDTO){
        list.add(playListDTO);
        Response response = new Response();
        response.setMessage("Playlist addition Successful.");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
