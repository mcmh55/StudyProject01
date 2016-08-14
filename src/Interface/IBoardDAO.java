package Interface;

import java.util.List;

import DTO.BoardCommentDTO;
import DTO.BoardDTO;

public interface IBoardDAO {

	List<BoardDTO> selectAllBoardList();
	BoardDTO selectOneBoard(int seq);
	List<BoardDTO> selectSearchBoardList(int searchType, String searchText);
	boolean insertBoard(BoardDTO bdto);
	boolean updateBoard(BoardDTO bdto);
	boolean deleteBoard(int seq);
	
	boolean insertReplyBoard(int seq, BoardDTO bdto);
	
	List<BoardCommentDTO> selectCommentList(int seq);
	boolean insertComment(int seq, BoardCommentDTO bcdto);
	boolean updateComment(int commentSeq, String commentContent);
	boolean deleteComment(int commentSeq);
}