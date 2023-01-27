package Problem6;

import java.time.LocalDate;

public class Employee {
    private Integer employeeNumber;
    private String employeeFirstName;
    private String employeeLastName;
    private LocalDate hireDate;

    public Employee(Integer employeeNumber, String employeeFirstName, String employeeLastName, LocalDate hireDate) {
        setEmployeeNumber(employeeNumber);
        setEmployeeFirstName(employeeFirstName);
        setEmployeeLastName(employeeLastName);
        setHireDate(hireDate);
    }

    public Integer getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(Integer employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public void setEmployeeFirstName(String employeeFirstName) {
        this.employeeFirstName = employeeFirstName;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "No: " + employeeNumber +
                ", " + employeeFirstName + " " + employeeLastName +
                ", hired on: " + hireDate +
                '}';
    }
}
