package model;

public interface EventObserver {
    void onEventCreated(Event event);
    void onEventUpdated(Event event);
    void onEventDeleted(Event event);
}
