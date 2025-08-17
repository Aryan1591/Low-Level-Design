package model;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Event {
    private final String id;
    private final String title;
    private final Instant start;
    private final Instant end;
    private final String organizerId;
    private final List<Attendee> attendees;
    private final List<Reminder> reminders;
    private final RecurrenceRule recurrence;
    private EventStatus status;

    private Event(EventBuilder builder) {
        this.id = builder.id == null ? UUID.randomUUID().toString() : builder.id;
        this.title = builder.title;
        this.start = builder.start;
        this.end = builder.end;
        this.organizerId = builder.organizerId;
        this.attendees = new CopyOnWriteArrayList<>(builder.attendees);
        this.reminders = Collections.unmodifiableList(new ArrayList<>(builder.reminders));
        this.recurrence = builder.recurrence;
        this.status = EventStatus.ACTIVE;
    }

    // Getters
    public String getId() { return id; }
    public String getTitle() { return title; }
    public Instant getStart() { return start; }
    public Instant getEnd() { return end; }
    public String getOrganizerId() { return organizerId; }
    public List<Attendee> getAttendees() { return attendees; }
    public List<Reminder> getReminders() { return reminders; }
    public RecurrenceRule getRecurrence() { return recurrence; }
    public EventStatus getStatus() { return status; }

    // Safe mutators
    public void addAttendee(Attendee a) { attendees.add(a); }
    public void updateStatus(EventStatus s) { this.status = s; }

    @Override
    public String toString() {
        return "Event{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", organizerId='" + organizerId + '\'' +
                ", attendees=" + attendees +
                ", reminders=" + reminders +
                ", recurrence=" + recurrence +
                ", status=" + status +
                '}';
    }

    // ===================== BUILDER =====================
    public static class EventBuilder {
        private String id;
        private String title;
        private Instant start;
        private Instant end;
        private String organizerId;
        private List<Attendee> attendees = new ArrayList<>();
        private List<Reminder> reminders = new ArrayList<>();
        private RecurrenceRule recurrence;

        public EventBuilder withId(String id) { this.id = id; return this; }
        public EventBuilder title(String t) { this.title = t; return this; }
        public EventBuilder start(Instant s) { this.start = s; return this; }
        public EventBuilder end(Instant e) { this.end = e; return this; }
        public EventBuilder organizer(String id) { this.organizerId = id; return this; }
        public EventBuilder addAttendee(Attendee a) { this.attendees.add(a); return this; }
        public EventBuilder addReminder(Reminder r) { this.reminders.add(r); return this; }
        public EventBuilder recurrence(RecurrenceRule r) { this.recurrence = r; return this; }

        public Event build() {
            if (title == null || title.isBlank())
                throw new IllegalArgumentException("Event must have a title");
            if (start == null || end == null || start.isAfter(end))
                throw new IllegalArgumentException("Invalid start/end time");
            if (organizerId == null)
                throw new IllegalArgumentException("Organizer required");
            return new Event(this);
        }
    }
}
