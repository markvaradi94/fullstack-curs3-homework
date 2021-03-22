package exercise3.domain;

import java.time.LocalDate;
import java.util.Objects;

public class GymMember {
    private final String name;
    private final LocalDate birthdate;
    private final int age;

    public GymMember(String name, LocalDate birthdate) {
        this.name = name;
        this.birthdate = birthdate;
        this.age = calculateAge();
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public int getAge() {
        return age;
    }

    public void printMemberInfo() {
        System.out.println("Name: " + name + "\nBirthdate: " + birthdate + "\nAge: " + age);
    }

    private int calculateAge() {
        return LocalDate.now().getYear() - birthdate.getYear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GymMember gymMember = (GymMember) o;
        return Objects.equals(name, gymMember.name) && Objects.equals(birthdate, gymMember.birthdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthdate);
    }

    @Override
    public String toString() {
        return "GymMember{" +
                "name='" + name + '\'' +
                ", birthdate=" + birthdate +
                '}';
    }
}
