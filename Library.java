import java.util.ArrayList;
import java.util.List;

public class Library {

    private final List<Song> songs;
    private final List<Podcast> podcasts;
    private final List<Album> albums;
    private final List<Playlist> playlists;

    public Library() {
        this.songs = new ArrayList<>();
        this.podcasts = new ArrayList<>();
        this.albums = new ArrayList<>();
        this.playlists = new ArrayList<>();
    }

    public void addSong(Song song) {
        if (song == null) throw new InvalidMediaException("Cannot add null song to library.");
        songs.add(song);
        System.out.println("Song added to library: " + song.getTitle());
    }

    public void addPodcast(Podcast podcast) {
        if (podcast == null) throw new InvalidMediaException("Cannot add null podcast to library.");
        podcasts.add(podcast);
        System.out.println("Podcast added to library: " + podcast.getTitle() + " (Ep " + podcast.getEpisodeNo() + ")");
    }

    public void addAlbum(Album album) {
        if (album == null) throw new InvalidMediaException("Cannot add null album to library.");
        albums.add(album);
        System.out.println("Album added to library: " + album.getName());
    }

    public void addPlaylist(Playlist playlist) {
        if (playlist == null) throw new InvalidMediaException("Cannot add null playlist to library.");
        playlists.add(playlist);
        System.out.println("Playlist added to library: " + playlist.getName());
    }

    public List<Song> getSongs() { return songs; }
    public List<Podcast> getPodcasts() { return podcasts; }
    public List<Album> getAlbums() { return albums; }
    public List<Playlist> getPlaylists() { return playlists; }

    // Checked exception searches
    public Song findSongByTitle(String title) throws MediaNotFoundException {
        if (title == null || title.trim().isEmpty()) {
            throw new InvalidMediaException("Search title cannot be null/empty.");
        }
        for (Song s : songs) {
            if (s.getTitle().equalsIgnoreCase(title.trim())) return s;
        }
        throw new MediaNotFoundException("Song not found with title: " + title);
    }

    public Album findAlbumByName(String name) throws MediaNotFoundException {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidMediaException("Album name cannot be null/empty.");
        }
        for (Album a : albums) {
            if (a.getName().equalsIgnoreCase(name.trim())) return a;
        }
        throw new MediaNotFoundException("Album not found with name: " + name);
    }

    public Playlist findPlaylistByName(String name) throws MediaNotFoundException {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidMediaException("Playlist name cannot be null/empty.");
        }
        for (Playlist p : playlists) {
            if (p.getName().equalsIgnoreCase(name.trim())) return p;
        }
        throw new MediaNotFoundException("Playlist not found with name: " + name);
    }

    public Podcast findPodcastByTitleAndEpisode(String title, int episodeNo) throws MediaNotFoundException {
        if (title == null || title.trim().isEmpty()) {
            throw new InvalidMediaException("Podcast title cannot be null/empty.");
        }
        if (episodeNo <= 0) {
            throw new InvalidMediaException("Episode number must be positive.");
        }

        for (Podcast p : podcasts) {
            if (p.getTitle().equalsIgnoreCase(title.trim()) && p.getEpisodeNo() == episodeNo) return p;
        }
        throw new MediaNotFoundException("Podcast not found: " + title + " (Ep " + episodeNo + ")");
    }
}