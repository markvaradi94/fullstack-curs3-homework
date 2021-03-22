package exercise1.business.inheritance;

import exercise1.domain.Person;

import java.util.List;

public class InMemoryPersonReportGenerator extends AbstractPersonReportGenerator {
    @Override
    protected List<Person> readPersons() {
        return List.of(
                new Person("Csabi", "Piros", 20),
                new Person("Tibi", "Zold", 14),
                new Person("Jozsi", "Feher", 35),
                new Person("Attila", "Fekete", 22),
                new Person("Viktor", "Pasztor", 71)
        );
    }
}
