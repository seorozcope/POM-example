package data;

public class Employee {
    private final String firstName, lastName;

    public Employee(EmployeeBuilder employeeBuilder) {
        this.firstName = employeeBuilder.firstName;
        this.lastName = employeeBuilder.lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return firstName.concat(" ").concat(lastName);
    }
}
