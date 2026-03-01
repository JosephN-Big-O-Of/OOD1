//logically our playlist should allow to start each item or to start/pause the whole playlist, hence the playAll we will implement

import java.util.ArrayList;
import java.util.List;

public class Playlist implements Playable {

  //can hold anthng including albums/playlists if we need

    private final String name;
    private final List<Playable> items;
    private int currentIndex;

//playlist starts emty, we only have name, cant be null
    public Playlist(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidMediaException("Playlist name cannot be null or empty.");
        }
        this.name = name;
        this.items = new ArrayList<>();
        this.currentIndex = 0;
    }

    public String getName() {
        return name;
    }

    public List<Playable> getItems() {
        return items;
    }
    //fine for now, we can copy to another list and return it, change later

    public int size() {
        return items.size();
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public Playable getCurrentItem() {
        if (items.isEmpty()) {
            throw new InvalidMediaException("Playlist is empty. No current item to return.");
        }
        if (currentIndex < 0 || currentIndex >= items.size()) {
            throw new InvalidMediaException("Current index out of bounds: " + currentIndex);
        }
        return items.get(currentIndex);
    }

    public void addItem(Playable item) {
        if (item == null) {
            throw new InvalidMediaException("Cannot add null item to playlist.");
        }
        items.add(item);
        System.out.println("Added item to playlist '" + name + "'. Total items: " + items.size());
    }

    public void removeItem(int index) {
        if (items.isEmpty()) {
            throw new InvalidMediaException("Cannot remove from an empty playlist.");
        }
        if (index < 0 || index >= items.size()) {
            throw new InvalidMediaException("Invalid index: " + index);
        }
        Playable removed = items.remove(index);

        // Keep currentIndex valid after removal
        if (currentIndex >= items.size()) {
            currentIndex = Math.max(0, items.size() - 1);
        }

        System.out.println("Removed item from playlist '" + name + "': " + removed);
    }

    public void next() {
        if (items.isEmpty()) {
            throw new InvalidMediaException("Playlist is empty. Cannot go to next item.");
        }
        currentIndex = (currentIndex + 1) % items.size();
        System.out.println("Moved to next item. Current index: " + currentIndex);
    }

    public void previous() {
        if (items.isEmpty()) {
            throw new InvalidMediaException("Playlist is empty. Cannot go to previous item.");
        }
        currentIndex = (currentIndex - 1 + items.size()) % items.size();
        System.out.println("Moved to previous item. Current index: " + currentIndex);
    }

    // Playable implementation controls the current item
    @Override
    public void play() {
        Playable current = getCurrentItem();
        System.out.println("Playlist '" + name + "' playing current item at index " + currentIndex + ":");
        current.play();
    }

    @Override
    public void pause() {
        Playable current = getCurrentItem();
        System.out.println("Playlist '" + name + "' pausing current item:");
        current.pause();
    }

    @Override
    public void stop() {
        Playable current = getCurrentItem();
        System.out.println("Playlist '" + name + "' stopping current item:");
        current.stop();
    }

//we do playAll here, but in main if we start multiple songs at the same time we may run into an issue, hence we try and catch by stopping the song before playing the one after.
  
    public void playAll() {
        if (items.isEmpty()) {
            throw new InvalidMediaException("Playlist is empty. Nothing to play.");
        }

        System.out.println("Playing all items in playlist '" + name + "':");
        for (int i = 0; i < items.size(); i++) {
            Playable item = items.get(i);
            System.out.println("Item " + (i + 1) + "/" + items.size() + ":");
            item.play();

           
            try {
                item.stop();
            } catch (InvalidMediaException ex) {
                

                System.out.println("Note: " + ex.getMessage());
            }
        }
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "name='" + name + '\'' +
                ", items=" + items.size() +
                ", currentIndex=" + currentIndex +
                '}';
    }
}