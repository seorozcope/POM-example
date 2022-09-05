package data;

public class UserBuilder {
    protected String username;
    protected String password;

    public UserBuilder (){
        username ="";
        password="";
    }

    public UserBuilder setUsername(String username){
        this.username=username;
        return this;
    }

    public UserBuilder setPassword(String password){
        this.password=password;
        return this;
    }

    public User build() {
        return new User(this);
    }

    public static User wrongCredentials() {
        return new UserBuilder().setUsername("not_a_user").setPassword("123456").build();
    }

    public static User adminUser() {
        return new UserBuilder().setUsername("Admin").setPassword("admin123").build();
    }
}
