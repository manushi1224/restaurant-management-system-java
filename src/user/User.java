package user;

public class User {
    private String name;
    private String username;
    private String email;

    public User(String name, String username, String email) {
        this.name = name;
        this.username = username;
        this.email = email;
    }

    public String getName() {
        return name;
    }
}
