package model;

import java.time.Instant;

public interface RecurrenceRule {
    boolean occursAt(Instant timestamp);
}