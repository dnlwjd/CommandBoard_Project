package CommandBoard_Project;

import java.time.LocalDateTime;

// 게시글 ID, 제목, 내용, 작성자, 작성일, 수정일

public class Post {
    private int postId;
    private int boardId;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private String author;

    public Post(int postId, int boardId, String title, String content, String author) {
        this.postId = postId;
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.author = author;
        this.createdDate = LocalDateTime.now();
        this.modifiedDate = LocalDateTime.now();
    }

    public int getPostId() {
        return postId;
    }

    public int getBoardId() {
        return boardId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        this.modifiedDate = LocalDateTime.now();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        this.modifiedDate = LocalDateTime.now();
    }

    public String getAuthor() {
        return author;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    @Override
    public String toString() {
        return "게시글 번호: " + postId +
                "\n제목: " + title +
                "\n내용: " + content +
                "\n작성자: " + author +
                "\n작성일: " + createdDate +
                "\n수정일: " + modifiedDate;
    }
}