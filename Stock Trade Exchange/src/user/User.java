package user;

import java.util.UUID;

public class User {
    private String userId;
    private String username;
    private String email;

    public User(String username, String email) {
        this.userId = UUID.randomUUID().toString();
        this.username = username;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
