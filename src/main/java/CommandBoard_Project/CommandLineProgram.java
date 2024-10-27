package CommandBoard_Project;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CommandLineProgram {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            BoardManager boardManager = new BoardManager();
            PostManager postManager = new PostManager(boardManager);
            UserManager userManager = new UserManager();
            boolean isRunning = true;
            Session session = new Session(null);

            while (isRunning) {
                System.out.print(session.isLoggedIn() ? session.getLoggedInUser().getUsername() + " > " : "손님 > ");
                String input = scanner.nextLine();

                try {
                    Request request = new Request(input, session);

                    if (input.equals("종료") || input.equals("exit")) {
                        System.out.println("프로그램을 종료합니다.");
                        isRunning = false;
                        continue;
                    }

                    processRequest(request, postManager, boardManager, userManager);
                } catch (InvalidUrlException | PostNotFoundException |
                         BoardNotFoundException | UserNotFoundException e) {
                    System.out.println("오류: " + e.getMessage());
                }
            }
        }
    }

    private static void processRequest(Request request, PostManager postManager,
                                       BoardManager boardManager, UserManager userManager) {
        String url = request.getUrl();
        Session session = request.getSession();

        String[] urlParts = url.split("\\?", 2);
        String[] pathParts = urlParts[0].split("/");

        if (pathParts.length < 3) {
            throw new InvalidUrlException("잘못된 URL 형식입니다.");
        }

        Map<String, String> params = new HashMap<>();
        if (urlParts.length > 1) {
            for (String param : urlParts[1].split("&")) {
                String[] keyValue = param.split("=", 2);
                if (keyValue.length == 2) {
                    params.put(keyValue[0], keyValue[1]);
                }
            }
        }

        String category = pathParts[1];
        String action = pathParts[2];

        switch (category) {
            case "accounts":
                processAccountsRequest(action, params, session, userManager);
                break;
            case "boards":
                processBoardsRequest(action, params, session, boardManager);
                break;
            case "posts":
                processPostsRequest(action, params, session, postManager);
                break;
            default:
                throw new InvalidUrlException("지원하지 않는 카테고리입니다: " + category);
        }
    }

    private static void processAccountsRequest(String action, Map<String, String> params,
                                               Session session, UserManager userManager) {
        switch (action) {
            case "signup":
                userManager.signup(params.get("username"), params.get("password"), params.get("email"));
                break;
            case "signin":
                session.setLoggedInUser(userManager.signin(params.get("username"),
                        params.get("password"), session));
                break;
            case "signout":
                userManager.signout(session);
                break;
            case "detail":
                userManager.viewUser(Integer.parseInt(params.get("userId")));
                break;
            case "edit":
                userManager.editUser(Integer.parseInt(params.get("userId")),
                        params.get("password"), params.get("email"));
                break;
            case "remove":
                userManager.removeUser(Integer.parseInt(params.get("userId")), session);
                break;
            default:
                throw new InvalidUrlException("지원하지 않는 계정 작업입니다: " + action);
        }
    }

    private static void processBoardsRequest(String action, Map<String, String> params,
                                             Session session, BoardManager boardManager) {
        switch (action) {
            case "add":
                boardManager.addBoard(session.getLoggedInUser(), params.get("name"));
                break;
            case "edit":
                boardManager.editBoard(Integer.parseInt(params.get("boardId")), params.get("name"));
                break;
            case "remove":
                boardManager.deleteBoard(Integer.parseInt(params.get("boardId")));
                break;
            case "view":
                boardManager.viewBoard(Integer.parseInt(params.get("boardId")));
                break;
            case "list":
                boardManager.listBoards();
                break;
            default:
                throw new InvalidUrlException("지원하지 않는 게시판 작업입니다: " + action);
        }
    }

    private static void processPostsRequest(String action, Map<String, String> params,
                                            Session session, PostManager postManager) {
        switch (action) {
            case "add":
                postManager.addPost(session.getLoggedInUser(),
                        Integer.parseInt(params.get("boardId")),
                        params.get("title"),
                        params.get("content"));
                break;
            case "edit":
                postManager.editPost(Integer.parseInt(params.get("postId")),
                        params.get("title"),
                        params.get("content"));
                break;
            case "remove":
                postManager.deletePost(Integer.parseInt(params.get("postId")));
                break;
            case "view":
                postManager.viewPost(Integer.parseInt(params.get("postId")));
                break;
            case "list":
                postManager.viewBoardPosts(Integer.parseInt(params.get("boardId")));
                break;
            default:
                throw new InvalidUrlException("지원하지 않는 게시글 작업입니다: " + action);
        }
    }
}