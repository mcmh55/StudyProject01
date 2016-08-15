package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DB.DBControll;
import DTO.BoardCommentDTO;
import DTO.BoardDTO;
import Interface.IBoardDAO;

public enum BoardDAO implements IBoardDAO {
	
	INSTANCE;

	// 모든 게시글 불러오기 ※ 제외: 삭제된 글
	@Override
	public List<BoardDTO> selectAllBoardList() {
		
		String sql = "SELECT * FROM MY_BOARD "
					+ "WHERE B_DEL = 0 "
					+ "ORDER BY B_GROUP DESC, B_DEPTH ASC, B_STEP ASC, B_SEQ DESC ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<BoardDTO> boardList = new ArrayList<BoardDTO>();
		BoardDTO bdto = null;
		
		try {
			
			conn = DBControll.getConnection();
			psmt = conn.prepareStatement(sql);
			
			rs = psmt.executeQuery();
			
			while ( rs.next() ) {
				
				int i = 1;
				bdto = new BoardDTO(
						rs.getInt(i++),		// B_SEQ
						rs.getInt(i++),		// B_GROUP
						rs.getInt(i++),		// B_DEPTH
						rs.getInt(i++),		// B_STEP
						rs.getInt(i++),		// B_PARENT_SEQ
						rs.getInt(i++),		// B_PARENT_DEL
						rs.getString(i++),	// B_ID
						rs.getInt(i++),		// B_PW
						rs.getString(i++),	// B_TITLE
						rs.getString(i++),	// B_CONTENT
						rs.getString(i++),	// B_FILENAME
						rs.getInt(i++),		// B_READCOUNT
						rs.getDate(i++),	// B_WRITEDATE
						rs.getInt(i++),		// B_NOTICE
						rs.getInt(i)		// B_DEL
						);
				
				boardList.add(bdto);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBControll.closeDatabase(conn, psmt, rs);
		}
		
		return boardList;
	}
	
	// 선택한 글 보기
	@Override
	public BoardDTO selectOneBoard(int seq) {
		
		String sql = "SELECT B_SEQ, B_GROUP, B_DEPTH, B_STEP, B_PARENT_SEQ, B_PARENT_DEL, B_ID, B_PW, B_TITLE, "
					+ "REPLACE(B_CONTENT, CHR(10), '<br/>'), "
					+ "B_FILENAME, B_READCOUNT, B_WRITEDATE, B_NOTICE, B_DEL "
					+ "FROM MY_BOARD "
					+ "WHERE B_SEQ = ? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		BoardDTO bdto = null;
		
		try {
			
			conn = DBControll.getConnection();
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1, seq);
			
			rs = psmt.executeQuery();
			
			if ( rs.next() ) {
				
				int i = 1;
				bdto = new BoardDTO(
						rs.getInt(i++),		// B_SEQ
						rs.getInt(i++),		// B_GROUP
						rs.getInt(i++),		// B_DEPTH
						rs.getInt(i++),		// B_STEP
						rs.getInt(i++),		// B_PARENT_SEQ
						rs.getInt(i++),		// B_PARENT_DEL
						rs.getString(i++),	// B_ID
						rs.getInt(i++),		// B_PW
						rs.getString(i++),	// B_TITLE
						rs.getString(i++),	// B_CONTENT
						rs.getString(i++),	// B_FILENAME
						rs.getInt(i++),		// B_READCOUNT
						rs.getDate(i++),	// B_WRITEDATE
						rs.getInt(i++),		// B_NOTICE
						rs.getInt(i)		// B_DEL
						);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBControll.closeDatabase(conn, psmt, rs);
		}
		
		return bdto;
	}

	// 검색 조건에 해당되는 모든 게시글 불러오기 ※ 제외: 삭제된 글
	@Override
	public List<BoardDTO> selectSearchBoardList(int searchType, String searchText) {
		
		if ( searchType == 0 || searchText.equals("") || searchText == null ) {
			
			System.out.println("selectSearchBoardList 매개변수 값을 정상적으로 받아오지 못함");
			
			return null;
		}
		
		String sql = null;
		
		switch ( searchType ) {
		case 1:
			sql = "SELECT * FROM MY_BOARD "
				+ "WHERE B_DEL = 0 "
				+ "AND B_TITLE LIKE ? "
				+ "OR B_CONTENT LIKE ? "
				+ "ORDER BY B_SEQ DESC "; 
			break;
		
		case 2:
			sql = "SELECT * FROM MY_BOARD "
				+ "WHERE B_DEL = 0 "
				+ "AND B_TITLE LIKE ? "
				+ "ORDER BY B_SEQ DESC ";
			break;
			
		case 3:	
			sql = "SELECT * FROM MY_BOARD "
				+ "WHERE B_DEL = 0 "
				+ "AND B_ID LIKE ? "
				+ "ORDER BY B_SEQ DESC ";
			break;
		
		default:
			break;
		}
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<BoardDTO> bdtoList = new ArrayList<BoardDTO>();
		BoardDTO bdto = null;
		
		try {
			
			conn = DBControll.getConnection();
			psmt = conn.prepareStatement(sql);
			
			if ( searchType == 1 ) {
				
				psmt.setString(1, "%" + searchText + "%");
				psmt.setString(2, "%" + searchText + "%");
				
			}
			else if ( searchType == 2 ) {	psmt.setString(1, "%" + searchText + "%"); }
			else if ( searchType == 3) 	{	psmt.setString(1, "%" + searchText + "%"); }
			
			rs = psmt.executeQuery();
			
			while ( rs.next() ) {
				
				int i = 1;
				bdto = new BoardDTO(
						rs.getInt(i++),		// B_SEQ
						rs.getInt(i++),		// B_GROUP
						rs.getInt(i++),		// B_DEPTH
						rs.getInt(i++),		// B_STEP
						rs.getInt(i++),		// B_PARENT_SEQ
						rs.getInt(i++),		// B_PARENT_DEL
						rs.getString(i++),	// B_ID
						rs.getInt(i++),		// B_PW
						rs.getString(i++),	// B_TITLE
						rs.getString(i++),	// B_CONTENT
						rs.getString(i++),	// B_FILENAME
						rs.getInt(i++),		// B_READCOUNT
						rs.getDate(i++),	// B_WRITEDATE
						rs.getInt(i++),		// B_NOTICE
						rs.getInt(i)		// B_DEL
						);
				
				bdtoList.add(bdto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBControll.closeDatabase(conn, psmt, rs);
		}
		
		return bdtoList;
	}
	
	// 글 저장
	@Override
	public boolean insertBoard(BoardDTO bdto) {
		
		String sql = "INSERT INTO MY_BOARD "
					+ "(B_SEQ, B_GROUP, B_DEPTH, B_STEP, B_PARENT_SEQ, B_PARENT_DEL, B_ID, B_PW, B_TITLE, "
					+ "B_CONTENT, B_FILENAME, B_READCOUNT, B_WRITEDATE, B_NOTICE, B_DEL) "
					+ "VALUES(SEQ_MY_BOARD.NEXTVAL, SEQ_MY_BOARD.CURRVAL, 0, 0, 0, 0, ?, ?, ?, "
					+ "?, ?, 0, SYSDATE, 0, 0) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int count = 0;
		
		try {
			
			conn = DBControll.getConnection();
			psmt = conn.prepareStatement(sql);
			
			int i = 1;
			psmt.setString(i++, bdto.getId());
			psmt.setInt(i++, bdto.getPw());
			psmt.setString(i++, bdto.getTitle());
			psmt.setString(i++, bdto.getContent());
			psmt.setString(i++, bdto.getFileName());
			
			count = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBControll.closeDatabase(conn, psmt, null);
		}
		
		return count > 0 ? true : false;
	}
	

	// 답글 저장
	@Override
	public boolean insertReplyBoard(int seq, BoardDTO bdto) {
		
		String sql = "INSERT INTO MY_BOARD "
					+ "(B_SEQ, B_GROUP, B_DEPTH, B_STEP, B_PARENT_SEQ, B_PARENT_DEL, B_ID, B_PW, B_TITLE, "
					+ "B_CONTENT, B_FILENAME, B_READCOUNT, B_WRITEDATE, B_NOTICE, B_DEL) "
					+ "VALUES(SEQ_MY_BOARD.NEXTVAL, "
					+ "(SELECT B_GROUP FROM MY_BOARD WHERE B_SEQ = ?), "
					+ "(SELECT B_DEPTH FROM MY_BOARD WHERE B_SEQ = ?) + 1, "
					+ "(SELECT NVL2(MAX(B_STEP), MAX(B_STEP)+1, 0) FROM MY_BOARD "
					+ "WHERE B_GROUP = (SELECT B_GROUP FROM MY_BOARD WHERE B_SEQ = ?) "
					+ "AND B_DEPTH = (SELECT B_DEPTH FROM MY_BOARD WHERE B_SEQ = ?) + 1), "
					+ "?, 0, ?, ?, ?, ?, ?, 0, SYSDATE, 0, 0) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int count = 0;
		
		try {
			
			conn = DBControll.getConnection();
			psmt = conn.prepareStatement(sql);
			
			int i = 1;
			psmt.setInt(i++, seq);
			psmt.setInt(i++, seq);
			psmt.setInt(i++, seq);
			psmt.setInt(i++, seq);
			psmt.setInt(i++, seq);
			psmt.setString(i++, bdto.getId());
			psmt.setInt(i++, bdto.getPw());
			psmt.setString(i++, bdto.getTitle());
			psmt.setString(i++, bdto.getContent());
			psmt.setString(i, bdto.getFileName());
			
			count = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBControll.closeDatabase(conn, psmt, null);
		}
		
		return count > 0 ? true : false;
	}


	// 글 수정
	@Override
	public boolean updateBoard(BoardDTO bdto) {
		
		String sql = "UPDATE MY_BOARD SET "
					+ "B_PW = ?, B_TITLE = ?, B_CONTENT = ?, B_FILENAME = ? "
					+ "WHERE B_SEQ = ?";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int count = 0;
		
		try {
			
			conn = DBControll.getConnection();
			psmt = conn.prepareStatement(sql);
			
			int i = 1;
			psmt.setInt(i++, bdto.getPw());
			psmt.setString(i++, bdto.getTitle());
			psmt.setString(i++, bdto.getContent());
			psmt.setString(i++, bdto.getFileName());
			psmt.setInt(i, bdto.getSeq());
			
			count = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBControll.closeDatabase(conn, psmt, null);
		}
		
		return count > 0 ? true : false;
	}
	

	// 글 삭제
	@SuppressWarnings("resource")
	@Override
	public boolean deleteBoard(int seq) {
		
		String sql1 = "UPDATE MY_BOARD SET "
					+ "B_DEL = 1 "
					+ "WHERE B_SEQ = ? ";
		
		String sql2 = "UPDATE MY_BOARD SET "
					+ "B_PARENT_DEL = 1 "
					+ "WHERE B_PARENT_SEQ = ? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int count = 0;
		
		try {
			
			conn = DBControll.getConnection();
			conn.setAutoCommit(false);
			
			psmt = conn.prepareStatement(sql1);
			psmt.setInt(1, seq);
			
			count += psmt.executeUpdate();
			psmt.clearParameters();
			
			psmt = conn.prepareStatement(sql2);
			psmt.setInt(1, seq);
			
			count += psmt.executeUpdate();
			
		} catch (SQLException e) {
			
			try { conn.rollback(); } 
			catch (SQLException e1) { e1.printStackTrace();	}
			e.printStackTrace();
			
		} finally {
			
			try { conn.setAutoCommit(true); } 
			catch (SQLException e) { e.printStackTrace(); }
			DBControll.closeDatabase(conn, psmt, null);
		}
		
		return count > 1 ? true : false;
	}
	
	
	//////////		댓글		//////////
	
	// 댓글 리스트 불러오기
	@Override
	public List<BoardCommentDTO> selectCommentList(int seq) {
		
		String sql = "SELECT BC_SEQ, BC_GROUP, BC_DEPTH, BC_STEP, "
					+ "BC_PARENT_SEQ, BC_BOARD_SEQ, BC_ID, "
					+ "REPLACE(BC_CONTENT, CHR(10), '<br/>'), "
					+ "BC_WRITEDATE, BC_DEL "
					+ "FROM MY_BOARD_COMMENT "
					+ "WHERE BC_BOARD_SEQ = ? "
					+ "ORDER BY BC_GROUP ASC, BC_WRITEDATE ASC ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<BoardCommentDTO> commentList = new ArrayList<BoardCommentDTO>();
		BoardCommentDTO bcdto = null;
		
		try {
			
			conn = DBControll.getConnection();
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			
			rs = psmt.executeQuery();
			
			while ( rs.next() ) {
				
				int i = 1;
				bcdto = new BoardCommentDTO(
							rs.getInt(i++),
							rs.getInt(i++),
							rs.getInt(i++),
							rs.getInt(i++),
							rs.getInt(i++),
							rs.getInt(i++),
							rs.getString(i++),
							rs.getString(i++),
							rs.getDate(i++),
							rs.getInt(i++)
							);
				
				commentList.add(bcdto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBControll.closeDatabase(conn, psmt, rs);
		}
		
		return commentList;
	}
	
	
	// 댓글 작성
	@Override
	public boolean writeComment(int boardSeq, BoardCommentDTO bcdto) {
		
		String sql = "INSERT INTO MY_BOARD_COMMENT "
					+ "(BC_SEQ, BC_GROUP, BC_DEPTH, BC_STEP, BC_PARENT_SEQ, "
					+ "BC_BOARD_SEQ, BC_ID, BC_CONTENT, BC_WRITEDATE, BC_DEL) "
					+ "VALUES(SEQ_MY_BOARD_COMMENT.NEXTVAL, SEQ_MY_BOARD_COMMENT.CURRVAL, 0, 0, 0, "
					+ "?, ?, ?, SYSDATE, 0) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int count = 0;
		
		try {
			
			conn = DBControll.getConnection();
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, boardSeq);
			psmt.setString(2, bcdto.getId());
			psmt.setString(3, bcdto.getContent());
			
			count = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBControll.closeDatabase(conn, psmt, null);
		}
		
		return count > 0 ? true : false;
	}

	
	// 댓글 수정
	@Override
	public boolean updateComment(int commentSeq, String commentContent) {
		
		String sql = "UPDATE MY_BOARD_COMMENT SET "
					+ "BC_CONTENT = ? "
					+ "WHERE BC_SEQ = ? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int count = 0;
		
		try {
			
			conn = DBControll.getConnection();
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, commentContent);
			psmt.setInt(2, commentSeq);
			
			count = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBControll.closeDatabase(conn, psmt, null);
		}
		
		return count > 0 ? true : false;
	}

	
	// 댓글 삭제
	@Override
	public boolean deleteComment(int commentSeq) {
		
		String sql = "UPDATE MY_BOARD_COMMENT SET "
					+ "BC_DEL = 1 "
					+ "WHERE BC_SEQ = ? ";
	
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int count = 0;
		
		try {
			
			conn = DBControll.getConnection();
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1, commentSeq);
			
			count = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBControll.closeDatabase(conn, psmt, null);
		}
		
		return count > 0 ? true : false;
	}

	
	// 댓글 답글 작성
	@Override
	public boolean replyComment(BoardCommentDTO comment) {
		
		String sql = "INSERT INTO MY_BOARD_COMMENT "
					+ "(BC_SEQ, BC_GROUP, BC_DEPTH, BC_STEP, BC_PARENT_SEQ, "
					+ "BC_BOARD_SEQ, BC_ID, BC_CONTENT, BC_WRITEDATE, BC_DEL) "
					+ "VALUES(SEQ_MY_BOARD_COMMENT.NEXTVAL, "
					+ "(SELECT BC_GROUP FROM MY_BOARD_COMMENT WHERE BC_SEQ = ?), "
					+ "(SELECT BC_DEPTH FROM MY_BOARD_COMMENT WHERE BC_SEQ = ?) + 1, "
					+ "(SELECT NVL2(MAX(BC_STEP), MAX(BC_STEP)+1, 0) FROM MY_BOARD_COMMENT "
					+ "WHERE BC_GROUP = (SELECT BC_GROUP FROM MY_BOARD_COMMENT WHERE BC_SEQ = ?) "
					+ "AND BC_DEPTH = (SELECT BC_DEPTH FROM MY_BOARD_COMMENT WHERE BC_SEQ = ?) + 1), "
					+ "?, ?, ?, ?, SYSDATE, 0) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int count = 0;
		
		try {
			
			conn = DBControll.getConnection();
			psmt = conn.prepareStatement(sql);
			
			int i = 1;
			psmt.setInt(i++, comment.getSeq());			// BC_GROUP
			psmt.setInt(i++, comment.getSeq());			// BC_DEPTH
			psmt.setInt(i++, comment.getSeq());			// BC_STEP(BC_GROUP)
			psmt.setInt(i++, comment.getSeq());			// BC_STEP(BC_DEPTH)
			psmt.setInt(i++, comment.getSeq());			// BC_PARENT_SEQ
			psmt.setInt(i++, comment.getBoardSeq());	// BC_BOARD_SEQ
			psmt.setString(i++, comment.getId());		// BC_ID
			psmt.setString(i++, comment.getContent());	// BC_CONTENT
			
			count = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBControll.closeDatabase(conn, psmt, null);
		}
		
		return count > 0 ? true : false;
	}
}