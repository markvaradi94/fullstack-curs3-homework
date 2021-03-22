package exercise3.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.unmodifiableList;
import static java.util.Optional.ofNullable;

public class Gym {
    private final List<Subscription> subscriptions;

    public Gym(List<Subscription> members) {
        this.subscriptions = ofNullable(members)
                .map(ArrayList::new)
                .orElseGet(ArrayList::new);
    }

    public List<Subscription> getSubscriptions() {
        return unmodifiableList(subscriptions);
    }

    public void showMemberInfo(String memberName) {
        Subscription sub = findSubscriptionByMemberName(memberName);
        sub.showMemberInfo();
    }

    public double timeSpentByMember(String memberName) {
        Subscription subscription = findSubscriptionByMemberName(memberName);
        return subscription.getRemainingTime();
    }

    public void registerGymTimeForMember(String memberName, double duration) {
        Subscription sub = findSubscriptionByMemberName(memberName);
        sub.useGymTime(duration);
    }

    public void addTimeToMember(String memberName, double time) {
        Subscription sub = findSubscriptionByMemberName(memberName);
        sub.purchaseAdditionalTime(time);
    }

    public double totalRemainingTimeForAllMembers() {
        return subscriptions.stream()
                .mapToDouble(Subscription::getRemainingTime)
                .sum();
    }

    public int youngestMemberAge() {
        return subscriptions.stream()
                .mapToInt(this::getMemberAgeFromSubscription)
                .min()
                .orElse(-1);
    }

    public int oldestMemberAge() {
        return subscriptions.stream()
                .mapToInt(this::getMemberAgeFromSubscription)
                .max()
                .orElse(-1);
    }

    public double averageAgeOfMembers() {
        return subscriptions.stream()
                .mapToInt(this::getMemberAgeFromSubscription)
                .average()
                .orElse(-1);
    }

    private Subscription findSubscriptionByMemberName(String name) {
        return subscriptions.stream()
                .filter(subscription -> getMemberNameFromSubscription(subscription).equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No subscriptions for member with the name " + name + " found"));
    }

    private List<Subscription> searchForSubscriptionByName(String name) {
        return subscriptions.stream()
                .filter(sub -> getMemberNameFromSubscription(sub).toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toUnmodifiableList());
    }

    private int getMemberAgeFromSubscription(Subscription subscription) {
        return subscription != null ? subscription.getMember().getAge() : -1;
    }

    private String getMemberNameFromSubscription(Subscription subscription) {
        return subscription != null ? subscription.getMember().getName() : "";
    }
}
