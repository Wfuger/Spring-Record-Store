import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by willfuger on 2/25/17.
 */

public interface AlbumRepository extends CrudRepository<Album, Long> {

}

@Entity
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Date releaseDate;
    private String artist;
    private String[] songs;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getReleaseDate() {
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

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setSongs(String[] songs) {
        this.songs = songs;
    }
}


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
        this.repository.save(album);
        return album;
    }
}
