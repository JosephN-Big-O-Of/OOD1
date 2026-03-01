public interface Subscribable {
    void subscribe(String plan);
    void cancel();
    void notifyUser();
    boolean isActive();
}
