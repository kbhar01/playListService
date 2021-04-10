package com.cognizant.playlistService.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "play_list")
@Data
public class PlayListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    Long Id;

    String name;
    @ElementCollection
    List<String> songList;

}
