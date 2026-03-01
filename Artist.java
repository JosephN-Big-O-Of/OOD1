import java.util.ArrayList;
import java.util.List;

public class Artist extends User {

    private String genre;
    private String bio;
    private List<Song> songs;
    private List<Album> albums;

//we use super because user is the parent class, easier call + no null constructor, only the one with params.
    public Artist(String name, String email, String password, String genre, String bio) {
        super(name, email, password); 

        if (genre == null || genre.trim().isEmpty()) {
            throw new InvalidMediaException("Artist genre cannot be null or empty");
        }
        if (bio == null) {
            throw new InvalidMediaException("Artist bio cannot be null");
        }

        this.genre = genre;
        this.bio = bio;
        this.songs = new ArrayList<>();
        this.albums = new ArrayList<>();
    }

    public String getArtistName() {
     
        return getName();
    }

    public String getGenre() {
        return genre;
    }

    public void uploadSong(Song s) {
        songs.add(s);
        System.out.println("Song uploaded successfully by " + getArtistName());
    }

    public Album createAlbum(String albumName) {
        Album album = new Album(albumName, this);
        albums.add(album);
        System.out.println("Album created: " + albumName);
        return album;
    }
//calls add song from Album, and adds the given song
    public void addSongToAlbum(Song song, Album album) {
        album.addSong(song);
        System.out.println("Song added to album: " + album);
    }

    public void displayProfile() {
        System.out.println("Artist Name: " + getArtistName());
        System.out.println("Genre: " + genre);
        System.out.println("Bio: " + bio);
        System.out.println("Total Songs: " + songs.size());
        System.out.println("Total Albums: " + albums.size());
    }
}