package exercise1.business.composition;

import exercise1.domain.AgeGroup;
import exercise1.domain.Person;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class PersonReportGenerator {
    private final PersonReportWriter reportWriter = new PersonReportWriter();
    private final String outputFile;
    private final PersonReader personReader;

    public PersonReportGenerator(PersonReader personReader) {
        this.personReader = personReader;
        this.outputFile = generateReportFilePath();
    }

    public void generateReport() throws IOException {
        reportWriter.writeReport(new TreeMap<>(groupPeopleByAgeGroup()), outputFile);
    }

    private String generateReportFilePath() {
        return "src/main/resources/remaining-time-report-" + LocalDate.now() + ".txt";
    }

    private Map<AgeGroup, List<Person>> groupPeopleByAgeGroup() {
        return personReader.readPersons()
                .stream()
                .collect(Collectors.groupingBy(Person::getAgeGroup));
    }

    public String getOutputFile() {
        return outputFile;
    }
}
