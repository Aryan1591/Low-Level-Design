package service;

import model.Attendee;
import model.AttendeeStatus;
import model.Event;
import model.EventObserver;

public class NotificationService implements EventObserver {

    @Override
    public void onEventCreated(Event event) {
        System.out.println("[Notification] Event created: " + event.getTitle());
        notifyAttendees(event, "created");
    }

    @Override
    public void onEventUpdated(Event event) {
        System.out.println("[Notification] Event updated: " + event.getTitle());
        notifyAttendees(event, "updated");
    }

    @Override
    public void onEventDeleted(Event event) {
        System.out.println("[Notification] Event deleted: " + event.getTitle());
        notifyAttendees(event, "deleted");
    }

    private void notifyAttendees(Event event, String action) {
        for (Attendee a : event.getAttendees()) {
            if (a.getAttendeeStatus() != AttendeeStatus.DECLINED) {
                System.out.println("  -> Notifying user " + a.getUserId() + " that event was " + action);
            }
        }
    }
}

