public class Podcast implements Playable, Downloadable {

    private final String title;
    private final int episodeNo;
    private final String host;
    private final int duration; 

    private boolean downloaded;
    private PlaybackState state;
//we use the state in all of these for simpler differentiation between played/paused/stopped, by default its stopped

    public Podcast(String title, int episodeNo, String host, int duration) {
        if (title == null || title.trim().isEmpty()) {
            throw new InvalidMediaException("Podcast title cannot be null or empty.");
        }
        if (episodeNo <= 0) {
            throw new InvalidMediaException("Podcast episode number must be positive.");
        }
        if (host == null || host.trim().isEmpty()) {
            throw new InvalidMediaException("Podcast host cannot be null or empty.");
        }
        if (duration <= 0) {
            throw new InvalidMediaException("Podcast duration must be positive.");
        }

        this.title = title;
        this.episodeNo = episodeNo;
        this.host = host;
        this.duration = duration;

        this.downloaded = false;
        this.state = PlaybackState.STOPPED;
    }

    // Getters
    public String getTitle() { return title; }
    public int getEpisodeNo() { return episodeNo; }
    public String getHost() { return host; }
    public int getDuration() { return duration; }
    public boolean isDownloaded() { return downloaded; }
    public PlaybackState getState() { return state; }

    public boolean isPlaying() {
        return state == PlaybackState.PLAYING;
    }

    // Playable
    @Override
    public void play() {
        if (state == PlaybackState.PLAYING) {
            throw new InvalidMediaException("Cannot play: podcast is already playing (" + title + ", ep " + episodeNo + ").");
        }
        state = PlaybackState.PLAYING;
        System.out.println("Playing podcast: " + title + " (Ep " + episodeNo + "), host: " + host);
    }

    @Override
    public void pause() {
        if (state != PlaybackState.PLAYING) {
            throw new InvalidMediaException("Cannot pause: podcast is not playing (" + title + ", ep " + episodeNo + ").");
        }
        state = PlaybackState.PAUSED;
        System.out.println("Paused podcast: " + title + " (Ep " + episodeNo + ")");
    }

    @Override
    public void stop() {
        if (state != PlaybackState.PLAYING && state != PlaybackState.PAUSED) {
            throw new InvalidMediaException("Cannot stop: podcast is already stopped (" + title + ", ep " + episodeNo + ").");
        }
        state = PlaybackState.STOPPED;
        System.out.println("Stopped podcast: " + title + " (Ep " + episodeNo + ")");
    }

    // Downloadable
    @Override
    public void download() {
        if (downloaded) {
            throw new InvalidMediaException("Cannot download: podcast already downloaded (" + title + ", ep " + episodeNo + ").");
        }
        downloaded = true;
        System.out.println("Downloaded podcast: " + title + " (Ep " + episodeNo + ")");
    }

    @Override
    public void removeDownload() {
        if (!downloaded) {
            throw new InvalidMediaException("Cannot remove download: podcast is not downloaded (" + title + ", ep " + episodeNo + ").");
        }
        downloaded = false;
        System.out.println("Removed download for podcast: " + title + " (Ep " + episodeNo + ")");
    }

    @Override
    public boolean isDownloadable() {
        return !downloaded;
    }

    @Override
    public String toString() {
        return "Podcast{" +
                "title='" + title + '\'' +
                ", episodeNo=" + episodeNo +
                ", host='" + host + '\'' +
                ", duration=" + duration +
                ", downloaded=" + downloaded +
                ", state=" + state +
                '}';
    }
}