package CommandBoard_Project;


import java.util.ArrayList;
import java.util.List;

public class PostManager {
    private List<Post> posts = new ArrayList<>();
    private int nextPostId = 1;

    public void addPost(User currentUser, int boardId, String title, String content) {
        String author = (currentUser != null) ? currentUser.getUsername() : "비회원";
        Post post = new Post(nextPostId++, boardId, title, content, author);
        posts.add(post);
        System.out.println("게시글이 작성되었습니다. (번호: " + post.getPostId() + ")");
    }

    public void viewPost(int postId) {
        Post post = findPostById(postId);
        if (post != null) {
            System.out.println(post);
        } else {
            throw new PostNotFoundException(postId + "번 게시글은 존재하지 않습니다.");
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
