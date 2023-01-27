package Problem6;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class EmployeeTest {
    static List<Employee> employees;

    public static void setup() {
        employees = new ArrayList<>();
        employees.add(new Employee(123, "Jack", "Johnson", LocalDate.of(1988,
                Month.APRIL, 12)));
        employees.add(new Employee(345, "Cindy", "Bower", LocalDate.of(2011,
                Month.DECEMBER, 15)));
        employees.add(new Employee(567, "Perry", "Node", LocalDate.of(2005,
                Month.JUNE, 07)));
        employees.add(new Employee(467, "Pam", "Krauss", LocalDate.of(2005,
                Month.JUNE, 07)));
        employees.add(new Employee(435, "Fred", "Shak", LocalDate.of(1988,
                Month.APRIL, 17)));
        employees.add(new Employee(678, "Ann", "Lee", LocalDate.of(2007,
                Month.APRIL, 12)));
        employees.add(new Employee(658, "Ann", "Bee", LocalDate.of(2004,
                Month.MARCH, 11)));
    }

    static void employeesByNumberAsc()
    {
        employees.stream()
                .sorted((e1, e2) -> e1.getEmployeeNumber().compareTo(e2.getEmployeeNumber()))
                .forEach(System.out::println);
    }

    static void employeesByDateDesc()
    {
        employees.stream()
                .sorted((e1, e2) -> e2.getHireDate().compareTo(e1.getHireDate()))
                .forEach(System.out::println);
    }

    static Optional<Employee> longestTenuredEmployee()
    {
        Optional<Employee> emp = employees.stream()
                .min(Comparator.comparing(Employee::getHireDate));
        System.out.println(emp);
        return emp;
    }

    static void employeesByFirstAndLastName()
    {
        employees.stream()
                .sorted(Comparator.comparing(Employee::getEmployeeFirstName).thenComparing(Employee::getEmployeeLastName, Comparator.reverseOrder()))
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        EmployeeTest.setup();
        System.out.println("\nemployeesByNumberAsc():");
        employeesByNumberAsc();
        System.out.println("\nemployeesByDateDesc():");
        employeesByDateDesc();
        System.out.println("\nlongestTenuredEmployee():");
        longestTenuredEmployee();
        System.out.println("\nemployeesByFirstAndLastName():");
        employeesByFirstAndLastName();

    }
}
