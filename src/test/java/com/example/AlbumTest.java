package com.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.Collections;
import java.util.Date;
import java.util.Random;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by willfuger on 2/25/17.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(AlbumController.class)
public class AlbumTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    AlbumRepository repository;


    private Gson gson = new GsonBuilder().create();

    @Test
    public void testGetAllAlbums() throws Exception {
        Long id = new Random().nextLong();
        Album album = new Album();
        String[] songs = new String[] {"Breathe", "On the Run", "Time", "The Great Gig in the Sky", "Money", "Us and Them", "Any Colour You Like", "Brain Damage", "Eclipse"};
        album.setId(id);
        album.setArtist("Pink Floyd");
        album.setName("Dark Side of the Moon");
        album.setReleaseDate("March 1, 1973");
        album.setSongs(songs);

        when(this.repository.findAll()).thenReturn(Collections.singletonList(album));

        MockHttpServletRequestBuilder request = get("/albums")
                .contentType(MediaType.APPLICATION_JSON);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", equalTo(id)))
                .andExpect(jsonPath("$[0].artist", equalTo("Pink Floyd")))
                .andExpect(jsonPath("$[0].name", equalTo("Dark Side of the Moon")));

    }

    @Test
    public void testPostAlbum() throws Exception {
        Long id = new Random().nextLong();
        Album album = new Album();
        String[] songs = new String[] {"Breathe", "On the Run", "Time", "The Great Gig in the Sky", "Money", "Us and Them", "Any Colour You Like", "Brain Damage", "Eclipse"};
        album.setId(id);
        album.setArtist("Pink Floyd");
        album.setName("Dark Side of the Moon");
        album.setReleaseDate("March 1, 1973");
        album.setSongs(songs);

        MockHttpServletRequestBuilder request = post("/albums")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(album));

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.artist", equalTo("Pink Floyd")))
                .andExpect(jsonPath("$.id", equalTo(id)))
                .andExpect(jsonPath("$.releaseDate", equalTo("March 1, 1973")));

        verify(this.repository).save(any(Album.class));

    }

}
