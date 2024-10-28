package CommandBoard_Project;

// 로그인 상태와 세션 시작 시간을 추적

public class Session {
    // 현재 로그인한 사용자 정보
    private User loggedInUser;

    public Session(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

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