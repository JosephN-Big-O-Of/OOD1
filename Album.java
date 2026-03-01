import java.util.ArrayList;
import java.util.List;

public class Album implements Playable, Downloadable {

    private final String name;
    private final Artist artist;
    private final List<Song> songs;

    private boolean downloaded;
    private PlaybackState state;

    public Album(String name, Artist artist) {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidMediaException("Album name cannot be null or empty.");
        }
        if (artist == null) {
            throw new InvalidMediaException("Album artist cannot be null.");
        }

        this.name = name;
        this.artist = artist;
        this.songs = new ArrayList<>();

        this.downloaded = false;
        this.state = PlaybackState.STOPPED;
    }

    // Getters
    public String getName() { return name; }
    public Artist getArtist() { return artist; }
    public List<Song> getSongs() { return songs; } 
    public boolean isDownloaded() { return downloaded; }
    public PlaybackState getState() { return state; }

    public int size() {
        return songs.size();
    }

    public void addSong(Song song) {
        if (song == null) {
            throw new InvalidMediaException("Cannot add null song to album.");
        }
        songs.add(song);
        System.out.println("Added song to album '" + name + "': " + song.getTitle());
    }

    // Playable
    @Override
    public void play() {
        if (songs.isEmpty()) {
            throw new InvalidMediaException("Cannot play album: album '" + name + "' has no songs.");
        }
        if (state == PlaybackState.PLAYING) {
            throw new InvalidMediaException("Cannot play album: already playing (" + name + ").");
        }

        state = PlaybackState.PLAYING;
        System.out.println("Playing album: " + name + " by " + artist.getArtistName());

      
        songs.get(0).play();
    }

    @Override
    public void pause() {
        if (state != PlaybackState.PLAYING) {
            throw new InvalidMediaException("Cannot pause album: album is not playing (" + name + ").");
        }
        state = PlaybackState.PAUSED;
        System.out.println("Paused album: " + name);

        
        try {
            songs.get(0).pause();
        } catch (Exception ignored) {
            // keep album pause independent of track state for simplicity
        }
    }

    @Override
    public void stop() {
        if (state != PlaybackState.PLAYING && state != PlaybackState.PAUSED) {
            throw new InvalidMediaException("Cannot stop album: album already stopped (" + name + ").");
        }
        state = PlaybackState.STOPPED;
        System.out.println("Stopped album: " + name);

        try {
            songs.get(0).stop();
        } catch (Exception ignored) {
        }
    }
    //try and catch to stop the song if the album is stopped, since its 2 diff entities

    // Downloadable
    @Override
    public void download() {
        if (downloaded) {
            throw new InvalidMediaException("Cannot download: album already downloaded (" + name + ").");
        }
        downloaded = true;
        System.out.println("Downloaded album: " + name);
    }

    @Override
    public void removeDownload() {
        if (!downloaded) {
            throw new InvalidMediaException("Cannot remove download: album is not downloaded (" + name + ").");
        }
        downloaded = false;
        System.out.println("Removed download for album: " + name);
    }

    @Override
    public boolean isDownloadable() {
        return !downloaded;
    }

    @Override
    public String toString() {
        return "Album{" +
                "name='" + name + '\'' +
                ", artist=" + artist.getArtistName() +
                ", songs=" + songs.size() +
                ", downloaded=" + downloaded +
                ", state=" + state +
                '}';
    }
}