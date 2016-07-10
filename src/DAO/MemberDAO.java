package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import DB.DBconn;
import DTO.MemberDTO;

public class MemberDAO {
	
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	// 싱글톤 패턴: 단일 객체
	private static MemberDAO instance = new MemberDAO();
	public static MemberDAO getInstance() {
		
		return instance;
	}
	
/*
 * [유저가 DB에 존재하는지 확인]
 * 입력한 아이디, 암호와 DB의 아이디, 암호와 비교
 * 아이디=불일치			'-1'을 리턴
 * 아이디=일치, 암호=불일치	'0'을 리턴
 * 아이디=일치, 암호=일치		'1'을 리턴
 */
	public int userCheck(String inputID, String inputPW) {
		
		int result = -1;
		String sql = "SELECT PW FROM TEST_MEMBER WHERE ID = ?";
		
		DBconn db = DBconn.getInstance();
		pstmt = null;
		rs = null;
		
		try {
			
			pstmt = db.getConnection().prepareStatement(sql);
			pstmt.setString(1, inputID);
			rs = pstmt.executeQuery();
			
			if ( rs.next() ) {
				
				if ( rs.getString("PW") != null && rs.getString("PW").equals(inputPW) ) {
					result = 1;
				} else {
					result = 0;
				}
				
			} else {
				result = -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				
				if ( rs != null ) 		rs.close();
				if ( pstmt != null ) 	pstmt.close();
				if ( db.conn != null ) 	db.conn.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	// id를 기준으로 회원 정보 가져오기
	public MemberDTO getMember(String inputID) {
		
		MemberDTO mdto = null;
		String sql = "SELECT * FROM TEST_MEMBER" + "\n"
					+ "WHERE ID = ?";
		DBconn db = DBconn.getInstance();
		pstmt = null;
		rs = null;
		
		try {
			
			pstmt = db.getConnection().prepareStatement(sql);
			pstmt.setString(1, inputID);
			rs = pstmt.executeQuery();
			
			if ( rs.next() ) {
				
				mdto = new MemberDTO();
				mdto.setId(rs.getString("ID"));
				mdto.setPw(rs.getString("PW"));
				mdto.setNick(rs.getString("NICK"));
				mdto.setAge(rs.getInt("AGE"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				
				if ( rs != null ) 		rs.close();
				if ( pstmt != null ) 	pstmt.close();
				if ( db.conn != null ) 	db.conn.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return mdto;
	}
	
/*
 * [아이디가 DB에 존재하는지 확인]
 * 입력한 아이디와 DB의 아이디와 비교
 * 존재	'1'을 리턴
 * 없음	'-1'을 리턴
 */
	public int idCheck(String inputID) {
		
		int result = -1;
		String sql = "SELECT ID FROM TEST_MEMBER WHERE ID = ?";
		DBconn db = DBconn.getInstance();
		pstmt = null;
		rs = null;
		
		try {
			
			pstmt = db.getConnection().prepareStatement(sql);
			pstmt.setString(1, inputID);
			rs = pstmt.executeQuery();
			
			if ( rs.next() ) {
				result = 1;
			} else {
				result = -1;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				
				if ( rs != null ) 		rs.close();
				if ( pstmt != null ) 	pstmt.close();
				if ( db.conn != null ) 	db.conn.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public int insertMember(MemberDTO mdto) {
		
		int result = -1;
		String sql = "INSERT INTO TEST_MEMBER VALUES(?, ?, ?, ?)";
		DBconn db = DBconn.getInstance();
		pstmt = null;
		
		try {
			
			pstmt = db.getConnection().prepareStatement(sql);
			
			pstmt.setString(1, mdto.getId());
			pstmt.setString(2, mdto.getPw());
			pstmt.setString(3, mdto.getNick());
			pstmt.setInt(4, mdto.getAge());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				
				if ( pstmt != null )	pstmt.close();
				if ( db.conn != null )	db.conn.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
}