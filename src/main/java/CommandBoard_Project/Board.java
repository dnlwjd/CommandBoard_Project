package CommandBoard_Project;

// 게시판 ID, 이름, 생성자 정보를 관리

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

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public String getCreator() {
        return creator;
    }

    @Override
    public String toString() {
        return "게시판 번호: " + boardId + ", 이름: " + boardName + ", 작성자: " + creator;
    }
}