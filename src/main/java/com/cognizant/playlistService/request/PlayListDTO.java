package com.cognizant.playlistService.request;

import lombok.Data;

import java.util.List;

@Data
public class PlayListDTO {
    private String name;
    private List<String> songList;
}
