package model;

public class Attendee {

    private String userId;
    private AttendeeStatus attendeeStatus;

    public Attendee(String userId, AttendeeStatus attendeeStatus) {
        this.userId = userId;
        this.attendeeStatus = attendeeStatus == null ? AttendeeStatus.NONE : attendeeStatus;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public AttendeeStatus getAttendeeStatus() {
        return attendeeStatus;
    }

    public void setAttendeeStatus(AttendeeStatus attendeeStatus) {
        this.attendeeStatus = attendeeStatus;
    }
}
