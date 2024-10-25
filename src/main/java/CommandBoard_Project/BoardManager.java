package CommandBoard_Project;


import java.util.ArrayList;
import java.util.List;

public class BoardManager {
    private List<Board> boards = new ArrayList<>();
    private int nextBoardId = 1;

    public void addBoard(User currentUser, String boardName) {
        String creator = (currentUser != null) ? currentUser.getUsername() : "비회원";
        Board board = new Board(nextBoardId++, boardName, creator);
        boards.add(board);
        System.out.println("게시판이 추가되었습니다. (번호: " + board.getBoardId() + ")");
    }


    public void viewBoard(int boardId) {
        Board board = findBoardById(boardId);
        if (board != null) {
            System.out.println("게시판 정보: " + board);
        } else {
            throw new BoardNotFoundException(boardId + "번 게시판은 존재하지 않습니다.");
        }
    }

    private Board findBoardById(int boardId) {
        for (Board board : boards) {
            if (board.getBoardId() == boardId) {
                return board;
            }
        }
        return null;
    }
}
