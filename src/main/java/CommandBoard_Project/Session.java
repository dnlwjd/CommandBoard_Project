package CommandBoard_Project;

import java.time.LocalDateTime;

public class Session {
    private User loggedInUser;
    private LocalDateTime sessionStartTime;

    public Session(User loggedInUser) {
        this.loggedInUser = loggedInUser;
        this.sessionStartTime = LocalDateTime.now();
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public boolean isLoggedIn() {
        return loggedInUser != null;
    }

    public LocalDateTime getSessionStartTime() {
        return sessionStartTime;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
        if (loggedInUser != null) {
            this.sessionStartTime = LocalDateTime.now();
        }
    }
}