public class MusicApp {
    public static void main(String[] args) {

        System.out.println("=== START SYSTEM TEST ===\n");

        // ----------------------------
        // 1) Users + inheritance
        // ----------------------------
        System.out.println("== Users (inheritance) ==");
        Artist artist = new Artist("Drake", "drake@email.com", "pass123", "Rap", "Bio text");
        artist.login();
        artist.displayProfile();

        Listener listener = new Listener("Joseph", "joseph@email.com", "pw123");
        listener.login();
        listener.displayProfile();

        // ----------------------------
        // 2) Create media (Playable + Downloadable)
        // ----------------------------
        System.out.println("\n== Create media ==");
        Song song1 = new Song("S1", "God's Plan", 210, artist, "Rap");
        Song song2 = new Song("S2", "One Dance", 190, artist, "Rap");
        Podcast pod1 = new Podcast("CS Talk", 1, "Professor", 1200);

        Album album1 = new Album("Scorpion", artist);
        album1.addSong(song1);
        album1.addSong(song2);

        // Artist actions
        System.out.println("\n== Artist actions ==");
        artist.uploadSong(song1);
        artist.uploadSong(song2);

        // ----------------------------
        // 3) Playlist polymorphism: Song + Podcast + Album in one list
        // ----------------------------
        System.out.println("\n== Playlist polymorphism test ==");
        Playlist mix = new Playlist("Mixed Media");
        mix.addItem(song1);
        mix.addItem(pod1);
        mix.addItem(album1);

        System.out.println("\n--- playAll() ---");
        mix.playAll();

        System.out.println("\n--- next(), play() current item ---");
        mix.next();
        mix.play();

        System.out.println("\n--- previous(), play() current item ---");
        mix.previous();
        mix.play();

        // ----------------------------
        // 4) Downloadable interface test
        // ----------------------------
        System.out.println("\n== Downloadable test ==");
        song1.download();
        pod1.download();
        album1.download();

        // Force an InvalidMediaException by downloading again
        System.out.println("\n--- force InvalidMediaException (download twice) ---");
        try {
            song1.download();
        } catch (InvalidMediaException e) {
            System.out.println("Caught InvalidMediaException: " + e.getMessage());
        }

        // ----------------------------
        // 5) Playback state rules (InvalidMediaException)
        // ----------------------------
        System.out.println("\n== Playback state exception test ==");
        // Song starts STOPPED, so pause should throw
        try {
            song2.pause();
        } catch (InvalidMediaException e) {
            System.out.println("Caught InvalidMediaException: " + e.getMessage());
        }

        // Normal play/pause/stop sequence
        System.out.println("\n--- normal play/pause/stop ---");
        song2.play();
        song2.pause();
        song2.stop();

        // ----------------------------
        // 6) Library tests + CHECKED exception try/catch (MediaNotFoundException)
        // ----------------------------
        System.out.println("\n== Library tests (checked exception) ==");
        Library library = new Library();
        library.addSong(song1);
        library.addPodcast(pod1);
        library.addAlbum(album1);
        library.addPlaylist(mix);

        System.out.println("\n--- find existing song (should succeed) ---");
        try {
            Song found = library.findSongByTitle("God's Plan");
            System.out.println("Found song: " + found.getTitle());
        } catch (MediaNotFoundException e) {
            System.out.println("Caught MediaNotFoundException: " + e.getMessage());
        }

        System.out.println("\n--- find missing song (should throw checked exception) ---");
        try {
            library.findSongByTitle("Definitely Not In Library");
            System.out.println("ERROR: should not print (song should be missing).");
        } catch (MediaNotFoundException e) {
            System.out.println("Caught MediaNotFoundException: " + e.getMessage());
        }

        // ----------------------------
        // 7) Subscription / Subscribable test
        // ----------------------------
        System.out.println("\n== Subscription (Subscribable) test ==");
        // If your Listener has a subscription field, use that.
        // Otherwise, create a Subscription directly.
        try {
            Subscription sub = new Subscription();
            sub.subscribe("PREMIUM");
            System.out.println("Active? " + sub.isActive());
            sub.notifyUser();
            sub.cancel();
            System.out.println("Active? " + sub.isActive());
            sub.notifyUser();
        } catch (Exception e) {
            System.out.println("Subscription test error: " + e.getMessage());
        }

        // ----------------------------
        // 8) Listener features (if your Listener has these)
        // ----------------------------
        System.out.println("\n== Listener features test ==");
        try {
            // if these methods exist in your Listener
            Playlist p = listener.createPlaylist("Joseph Playlist");
            listener.likeSong(song1);
            listener.saveAlbum(album1);
            p.addItem(song1);
            p.addItem(pod1);
            p.playAll();
        } catch (Exception e) {
            System.out.println("Listener feature test skipped/failed (method mismatch): " + e.getMessage());
        }

        System.out.println("\n=== END SYSTEM TEST ===");
    }
}