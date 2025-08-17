package model;

import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class DailyRecurrence implements RecurrenceRule {

    private final Instant start;
    private final Instant end;

    public DailyRecurrence(Instant start, Instant end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public boolean occursAt(Instant timestamp) {
        if (timestamp.isBefore(start) || timestamp.isAfter(end)) return false;
        long daysBetween = ChronoUnit.DAYS.between(start.atZone(ZoneId.systemDefault()).toLocalDate(),
                timestamp.atZone(ZoneId.systemDefault()).toLocalDate());
        return daysBetween >= 0;
    }

    @Override
    public String toString() {
        return "DailyRecurrence{" + "start=" + start + ", end=" + end + '}';
    }
}