package app;

import exercise1.business.composition.FilePersonReader;
import exercise1.business.composition.PersonReportGenerator;
import exercise2.LogicalSwitch;
import exercise2.Person;
import exercise3.business.GymReportGenerator;
import exercise3.domain.Gym;
import exercise3.domain.GymMember;
import exercise3.domain.Subscription;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static exercise3.domain.SubscriptionType.*;

public class App {
    public static final String PEOPLE_TXT = "src/main/resources/people.txt";

    public static void main(String[] args) throws IOException {
        FilePersonReader personReader = new FilePersonReader(PEOPLE_TXT);
        String outputFile = "src/main/resources/remaining-time-report-" + LocalDate.now() + ".txt";
        new PersonReportGenerator(personReader).generateReport();

        GymMember gyuszi = new GymMember("Kiss Gyuszi", LocalDate.now().minusYears(35));
        GymMember sanyi = new GymMember("Nagy Sandor", LocalDate.now().minusYears(30));
        GymMember ioan = new GymMember("Pop Ioan", LocalDate.now().minusYears(22));
        GymMember gicu = new GymMember("Ugyes Gheorghe", LocalDate.now().minusYears(19));
        GymMember andrei = new GymMember("Kiss Andrei", LocalDate.now().minusYears(38));
        GymMember zsolti = new GymMember("Kocsis Zsolt", LocalDate.now().minusYears(25));
        GymMember vladimir = new GymMember("Boris Vladimir", LocalDate.now().minusYears(21));

        Gym gym = new Gym(List.of(
                new Subscription(GOLD, gyuszi),
                new Subscription(SILVER, sanyi),
                new Subscription(SILVER, ioan),
                new Subscription(BRONZE, gicu),
                new Subscription(GOLD, andrei),
                new Subscription(BRONZE, zsolti),
                new Subscription(BRONZE, vladimir)
        ));

        GymReportGenerator reportGenerator = new GymReportGenerator(outputFile, gym);
        reportGenerator.generateReport();

        System.out.println();

        Person person = new exercise2.Person("Ana", 22, "Oradea");
        Person person2 = new exercise2.Person("Ioane", 14, "Deva");
        LogicalSwitch logicalSwitch = new LogicalSwitch();


        System.out.println(logicalSwitch.dispatch(person));
        System.out.println(logicalSwitch.dispatch(person2));
    }
}
