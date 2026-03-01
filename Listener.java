import java.util.ArrayList;
import java.util.List;

public class Listener extends User {

    private final List<Playlist> playlists;
    private final List<Song> likedSongs;
    private final List<Album> savedAlbums;
    private final Subscription subscription;
//calls for the subscription class to be used as an attrib.
        public Listener(String name, String email, String password) {
        super(name, email, password);
        this.subscription = new Subscription();

        this.playlists = new ArrayList<>();
        this.likedSongs = new ArrayList<>();
        this.savedAlbums = new ArrayList<>();
    }

    public Subscription getSubscription() {
        return subscription;
    }

    // uses subscription class methods to subscribe cancel etc
    public void subscribe(String plan) {
        subscription.subscribe(plan);
    }

    public void cancelSubscription() {
        subscription.cancel();
    }

    public boolean hasActiveSubscription() {
        return subscription.isActive();
    }


    // Playlists
    public Playlist createPlaylist(String playlistName) {
        Playlist p = new Playlist(playlistName);
        playlists.add(p);
        System.out.println(getName() + " created playlist: " + playlistName);
        return p;
    }

    public void addPlaylist(Playlist playlist) {
        if (playlist == null) {
            throw new InvalidMediaException("Cannot add null playlist to listener.");
        }
        playlists.add(playlist);
        System.out.println(getName() + " added playlist: " + playlist.getName());
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    // Likes / Saves
    public void likeSong(Song song) {
        if (song == null) {
            throw new InvalidMediaException("Cannot like a null song.");
        }
        likedSongs.add(song);
        System.out.println(getName() + " liked song: " + song.getTitle());
    }

    public void saveAlbum(Album album) {
        if (album == null) {
            throw new InvalidMediaException("Cannot save a null album.");
        }
        savedAlbums.add(album);
        System.out.println(getName() + " saved album: " + album.getName());
    }

    public List<Song> getLikedSongs() {
        return likedSongs;
    }

    public List<Album> getSavedAlbums() {
        return savedAlbums;
    }

    public void addToPlaylist(Playlist playlist, Playable item) {
    if (playlist == null) throw new InvalidMediaException("Playlist cannot be null.");
    if (item == null) throw new InvalidMediaException("Item cannot be null.");
    playlist.addItem(item);
}

    public void displayProfile() {
        System.out.println("Listener: " + getName());
        System.out.println("Email: " + getEmail());
        System.out.println("Playlists: " + playlists.size());
        System.out.println("Liked songs: " + likedSongs.size());
        System.out.println("Saved albums: " + savedAlbums.size());
    }
}