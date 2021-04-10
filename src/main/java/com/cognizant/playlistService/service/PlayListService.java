package com.cognizant.playlistService.service;

import com.cognizant.playlistService.entity.PlayListEntity;
import com.cognizant.playlistService.repository.PlayListRepository;
import com.cognizant.playlistService.request.PlayListDTO;
import com.cognizant.playlistService.request.PlayListSongDTO;
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

        Response response = new Response();
        if(playListDTO == null || playListDTO.getName() == null || playListDTO.getName().isEmpty())
        {
            response.setMessage("A name is required to create a Playlist.");
        }
        else
        {
            List<PlayListEntity> existingPlaylists = this.repository.findAll();
            for (PlayListEntity entity : existingPlaylists)
            {
                if(entity.getName().equalsIgnoreCase(playListDTO.getName()))
                {
                    response.setMessage("Playlist NOT created. Playlist already exists.");
                    return response;
                }
            }

            PlayListEntity entity = new PlayListEntity();
            entity.setName(playListDTO.getName());
            entity.setSongList(playListDTO.getSongList());
            repository.save(entity);
            response.setMessage("Playlist is Successfully Created.");
        }
        return response;
    }

    public Response addSongToPlayList(PlayListSongDTO playListSongDTO) {
        Response response = new Response();

        List<PlayListEntity> existingPlaylists = this.repository.findAll();
        for (PlayListEntity entity : existingPlaylists)
        {
            if(entity.getName().equalsIgnoreCase(playListSongDTO.getName()))
            {
                entity.getSongList().add(playListSongDTO.getSong());
                repository.save(entity);
                response.setMessage("Song added successfully to the playlist.");
            }
        }

        return response;
    }

    public Response deleteSongFromPlaylist(PlayListSongDTO playListSongDTO) {

        Response response = new Response();

        List<PlayListEntity> existingPlaylists = this.repository.findAll();
        for (PlayListEntity entity : existingPlaylists)
        {
            if(entity.getName().equalsIgnoreCase(playListSongDTO.getName()))
            {
                entity.getSongList().remove(playListSongDTO.getSong());
                repository.save(entity);
                response.setMessage("Song has been removed successfully from the playlist.");
            }
        }

        return response;

    }
}
