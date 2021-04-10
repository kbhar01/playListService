package com.cognizant.playlistService.unitTest;

import com.cognizant.playlistService.entity.PlayListEntity;
import com.cognizant.playlistService.repository.PlayListRepository;
import com.cognizant.playlistService.service.PlayListService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlayListServiceUnitTest {

    @Mock
    PlayListRepository repository;

    @InjectMocks
    PlayListService service;

    @Test
    public void fetchAllPlaylistsTest() {
        PlayListEntity playListEntity1 = new PlayListEntity();
        PlayListEntity playListEntity2 = new PlayListEntity();

        List<PlayListEntity> playListEntityList = Arrays.asList(playListEntity1, playListEntity2);
        when(repository.findAll()).thenReturn(playListEntityList);

        List<PlayListEntity> actual = service.getAllPlayLists();

        assertEquals(actual, playListEntityList);
    }

}
