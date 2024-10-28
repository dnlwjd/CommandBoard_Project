package CommandBoard_Project;

import java.util.ArrayList;
import java.util.List;

public class PostManager {
    private List<Post> posts = new ArrayList<>();
    private int nextPostId = 1;
    private final BoardManager boardManager;

    public PostManager(BoardManager boardManager) {
        this.boardManager = boardManager;
    }

    public void addPost(User currentUser, int boardId, String title, String content) {
        if (!boardManager.boardExists(boardId)) {
            throw new BoardNotFoundException(boardId + "번 게시판은 존재하지 않습니다.");
        }
        String author = (currentUser != null) ? currentUser.getUsername() : "비회원";
        Post post = new Post(nextPostId++, boardId, title, content, author);
        posts.add(post);
        System.out.println("게시글이 작성되었습니다. (번호: " + post.getPostId() + ")");
    }

    public void editPost(int postId, String title, String content) {
        Post post = findPostById(postId);
        if (post != null) {
            post.setTitle(title);
            post.setContent(content);
            System.out.println(postId + "번 게시글이 성공적으로 수정되었습니다!");
        } else {
            throw new PostNotFoundException(postId + "번 게시글은 존재하지 않습니다.");
        }
    }

    public void deletePost(int postId) {
        Post post = findPostById(postId);
        if (post != null) {
            posts.remove(post);
            System.out.println(postId + "번 게시글이 성공적으로 삭제되었습니다!");
        } else {
            throw new PostNotFoundException(postId + "번 게시글은 존재하지 않습니다.");
        }
    }

    public void viewPost(int postId) {
        Post post = findPostById(postId);
        if (post != null) {
            // 게시판 정보 가져오기
            Board board = boardManager.getBoardById(post.getBoardId());
            System.out.println("게시판: " + board.getBoardName());
            System.out.println("------------------------------------");
            System.out.println(post);
        } else {
            throw new PostNotFoundException(postId + "번 게시글은 존재하지 않습니다.");
        }
    }

    public void viewBoardPosts(int boardId) {
        if (!boardManager.boardExists(boardId)) {
            throw new BoardNotFoundException(boardId + "번 게시판은 존재하지 않습니다.");
        }

        // 해당 게시판의 게시글만 필터링
        List<Post> boardPosts = new ArrayList<>();
        for (Post post : posts) {
            if (post.getBoardId() == boardId) {
                boardPosts.add(post);
            }
        }

        // 게시판 이름 가져오기
        Board board = boardManager.getBoardById(boardId);
        System.out.println("\n'" + board.getBoardName() + "' 게시판 글 목록:");
        System.out.println("------------------------------------");
        System.out.println("글번호 / 제목 / 작성자 / 작성일");
        System.out.println("------------------------------------");

        if (boardPosts.isEmpty()) {
            System.out.println("등록된 게시글이 없습니다.");
            return;
        }

        for (Post post : boardPosts) {
            System.out.printf("%d / %s / %s / %s%n",
                    post.getPostId(),
                    post.getTitle(),
                    post.getAuthor(),
                    post.getCreatedDate());
        }
    }

    private Post findPostById(int postId) {
        for (Post post : posts) {
            if (post.getPostId() == postId) {
                return post;
            }
        }
        return null;
    }
}