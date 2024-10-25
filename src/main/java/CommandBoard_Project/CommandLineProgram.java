package CommandBoard_Project;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CommandLineProgram {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            PostManager postManager = new PostManager();
            BoardManager boardManager = new BoardManager();
            UserManager userManager = new UserManager();
            boolean isRunning = true;
            Session session = new Session(null); // 초기 세션은 로그인된 사용자가 없음

            while (isRunning) {
                System.out.print(session.isLoggedIn() ? session.getLoggedInUser().getUsername() + " > " : "손님 > ");
                String input = scanner.nextLine();

                try {
                    // Request 객체 생성
                    Request request = new Request(input, session);
                    if (input.equals("종료") || input.equals("exit")) {
                        isRunning = false; // 프로그램 종료
                        System.out.println("프로그램을 종료합니다.");
                    } else {
                        session = processRequest(request, postManager, boardManager, userManager);
                        isRunning = session != null;
                    }
                } catch (InvalidUrlException | PostNotFoundException | BoardNotFoundException | UserNotFoundException e) {
                    System.out.println("오류: " + e.getMessage());
                }
            }
        }
    }

    private static Session processRequest(Request request, PostManager postManager, BoardManager boardManager, UserManager userManager) {
        String url = request.getUrl();
        Session session = request.getSession();
        String[] parts = url.split("/");
        if (parts.length < 2) {
            throw new InvalidUrlException("잘못된 URL 형식입니다.");
        }

        String command = parts.length > 2 ? parts[1] + "/" + parts[2] : parts[1];

        Map<String, String> params = new HashMap<>();
        for (int i = 3; i < parts.length; i++) {
            String[] keyValue = parts[i].split("=");
            if (keyValue.length == 2) {
                params.put(keyValue[0], keyValue[1]);
            }
        }

        switch (command) {
            case "accounts/signup" -> {
                userManager.signup(params.get("username"), params.get("password"), params.get("email"));
                return session;
            }
            case "accounts/signin" -> {
                User loggedInUser = userManager.signin(params.get("username"), params.get("password"));
                session.setLoggedInUser(loggedInUser);
                return session;
            }
            case "accounts/signout" -> {
                System.out.println("로그아웃되었습니다.");
                session.setLoggedInUser(null);
                return session;
            }
            case "posts/add" -> {
                postManager.addPost(session.getLoggedInUser(), Integer.parseInt(params.get("boardId")), params.get("title"), params.get("content"));
                return session;
            }
            case "posts/view" -> {
                postManager.viewPost(Integer.parseInt(params.get("postId")));
                return session;
            }
            case "boards/add" -> {
                boardManager.addBoard(session.getLoggedInUser(), params.get("name"));
                return session;
            }
            case "boards/view" -> {
                boardManager.viewBoard(Integer.parseInt(params.get("boardId")));
                return session;
            }
            default -> throw new InvalidUrlException("지원하지 않는 명령어입니다: " + command);
        }
    }
}
