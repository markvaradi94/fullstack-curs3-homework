package exercise3.business;

import exercise3.domain.Gym;
import exercise3.domain.GymMember;
import exercise3.domain.Subscription;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class GymReportGenerator {
    private final String outputFile;
    private final ReportWriter reportWriter = new ReportWriter();
    private final Gym gym;

    public GymReportGenerator(String outputFile, Gym gym) {
        this.outputFile = outputFile;
        this.gym = gym;
    }

    public void generateReport() throws IOException {
        reportWriter.writeReport(new TreeMap<>(groupMembersByCategory()), outputFile);
    }

    private Map<GymReportMemberCategory, List<GymMember>> groupMembersByCategory() {
        return gym.getSubscriptions().stream()
                .collect(groupingBy(this::getReportCategory,
                        mapping(Subscription::getMember, toList())));
    }

    private GymReportMemberCategory getReportCategory(Subscription subscription) {
        return GymReportMemberCategory.determineCategory(subscription.getRemainingTime());
    }
}
