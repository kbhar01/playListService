package com.cognizant.playlistService.service;

import com.cognizant.playlistService.entity.PlayListEntity;
import com.cognizant.playlistService.repository.PlayListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayListService {

    @Autowired
    PlayListRepository repository;

    public List<PlayListEntity> getAllPlayLists() {

        return this.repository.findAll();

    }
}
