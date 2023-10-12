package dev.cocoa.uspgymbooking.facility;

public enum FacilityStatus {
    AVAILABLE("Available"),
    UNAVAILABLE("Unavailable"),
    MAINTENANCE("Maintenance"),
    DELETED("Deleted");

    private final String displayName;

    FacilityStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

