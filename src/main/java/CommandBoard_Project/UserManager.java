package CommandBoard_Project;


import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private Map<String, User> users = new HashMap<>();

    public void signup(String username, String password, String email) {
        if (users.containsKey(username)) {
            throw new InvalidUrlException("이미 존재하는 사용자입니다.");
        }
        users.put(username, new User(username, password, email));
        System.out.println("회원가입이 완료되었습니다.");
    }

    public User signin(String username, String password) {
        User user = users.get(username);
        if (user == null || !user.getPassword().equals(password)) {
            throw new UserNotFoundException("아이디나 비밀번호가 잘못되었습니다.");
        }
        System.out.println("로그인되었습니다.");
        return user;
    }

    public void viewUser(String username) {
        User user = users.get(username);
        if (user == null) {
            throw new UserNotFoundException(username + " 회원은 존재하지 않습니다.");
        }
        System.out.println(user);
    }
}
