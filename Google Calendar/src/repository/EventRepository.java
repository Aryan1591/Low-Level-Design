package repository;

import model.Event;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface EventRepository {
    Event save(Event event);
    Optional<Event> findById(String eventId);
    List<Event> findByUser(String userId, Instant from, Instant to);
    void delete(String eventId);
}
