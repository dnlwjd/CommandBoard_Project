package CommandBoard_Project;


public class Board {
    private int boardId;
    private String boardName;
    private String creator;

    public Board(int boardId, String boardName, String creator) {
        this.boardId = boardId;
        this.boardName = boardName;
        this.creator = creator;
    }

    public int getBoardId() {
        return boardId;
    }

    public String getBoardName() {
        return boardName;
    }

    public String getCreator() {
        return creator;
    }

    @Override
    public String toString() {
        return "게시판 번호: " + boardId + ", 이름: " + boardName + ", 작성자: " + creator;
    }
}

