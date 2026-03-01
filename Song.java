public class Song implements Playable, Downloadable {

    private final String id;
    private final String title;
    private final int duration;
    private final Artist artist;
    private final String genre;

    private boolean downloaded;
    private PlaybackState state;

//constructor + checks for correct value otherwise we throw exception

//we can use isBlank in newer java versions instead of trim

    public Song(String id, String title, int duration, Artist artist, String genre) {
      if (id == null || id.isBlank()) {
            throw new InvalidMediaException("Song id cannot be null/blank.");
        }
        if (title == null || title.isBlank()) {
            throw new InvalidMediaException("Song title cannot be null/blank.");
        }
        if (duration <= 0) {
            throw new InvalidMediaException("Song duration must be > 0.");
        }
        if (artist == null) {
            throw new InvalidMediaException("Song artist cannot be null.");
        }
        if (genre == null || genre.isBlank()) {
            throw new InvalidMediaException("Song genre cannot be null/blank.");
        }

        this.id = id;
        this.title = title;
        this.duration = duration;
        this.artist = artist;
        this.genre = genre;

        this.downloaded = false;
        this.state = PlaybackState.STOPPED;
    } 
    //this is for when we need to differentiate between the states of the songs properly, rather than using a playing true or false. using the enum.

    //getters


    public String getId() { return id; }
    public String getTitle() { return title; }
    public int getDuration() { return duration; }
    public Artist getArtist() { return artist; }
    public String getGenre() { return genre; }
    public boolean isDownloaded() { return downloaded; }
    public PlaybackState getState() { return state; }

     public boolean isPlaying() {
        return state == PlaybackState.PLAYING;
     }

     //returns current state


         //we override playable now

    @Override
    public void play() {
       if (state == PlaybackState.PLAYING) {
            throw new InvalidMediaException("Cannot play: song is already playing (" + title + ").");
        }
        state = PlaybackState.PLAYING;
        System.out.println("Playing song: " + title + " by " + artist.getArtistName());
    }

    @Override
    public void pause() {
        if (state != PlaybackState.PLAYING) {
            throw new InvalidMediaException("Cannot pause: song is not playing (" + title + ").");
        }
        state = PlaybackState.PAUSED;
        System.out.println("Paused song: " + title);
    }
    @Override
    public void stop() {
        if (state != PlaybackState.PLAYING && state != PlaybackState.PAUSED) {
            throw new InvalidMediaException("Cannot stop: song is already stopped (" + title + ").");
        }
        state = PlaybackState.STOPPED;
        System.out.println("Stopped song: " + title);
    }


   //we override downloadable now

    @Override
    public void download() {
        if (downloaded) {
            throw new InvalidMediaException("Cannot download: song is already downloaded (" + title + ").");
        }
        downloaded = true;
        System.out.println("Downloaded song: " + title);
    }

    @Override
    public void removeDownload() {
        if (!downloaded) {
            throw new InvalidMediaException("Cannot remove download: song is not downloaded (" + title + ").");
        }
        downloaded = false; //CHANGES boolean
        System.out.println("Removed download for song: " + title);
    }

    @Override
    public boolean isDownloadable() {
        return !downloaded;
    } 
     @Override
    public String toString() {
        return "Song{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", duration=" + duration +
                ", artist=" + artist.getArtistName() +
                ", genre='" + genre + '\'' +
                ", downloaded=" + downloaded +
                ", state=" + state +
                '}';
    }
}