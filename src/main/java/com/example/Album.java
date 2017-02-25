package com.example;

import org.springframework.data.repository.CrudRepository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by willfuger on 2/25/17.
 */

@Entity
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String releaseDate;
    private String artist;
    private String[] songs;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getArtist() {
        return artist;
    }

    public String[] getSongs() {
        return songs;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setSongs(String[] songs) {
        this.songs = songs;
    }
}


