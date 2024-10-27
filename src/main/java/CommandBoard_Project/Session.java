package CommandBoard_Project;

public class Session {
    // 현재 로그인한 사용자 정보
    private User loggedInUser;

    public Session(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    // 현재 로그인한 사용자 정보를 반환하는 getter
    public User getLoggedInUser() {
        return loggedInUser;
    }

    public boolean isLoggedIn() {
        return loggedInUser != null;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    @Override
    public String toString() {
        return "Session{loggedIn=" + isLoggedIn() +
                (isLoggedIn() ? ", user=" + loggedInUser.getUsername() : "") + "}";
    }
}