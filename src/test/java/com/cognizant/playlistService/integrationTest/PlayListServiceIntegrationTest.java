package com.cognizant.playlistService.integrationTest;

import com.cognizant.playlistService.request.PlayListDTO;
import com.cognizant.playlistService.request.PlayListSongDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("qa")
@AutoConfigureRestDocs
public class PlayListServiceIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @BeforeEach
    void cleanUp(){

    }

    @Test
    public void getZeroEntriesTest() throws Exception {

        RequestBuilder requestBuilder = get("/");
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

    }

    @Test
    public void postAnEntryTest() throws Exception {

        PlayListDTO tempRequest = new PlayListDTO();
        tempRequest.setName("Playlist Name");
        List<String> songList = new ArrayList<String>();
        songList.add("Name of Song");
        tempRequest.setSongList(songList);

        RequestBuilder rq = post("/")
                .content(mapper.writeValueAsString(tempRequest))
                .contentType(MediaType.APPLICATION_JSON)
                ;

        mockMvc.perform(rq)
                .andExpect(status().isCreated())
                .andDo(print())
                .andDo(document("AddPlayList", responseFields(
                        fieldWithPath("message").description("Response Message.")
                )));

    }

    @Test
    public void postAndGetPlaylistBack() throws Exception {
        PlayListDTO tempRequest = new PlayListDTO();
        tempRequest.setName("Playlist Name");
        List<String> songList = new ArrayList<String>();
        songList.add("Name of Song");



        RequestBuilder rq = post("/")
                .content(mapper.writeValueAsString(tempRequest))
                .contentType(MediaType.APPLICATION_JSON)
                ;

        mockMvc.perform(rq)
                .andExpect(status().isCreated())
        ;

        RequestBuilder requestBuilder = get("/");
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("[0].name").value("Playlist Name"))
                .andDo(document("PlayList", responseFields(
                        fieldWithPath("[0].name").description("Name of Playlist."),
                        fieldWithPath("[0].songList").description("List of Songs.")
                )));
    }

    @Test
    public void addSongToExisitingPlaylist() throws Exception {
        PlayListDTO tempRequest = new PlayListDTO();
        tempRequest.setName("Playlist Name");
        List<String> songList = new ArrayList<String>();
        songList.add("Name of Song");
        tempRequest.setSongList(songList);

        PlayListSongDTO playListSongDTO = new PlayListSongDTO();
        playListSongDTO.setName("Playlist Name");
        playListSongDTO.setSong("Song 2");




        RequestBuilder rq = post("/")
                .content(mapper.writeValueAsString(tempRequest))
                .contentType(MediaType.APPLICATION_JSON)
                ;

        mockMvc.perform(rq)
                .andExpect(status().isCreated())
        ;

        RequestBuilder rq2 = post("/addsong")
                .content(mapper.writeValueAsString(playListSongDTO))
                .contentType(MediaType.APPLICATION_JSON)
                ;

        mockMvc.perform(rq2)
                .andExpect(status().isAccepted())
        ;

        RequestBuilder requestBuilder = get("/");
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("[0].name").value("Playlist Name"))
                .andExpect(jsonPath("[0].songList[0]").value("Name of Song"))
                .andExpect(jsonPath("[0].songList[1]").value("Song 2"))
                .andDo(print())
                .andDo(document("AddSongToPlayList", responseFields(
                        fieldWithPath("[0].name").description("Name of Playlist."),
                        fieldWithPath("[0].songList[0]").description("Song 1"),
                        fieldWithPath("[0].songList[1]").description("Song 2")
                )));
    }


    @Test
    public void deleteSongFromPlaylist() throws Exception {

        PlayListDTO tempRequest = new PlayListDTO();
        tempRequest.setName("Playlist Name");
        List<String> songList = new ArrayList<String>();
        songList.add("Song 1");
        songList.add("Song 2");
        songList.add("Song 3");
        tempRequest.setSongList(songList);

        PlayListSongDTO playListSongDTO = new PlayListSongDTO();
        playListSongDTO.setName("Playlist Name");
        playListSongDTO.setSong("Song 2");


        RequestBuilder rq = post("/")
                .content(mapper.writeValueAsString(tempRequest))
                .contentType(MediaType.APPLICATION_JSON)
                ;

        mockMvc.perform(rq)
                .andExpect(status().isCreated())
        ;

        RequestBuilder rq2 = post("/deletesong")
                .content(mapper.writeValueAsString(playListSongDTO))
                .contentType(MediaType.APPLICATION_JSON)
                ;

        mockMvc.perform(rq2)
                .andExpect(status().isAccepted())
                .andDo(document("DeleteSongFromPlayList"))
        ;

        RequestBuilder requestBuilder = get("/");
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("[0].name").value("Playlist Name"))
                .andExpect(jsonPath("[0].songList[0]").value("Song 1"))
                .andExpect(jsonPath("[0].songList[1]").value("Song 3"))
                .andDo(print())
                .andDo(document("DeleteSongFromPlayList", responseFields(
                        fieldWithPath("[0].name").description("Name of Playlist."),
                        fieldWithPath("[0].songList[0]").description("Song 1"),
                        fieldWithPath("[0].songList[1]").description("Song 3")
                )));


    }
}
