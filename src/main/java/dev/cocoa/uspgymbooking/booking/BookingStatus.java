package dev.cocoa.uspgymbooking.booking;

public enum BookingStatus {
    PENDING("Pending"),
    PAID("Paid"),
    COMPLETE("Complete"),
    DELETED("DELETED");

    private final String displayName;

    BookingStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

