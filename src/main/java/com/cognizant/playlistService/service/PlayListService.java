package com.cognizant.playlistService.service;

import com.cognizant.playlistService.entity.PlayListEntity;
import com.cognizant.playlistService.repository.PlayListRepository;
import com.cognizant.playlistService.request.PlayListDTO;
import com.cognizant.playlistService.response.Response;
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

    public Response addPlayList(PlayListDTO playListDTO) {

        PlayListEntity entity = new PlayListEntity();
        entity.setName(playListDTO.getName());
        entity.setSongList(playListDTO.getSongList());
        repository.save(entity);
        Response response = new Response();
        response.setMessage("Playlist is Successfully Created.");
        return response;
    }
}
