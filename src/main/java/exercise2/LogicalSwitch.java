package exercise2;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LogicalSwitch {
    private final Map<Predicate<Person>, Function<Person, String>> logicalSwitch = Map.of(
            person -> nameStartsWith(person, "A"), this::messageForStartsWith,
            person -> person.getAge() > 10, this::goVote,
            person -> person.getAddress().equalsIgnoreCase("Oradea"), this::goToUnirii
    );

    public String dispatch(Person person) {
        return logicalSwitch.entrySet().stream()
                .filter(entry -> entry.getKey().test(person))
                .map(entry -> entry.getValue().apply(person))
                .collect(Collectors.joining(", "));
    }

    private String goVote(Person person) {
        return "Voted for Vadim.";
    }

    private String goToUnirii(Person person) {
        return "I'm going to Unirii";
    }

    private String messageForStartsWith(Person person) {
        return person.getName() + " starts with A";
    }

    private boolean nameStartsWith(Person person, String letter) {
        return person.getName().startsWith(letter);
    }
}
