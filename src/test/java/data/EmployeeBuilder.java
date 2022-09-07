package data;

public class EmployeeBuilder {
    protected String id;
    protected String firstName;
    protected String lastName;

    public EmployeeBuilder() {
        firstName = "";
        lastName = "";
        id = "";
    }

    public EmployeeBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public EmployeeBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public EmployeeBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public Employee build() {
        return new Employee(this);
    }
}