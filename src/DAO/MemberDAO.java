package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DB.DBControll;
import DTO.MemberDTO;

public class MemberDAO {
	
	// 싱글톤 패턴: 단일 객체
	private static MemberDAO instance = new MemberDAO();
	public static MemberDAO getInstance() {
		
		return instance;
	}
	
/*
 * [입력한 아이디가 DB에 존재하는지 확인]
 * 입력한 아이디, 암호와 DB의 아이디, 암호와 비교
 * 아이디=불일치			'-1'을 리턴
 * 아이디=일치, 암호=불일치	'0'을 리턴
 * 아이디=일치, 암호=일치		'1'을 리턴
 */
	public int checkMember(String inputID, String inputPW) {
		
		int result = 0;
		String sql = "SELECT PW FROM TEST_MEMBER "
					+ "WHERE ID = ?";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBControll.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, inputID);
			rs = psmt.executeQuery();
			
			if ( rs.next() ) {
				
				if ( rs.getString("PW") != null && rs.getString("PW").equals(inputPW) ) {
					result = 1;
				} else {
					result = -1;
				}
				
			} else {
				result = 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBControll.closeDatabase(conn, psmt, rs);
		}
		
		return result;
	}
	
	// id를 기준으로 회원 정보 가져오기
	public MemberDTO getMember(String inputID) {
		
		MemberDTO mdto = null;
		String sql = "SELECT * FROM TEST_MEMBER "
					+ "WHERE ID = ?";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBControll.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, inputID);
			rs = psmt.executeQuery();
			
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
			DBControll.closeDatabase(conn, psmt, rs);
		}
		
		return mdto;
	}
	
/*
 * [아이디가 DB에 존재하는지 확인]
 * 입력한 아이디와 DB의 아이디와 비교
 * 존재	'1'을 리턴
 * 없음	'0'을 리턴
 */
	public int checkId(String inputID) {
		
		int result = 0;
		String sql = "SELECT ID FROM TEST_MEMBER "
					+ "WHERE ID = ?";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBControll.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, inputID);
			rs = psmt.executeQuery();
			
			if ( rs.next() ) {
				result = 1;
			} else {
				result = 0;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBControll.closeDatabase(conn, psmt, rs);
		}
		
		return result;
	}
	
	public int checkNick(String inputNick) {
		
		int result = 0;
		String sql = "SELECT NICK FROM TEST_MEMBER "
					+ "WHERE NICK = ? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBControll.getConnection();
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, inputNick);
			
			rs = psmt.executeQuery();
			
			if ( rs.next() ) {
				result = 1;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBControll.closeDatabase(conn, psmt, rs);
		}
		
		return result;
	}
	
	public int addMember(MemberDTO mdto) {
		
		int result = -1;
		String sql = "INSERT INTO TEST_MEMBER VALUES(?, ?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {
			
			conn = DBControll.getConnection();
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, mdto.getId());
			psmt.setString(2, mdto.getPw());
			psmt.setString(3, mdto.getNick());
			psmt.setInt(4, mdto.getAge());
			
			result = psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBControll.closeDatabase(conn, psmt, null);
		}
		
		return result;
	}
	
	public MemberDTO updateMember(MemberDTO mdto, String userNick, String inputNick, int inputAge) {
		
		String sql = "UPDATE TEST_MEMBER SET "
					+ "NICK = ?, AGE = ? "
					+ "WHERE NICK = ?";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {
			
			conn = DBControll.getConnection();
			psmt = conn.prepareStatement(sql);
			
			int i = 1;
			psmt.setString(i++, inputNick);
			psmt.setInt(i++, inputAge);
			psmt.setString(i, userNick);
			
			int count = 0;
			count = psmt.executeUpdate();
			
			if ( count == 1 ) {
				
				mdto.setNick(inputNick);
				mdto.setAge(inputAge);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBControll.closeDatabase(conn, psmt, null);
		}
		
		return mdto;
	}
}