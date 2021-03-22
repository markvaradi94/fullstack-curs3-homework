package exercise1.business.composition;

import exercise1.domain.Person;

import java.util.List;

public class InMemoryPersonReader implements PersonReader {
    @Override
    public List<Person> readPersons() {
        return List.of(
                new Person("Ioan", "Ionescu", 25),
                new Person("Ion", "Ionsusianu", 41),
                new Person("Ionut", "Ionovovici", 66),
                new Person("Ionel", "Ionceanu", 45),
                new Person("Cristi", "de la Holod", 7)
        );
    }
}
