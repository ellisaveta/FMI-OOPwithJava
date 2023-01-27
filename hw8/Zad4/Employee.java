package Zad4;

import org.w3c.dom.ls.LSOutput;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class Employee {
    public static enum Gender {
        MALE, FEMALE
    }

    private String name;
    private Gender gender;
    private LocalDate dob;
    private double income;
    private UUID employeeID;

    private Employee(String name, Gender gender, LocalDate dob, double income) {
        this.name = name;
        this.gender = gender;
        this.dob = dob;
        this.income = income;
        employeeID = UUID.randomUUID();

    }

    public Employee() {
    }

    public double getIncome() {
        return income;
    }

    public static List<Employee> persons() {
        Employee p1 = new Employee( "Jake", Gender.MALE,
                LocalDate.of(1971, Month.JANUARY, 1), 2343.0);
        Employee p2 = new Employee("Jack", Gender.MALE,
                LocalDate.of(1972, Month.JULY, 21), 7100.0);
        Employee p3 = new Employee( "Jane", Gender.FEMALE,
                LocalDate.of(1973, Month.MAY, 29), 5455.0);
        Employee p4 = new Employee("Jode", Gender.MALE,
                LocalDate.of(1974, Month.OCTOBER, 16), 1800.0);
        Employee p5 = new Employee( "Jeny", Gender.FEMALE,
                LocalDate.of(1975, Month.DECEMBER, 13), 1234.0);
        Employee p6 = new Employee( "Jason", Gender.MALE,
                LocalDate.of(1976, Month.JUNE, 9), 3211.0);
        List<Employee> persons = Arrays.asList(p1, p2, p3, p4, p5, p6);
        return persons;
    }

    public static void statistics() {
        List<Employee> employees = persons();
        double sum = employees.stream()
                .flatMapToDouble(employee -> DoubleStream.of(employee.income))
                .sum();
        System.out.printf("Sum of all incomes is: %.2f%n", sum);
    }

    public void personsStatsByGenderCount()
    {
        List<Employee> employees = persons();
        Map<String, Long> personsByGender = employees.stream()
                .collect(Collectors.groupingBy(e -> e.gender.name(), Collectors.counting()));
        System.out.println("Count of employees by gender:");
        System.out.println(personsByGender);
    }

    public void personsStatsByGenderList() {
        List<Employee> employees = persons();
        Map<String, List<Employee>> personsByGender = employees.stream()
                .collect(Collectors.groupingBy(e -> e.gender.name()));
        System.out.println("Employees by gender:");
        personsByGender.forEach((x, y) -> System.out.printf("%s: %s", x, y));
    }

    @Override
    public String toString() {
        return String.format("Employee N: %s Name: %s %s %s has income: %.2f",
                employeeID.toString(), name, gender.name(), dob, income);
    }
}
