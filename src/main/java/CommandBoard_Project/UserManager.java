package CommandBoard_Project;

import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private Map<String, User> users = new HashMap<>();
    private int nextUserId = 1;

    public void signup(String username, String password, String email) {
        if (username == null || password == null || email == null) {
            throw new InvalidUrlException("모든 필드를 입력해주세요.");
        }
        if (users.containsKey(username)) {
            throw new InvalidUrlException("이미 존재하는 사용자입니다.");
        }
        users.put(username, new User(nextUserId++, username, password, email));
        System.out.println("회원가입이 완료되었습니다.");
    }

    public User signin(String username, String password, Session session) {
        if (session.isLoggedIn()) {
            throw new InvalidUrlException("이미 로그인되어 있습니다. 먼저 로그아웃 해주세요.");
        }

        User user = users.get(username);
        if (user == null || !user.getPassword().equals(password)) {
            throw new UserNotFoundException("아이디나 비밀번호가 잘못되었습니다.");
        }
        System.out.println("로그인되었습니다.");
        return user;
    }

    public void signout(Session session) {
        if (!session.isLoggedIn()) {
            throw new InvalidUrlException("로그인 상태가 아닙니다.");
        }
        session.setLoggedInUser(null);
        System.out.println("로그아웃되었습니다.");
    }

    public void editUser(int userId, String password, String email) {
        User user = findUserById(userId);
        if (user == null) {
            throw new UserNotFoundException(userId + "번 회원은 존재하지 않습니다.");
        }
        if (password != null) {
            user.setPassword(password);
        }
        if (email != null) {
            user.setEmail(email);
        }
        System.out.println("회원 정보가 수정되었습니다.");
    }

    public void viewUser(int userId) {
        User user = findUserById(userId);
        if (user == null) {
            throw new UserNotFoundException(userId + "번 회원은 존재하지 않습니다.");
        }
        System.out.println(user);
    }

    public void removeUser(int userId, Session session) {
        User user = findUserById(userId);
        if (user == null) {
            throw new UserNotFoundException(userId + "번 회원은 존재하지 않습니다.");
        }

        // 로그인된 사용자와 동일한 경우 로그아웃 처리
        if (session.isLoggedIn() && session.getLoggedInUser().getUsername().equals(user.getUsername())) {
            session.setLoggedInUser(null);
        }

        users.remove(user.getUsername());
        System.out.println("회원 탈퇴가 완료되었습니다.");
    }

    private User findUserById(int userId) {
        for (User user : users.values()) {
            if (user.getUserId() == userId) {
                return user;
            }
        }
        return null;
    }
}