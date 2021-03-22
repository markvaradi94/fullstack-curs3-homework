package exercise1.business.composition;

import exercise1.domain.Person;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class FilePersonReader implements PersonReader {
    private final String inputFile;

    public FilePersonReader(String inputFile) {
        this.inputFile = inputFile;
    }

    public List<Person> readPersons() {
        try {
            return readPersonsFromFile();
        } catch (IOException e) {
            System.err.println("Could not read from file " + inputFile);
        }
        return List.of();
    }

    private List<Person> readPersonsFromFile() throws IOException {
        return Files.lines(Path.of(inputFile))
                .map(this::readPerson)
                .collect(toList());
    }

    private Person readPerson(String line) {
        String[] personInfo = line.split(",");
        return new Person(
                personInfo[0],
                personInfo[1],
                Integer.parseInt(personInfo[2])
        );
    }
}
