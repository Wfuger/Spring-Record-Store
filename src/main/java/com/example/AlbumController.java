package com.example;

import org.springframework.web.bind.annotation.*;

/**
 * Created by willfuger on 2/25/17.
 */

@RestController
@RequestMapping("/albums")
public class AlbumController {

    private final AlbumRepository repository;

    public AlbumController(AlbumRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public Iterable<Album> findAll() {
        return this.repository.findAll();
    }


    @PostMapping("")
    public Album saveAlbum(@RequestBody Album album) {
        System.out.println(String.format("********** WILL FUCKING ALBUM NAME ******** %s", album.getName()));
        this.repository.save(album);
        return album;
    }
}
