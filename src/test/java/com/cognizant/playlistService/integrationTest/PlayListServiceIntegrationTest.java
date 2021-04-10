package com.cognizant.playlistService.integrationTest;

import com.cognizant.playlistService.request.PlayListDTO;
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

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
//@ActiveProfiles("qa")
//@AutoConfigureRestDocs
public class PlayListServiceIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @BeforeEach
    void cleanUp(){

    }

    @Test
    public void getAllEntriesTest() throws Exception {

        RequestBuilder requestBuilder = get("/");
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)))
                ;
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
                ;
    }
}
