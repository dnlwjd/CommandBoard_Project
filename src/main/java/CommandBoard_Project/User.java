package CommandBoard_Project;

import java.time.LocalDateTime;

public class User {
    private int userId;
    private String username;
    private String password;
    private String email;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public User(int userId, String username, String password, String email) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.createdDate = LocalDateTime.now();
        this.modifiedDate = LocalDateTime.now();
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        this.modifiedDate = LocalDateTime.now();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        this.modifiedDate = LocalDateTime.now();
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    @Override
    public String toString() {
        return userId + "번 회원\n" +
                "계정: " + username + "\n" +
                "이메일: " + email + "\n" +
                "가입일: " + createdDate;
    }
}