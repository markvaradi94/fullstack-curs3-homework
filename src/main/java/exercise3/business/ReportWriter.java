package exercise3.business;

import exercise1.domain.Person;
import exercise3.domain.GymMember;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReportWriter {

    public void writeReport(Map<GymReportMemberCategory, List<GymMember>> gymMembersByCategory, String outputFile)
            throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            gymMembersByCategory.entrySet()
                    .stream()
                    .map(entry -> entry.getKey().name() + ": " + getMemberNames(entry.getValue()))
                    .forEach(names -> writeLine(writer, names));
        }
    }

    private String getMemberNames(List<GymMember> members) {
        return members.stream()
                .sorted(Comparator.comparing(GymMember::getName))
                .map(GymMember::getName)
                .collect(Collectors.joining(", "));
    }

    private void writeLine(BufferedWriter writer, String line) {
        try {
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
