package exercise1.business.inheritance;

import exercise1.domain.AgeGroup;
import exercise1.domain.Person;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

abstract class AbstractPersonReportGenerator {
    public void generateReport(String outputFile) throws IOException {
        Map<AgeGroup, List<Person>> personsByAgeGroup = new TreeMap<>(groupPeopleByAgeGroup());
        writeReport(personsByAgeGroup, outputFile);
    }

    public void writeReport(Map<AgeGroup, List<Person>> personsByAgeGroup, String outputFile) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            personsByAgeGroup.entrySet()
                    .stream()
                    .map(entry -> getAgeCategory(entry.getKey()) + ": " + getPersonNames(entry.getValue()))
                    .forEach(names -> writeLine(writer, names));
        }
    }

    private String getAgeCategory(AgeGroup ageGroup) {
        return ageGroup.getAgeCategory();
    }

    private String getPersonNames(List<Person> persons) {
        return persons.stream()
                .sorted(Comparator.comparing(Person::getFullName))
                .map(Person::getFullName)
                .collect(Collectors.joining(", "));
    }

    private Map<AgeGroup, List<Person>> groupPeopleByAgeGroup() {
        return readPersons()
                .stream()
                .collect(Collectors.groupingBy(Person::getAgeGroup));
    }

    private void writeLine(BufferedWriter writer, String line) {
        try {
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected abstract List<Person> readPersons();
}
