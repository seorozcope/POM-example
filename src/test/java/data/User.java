package data;

public class User {
    private final String username, password;

    public User(UserBuilder userBuilder) {
        this.username = userBuilder.username;
        this.password = userBuilder.password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
