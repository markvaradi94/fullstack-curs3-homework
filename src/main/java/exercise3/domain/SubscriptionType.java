package exercise3.domain;

public enum SubscriptionType {
    BRONZE(5, false),
    SILVER(20, false),
    GOLD(30, true);

    private final double gymTime;
    private final boolean autoRenewActive;

    SubscriptionType(double gymTime, boolean hasAutoRenew) {
        this.gymTime = gymTime;
        this.autoRenewActive = hasAutoRenew;
    }

    public double getGymTime() {
        return gymTime;
    }

    public boolean hasAutoRenew() {
        return autoRenewActive;
    }
}
