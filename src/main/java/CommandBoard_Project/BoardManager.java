package CommandBoard_Project;

import java.util.ArrayList;
import java.util.List;

// 게시판의 생성, 수정, 삭제, 조회 등의 기능

public class BoardManager {
    private List<Board> boards = new ArrayList<>();
    private int nextBoardId = 1;

    public void addBoard(User currentUser, String boardName) {
        String creator = (currentUser != null) ? currentUser.getUsername() : "비회원";
        Board board = new Board(nextBoardId++, boardName, creator);
        boards.add(board);
        System.out.println("게시판이 추가되었습니다. (번호: " + board.getBoardId() + ")");
    }

    public void editBoard(int boardId, String newName) {
        Board board = findBoardById(boardId);
        if (board != null) {
            board.setBoardName(newName);
            System.out.println(boardId + "번 게시판이 성공적으로 수정되었습니다!");
        } else {
            throw new BoardNotFoundException(boardId + "번 게시판은 존재하지 않습니다.");
        }
    }

    public void deleteBoard(int boardId) {
        Board board = findBoardById(boardId);
        if (board != null) {
            boards.remove(board);
            System.out.println(boardId + "번 게시판이 성공적으로 삭제되었습니다!");
        } else {
            throw new BoardNotFoundException(boardId + "번 게시판은 존재하지 않습니다.");
        }
    }

    public void viewBoard(int boardId) {
        Board board = findBoardById(boardId);
        if (board != null) {
            System.out.println("게시판 정보: " + board);
        } else {
            throw new BoardNotFoundException(boardId + "번 게시판은 존재하지 않습니다.");
        }
    }

    public void listBoards() {
        if (boards.isEmpty()) {
            System.out.println("등록된 게시판이 없습니다.");
            return;
        }
        System.out.println("전체 게시판 목록:");
        for (Board board : boards) {
            System.out.println(board);
        }
    }

    public Board getBoardById(int boardId) {
        return findBoardById(boardId);
    }

    public boolean boardExists(int boardId) {
        return findBoardById(boardId) != null;
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