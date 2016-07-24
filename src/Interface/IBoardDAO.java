package Interface;

import java.util.List;

import DTO.BoardDTO;

public interface IBoardDAO {

	List<BoardDTO> selectAllBoardList();
	boolean insertBoard(BoardDTO bdto);
	BoardDTO selectOneBoard(int seq);
}