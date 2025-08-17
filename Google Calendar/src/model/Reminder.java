package model;

public class Reminder {
    private final ReminderType type;
    private final long minutesBefore; // minutes offset before event start

    public Reminder(ReminderType type, long minutesBefore) {
        if (type == null) throw new IllegalArgumentException("Reminder type cannot be null");
        if (minutesBefore < 0) throw new IllegalArgumentException("minutesBefore must be >= 0");
        this.type = type;
        this.minutesBefore = minutesBefore;
    }

    public ReminderType getType() {
        return type;
    }

    public long getMinutesBefore() {
        return minutesBefore;
    }
}
