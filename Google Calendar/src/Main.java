import model.*;
import service.CalendarService;
import service.NotificationService;

import java.time.Instant;

public class Main {
    public static void main(String[] args) {
        // 1️⃣ Users
        User alice = new User("Alice", "alice@example.com");
        User bob = new User("Bob", "bob@example.com");

        // 2️⃣ Services
        CalendarService service = CalendarService.getInstance();
        NotificationService notificationService = new NotificationService();

        // Register observers
        service.registerObserver(notificationService);

        // 3️⃣ Create a simple event
        Event interview = service.createEvent(
                new Event.EventBuilder()
                        .title("Interview with Bob")
                        .start(Instant.parse("2025-08-20T10:00:00Z"))
                        .end(Instant.parse("2025-08-20T11:00:00Z"))
                        .organizer(alice.getId())
                        .addAttendee(new Attendee(bob.getId(), AttendeeStatus.INVITED))
        );

        System.out.println("Created Event: " + interview);

        // 4️⃣ Create a recurring weekly event
        Event teamMeeting = service.createEvent(
                new Event.EventBuilder()
                        .title("Weekly Team Meeting")
                        .start(Instant.parse("2025-08-18T09:00:00Z"))
                        .end(Instant.parse("2025-08-18T10:00:00Z"))
                        .organizer(alice.getId())
                        .recurrence(new DailyRecurrence(
                                Instant.parse("2025-08-18T09:00:00Z"),
                                Instant.parse("2025-12-31T10:00:00Z")
                        ))
        );

        System.out.println("Created Recurring Event: " + teamMeeting);

        // 5️⃣ Update event example
        service.updateEvent(interview.getId(), e -> e.addAttendee(new Attendee(alice.getId(), AttendeeStatus.ACCEPTED)));

        // 6️⃣ Conflict demo (will throw exception)
        try {
            service.createEvent(
                    new Event.EventBuilder()
                            .title("Overlapping Meeting")
                            .start(Instant.parse("2025-08-20T10:30:00Z"))
                            .end(Instant.parse("2025-08-20T11:30:00Z"))
                            .organizer(alice.getId())
            );
        } catch (IllegalArgumentException ex) {
            System.out.println("[Conflict Detected] " + ex.getMessage());
        }

        // 7️⃣ Fetch events for Alice in a range
        System.out.println("Alice's Events:");
        service.getEventsForUser(alice.getId(),
                        Instant.parse("2025-08-19T00:00:00Z"),
                        Instant.parse("2025-08-21T00:00:00Z"))
                .forEach(System.out::println);
    }

}