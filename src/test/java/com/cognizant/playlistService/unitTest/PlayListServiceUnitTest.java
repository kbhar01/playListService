package com.cognizant.playlistService.unitTest;

import com.cognizant.playlistService.entity.PlayListEntity;
import com.cognizant.playlistService.repository.PlayListRepository;
import com.cognizant.playlistService.request.PlayListDTO;
import com.cognizant.playlistService.service.PlayListService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PlayListServiceUnitTest {

    @Mock
    PlayListRepository repository;

    @InjectMocks
    PlayListService service;

    @Test
    public void fetchAllPlaylistsTest() {
        PlayListEntity playListEntity1 = new PlayListEntity();
        playListEntity1.setName("Playlist 1");
        List<String> songList = new ArrayList<String>();
        songList.add("Name of Song1");
        playListEntity1.setSongList(songList);

        PlayListEntity playListEntity2 = new PlayListEntity();
        playListEntity2.setName("Playlist 2");
        songList.add("Name of Song2");
        songList.add("Name of Song3");
        playListEntity2.setSongList(songList);

        List<PlayListEntity> playListEntityList = Arrays.asList(playListEntity1, playListEntity2);
        when(repository.findAll()).thenReturn(playListEntityList);
        List<PlayListEntity> actual = service.getAllPlayLists();
        assertEquals(actual, playListEntityList);
    }

    @Test
    public void noNamePlaylistTest()
    {
        PlayListDTO playList = new PlayListDTO();
        List<String> songList = new ArrayList<String>();
        songList.add("Name of Song1");
        playList.setSongList(songList);

        service.addPlayList(playList);

        verify(repository,never()).save(any());
    }

}
