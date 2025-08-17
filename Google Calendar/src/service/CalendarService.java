package service;

import model.Attendee;
import model.AttendeeStatus;
import model.Event;
import model.EventObserver;
import repository.EventRepository;
import repository.InMemoryEventRepository;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

public class CalendarService {
    private static final CalendarService INSTANCE = new CalendarService();

    private final EventRepository eventRepo;
    private final List<EventObserver> observers = new CopyOnWriteArrayList<>();

    private CalendarService() {
        this.eventRepo = new InMemoryEventRepository(); // can swap with DB repo later
    }

    public static CalendarService getInstance() {
        return INSTANCE;
    }

    // Observer management
    public void registerObserver(EventObserver observer) {
        observers.add(observer);
    }

    public void unregisterObserver(EventObserver observer) {
        observers.remove(observer);
    }

    // Create event
    public Event createEvent(Event.EventBuilder builder) {
        Instant start = builder.build().getStart();
        Instant end = builder.build().getEnd();

        // Check organizer conflicts
        if (hasConflict(builder.build().getOrganizerId(), start, end)) {
            throw new IllegalArgumentException("Organizer has a conflicting event");
        }

        // Check attendees conflicts
        for (Attendee a : builder.build().getAttendees()) {
            if (hasConflict(a.getUserId(), start, end)) {
                throw new IllegalArgumentException("Attendee " + a.getUserId() + " has a conflicting event");
            }
        }

        Event event = builder.build();
        eventRepo.save(event);
        observers.forEach(o -> o.onEventCreated(event));
        return event;
    }

    // Update event (with synchronized update)
    public Event updateEvent(String id, Consumer<Event> updater) {
        Event event = eventRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Event not found"));
        synchronized (event) { // thread-safe update
            updater.accept(event);
        }
        eventRepo.save(event);
        observers.forEach(o -> o.onEventUpdated(event));
        return event;
    }

    // Delete event
    public void deleteEvent(String id) {
        Event event = eventRepo.findById(id).orElse(null);
        if (event != null) {
            eventRepo.delete(id);
            observers.forEach(o -> o.onEventDeleted(event));
        }
    }

    // Get events for a user
    public List<Event> getEventsForUser(String userId, Instant from, Instant to) {
        return eventRepo.findByUser(userId, from, to);
    }

    public Event inviteUserToEvent(String eventId, String userId) {
        return updateEvent(eventId, event -> {
            event.addAttendee(new Attendee(userId, AttendeeStatus.INVITED));
        });
    }

    private boolean hasConflict(String userId, Instant start, Instant end) {
        return eventRepo.findByUser(userId, start, end).stream()
                .anyMatch(e -> !(e.getEnd().isBefore(start) || e.getStart().isAfter(end)));
    }
}

