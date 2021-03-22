package exercise3.domain;

import java.util.Objects;

public class Subscription {
    private final SubscriptionType type;
    private final double timeSpent;
    private double remainingTime;
    private final GymMember member;

    public Subscription(SubscriptionType type, GymMember member) {
        this.type = type;
        this.member = member;
        this.timeSpent = 0;
        this.remainingTime = type.getGymTime();
    }

    public SubscriptionType getType() {
        return type;
    }

    public double getTimeSpent() {
        return timeSpent;
    }

    public GymMember getMember() {
        return member;
    }

    protected void showMemberInfo() {
        member.printMemberInfo();
    }

    protected void useGymTime(double duration) {
        if (this.remainingTime - duration <= 0 && type.hasAutoRenew()) {
            autoRenewSubscription();
        }
        decreaseRemainingTime(duration);
    }

    protected void decreaseRemainingTime(double duration) {
        this.remainingTime = this.remainingTime - duration < 0 ? this.remainingTime - duration : 0;
    }

    protected void purchaseAdditionalTime(double time) {
        this.remainingTime += time;
    }

    protected void autoRenewSubscription() {
        this.remainingTime += this.type.getGymTime();
    }

    private boolean membershipRanOut() {
        return this.remainingTime > 0;
    }

    public double getRemainingTime() {
        return remainingTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subscription that = (Subscription) o;
        return Double.compare(that.timeSpent, timeSpent) == 0 && type == that.type && Objects.equals(member, that.member);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, timeSpent, member);
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "type=" + type +
                ", remainingTime=" + timeSpent +
                ", member=" + member +
                '}';
    }
}
