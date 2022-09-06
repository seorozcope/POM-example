package data;

public class EmployeeBuilder {
    protected String firstName;
    protected String lastName;

    public EmployeeBuilder() {
        firstName = "";
        lastName = "";
    }

    public EmployeeBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public EmployeeBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Employee build() {
        return new Employee(this);
    }
}