package Interface;

import java.util.List;

import DTO.BoardDTO;

public interface IBoardDAO {

	List<BoardDTO> selectAllBoardList();
	BoardDTO selectOneBoard(int seq);
	List<BoardDTO> selectSearchBoardList(int searchType, String searchText);
	boolean insertBoard(BoardDTO bdto);
	boolean insertReplyBoard(int seq, BoardDTO bdto);
	boolean updateBoard(BoardDTO bdto);
	boolean deleteBoard(int seq);
}