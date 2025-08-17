package repository;
import model.Event;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class InMemoryEventRepository implements EventRepository {
    private final ConcurrentMap<String, Event> store = new ConcurrentHashMap<>();

    @Override
    public Event save(Event event) {
        store.put(event.getId(), event);
        return event;
    }


    @Override
    public Optional<Event> findById(String eventId) {
        return Optional.ofNullable(store.get(eventId));
    }

    @Override
    public List<Event> findByUser(String userId, Instant from, Instant to) {
        return store.values().stream()
                .filter(ev -> ev.getOrganizerId().equals(userId)
                        || ev.getAttendees().stream().anyMatch(a -> a.getUserId().equals(userId)))
                .filter(ev -> !(ev.getEnd().isBefore(from) || ev.getStart().isAfter(to)))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String eventId) {
        store.remove(eventId);
    }
}

