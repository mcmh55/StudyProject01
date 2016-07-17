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
 * [입력한 ID, PW가 DB에 존재하는지 확인]
 * 입력한 ID, PW와 DB의 ID, PW와 비교
 * ID=불일치			'-1'을 리턴
 * ID=일치, PW=불일치	'0'을 리턴
 * ID=일치, PW=일치	'1'을 리턴
 */
	public int checkMember(String inputID, String inputPW) {
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		int result = 0;
		
		String sql = "SELECT PW FROM MEMBER "
					+ "WHERE ID = ?";
		
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
	
/*
 * [ID가 DB에 존재하는지 확인]
 * 입력한 ID와 DB의 ID와 비교
 * 존재	'1'을 리턴
 * 없음	'0'을 리턴
 */
	public boolean checkId(String inputID) {
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		boolean result = false;
		
		String sql = "SELECT ID FROM MEMBER "
					+ "WHERE ID = ?";
		
		try {
			
			conn = DBControll.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, inputID);
			rs = psmt.executeQuery();
			
			if ( rs.next() ) {
				result = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBControll.closeDatabase(conn, psmt, rs);
		}
		
		return result;
	}
	
/*
 * [별명이 DB에 존재하는지 확인]
 * 입력한 별명과 DB의 별명과 비교
 * 존재	'1'을 리턴
 * 없음	'0'을 리턴
 */
	public boolean checkNick(String inputNick) {
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		boolean result = false;
		
		String sql = "SELECT NICK FROM MEMBER "
					+ "WHERE NICK = ? ";
		
		try {
			
			conn = DBControll.getConnection();
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, inputNick);
			
			rs = psmt.executeQuery();
			
			if ( rs.next() ) {
				result = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBControll.closeDatabase(conn, psmt, rs);
		}
		
		return result;
	}
	
	public boolean checkEmail(String inputEmail) {
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		boolean result = false;
		
		String sql = "SELECT EMAIL FROM MEMBER "
					+ "WHERE EMAIL = ? ";
		
		try {
			
			conn = DBControll.getConnection();
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, inputEmail);
			
			rs = psmt.executeQuery();
			
			if ( rs.next() ) {
				result = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBControll.closeDatabase(conn, psmt, rs);
		}
		
		return result;
	}
	
//////////////////////////////
/*
 * 	[회원 - 가입, 수정, 삭제, 조회]
 */
//////////////////////////////
	public boolean addMember(MemberDTO mdto) {
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int result = 0;
		
		String sql = "INSERT INTO MEMBER VALUES(?, ?, ?, ?, ?)";
		
		try {
			
			conn = DBControll.getConnection();
			psmt = conn.prepareStatement(sql);
			
			int i = 1;
			psmt.setString(i++, mdto.getId());
			psmt.setString(i++, mdto.getPw());
			psmt.setString(i++, mdto.getNick());
			psmt.setInt(i++, mdto.getAge());
			psmt.setString(i, mdto.getEmail());
			
			result = psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBControll.closeDatabase(conn, psmt, null);
		}
		
		return result > 0 ? true : false;
	}
	
	public MemberDTO updateMember(MemberDTO mdto, String inputPw, 
									String inputNick, int inputAge, String inputEmail) {
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		String sql = "UPDATE MEMBER SET "
					+ "PW = ?, NICK = ?, AGE = ?, EMAIL = ? "
					+ "WHERE ID = ?";
		
		try {
			
			conn = DBControll.getConnection();
			psmt = conn.prepareStatement(sql);
			
			String pw = "";
			
			int i = 1;
			if ( inputPw.equals("") ) {
				pw = mdto.getPw();
			} else {
				pw = inputPw;
			}
			
			psmt.setString(i++, pw);
			psmt.setString(i++, inputNick);
			psmt.setInt(i++, inputAge);
			psmt.setString(i++, inputEmail);
			psmt.setString(i, mdto.getId());
			
			int count = 0;
			count = psmt.executeUpdate();
			
			if ( count == 1 ) {
				
				mdto.setPw(pw);
				mdto.setNick(inputNick);
				mdto.setAge(inputAge);
				mdto.setEmail(inputEmail);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBControll.closeDatabase(conn, psmt, null);
		}
		
		return mdto;
	}
	
	public int deleteMember(String userId) {
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int result = 0;
		
		String sql = "DELETE FROM MEMBER "
					+ "WHERE ID = ? ";
		
		try {
			
			conn = DBControll.getConnection();
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, userId);
			
			result = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBControll.closeDatabase(conn, psmt, null);
		}
		
		return result;
	}
	
	
	// ID를 기준으로 회원 정보 가져오기
	public MemberDTO getMember(String inputID) {
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		MemberDTO mdto = null;
		
		String sql = "SELECT * FROM MEMBER "
					+ "WHERE ID = ?";
		
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
				mdto.setEmail(rs.getString("EMAIL"));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBControll.closeDatabase(conn, psmt, rs);
		}
		
		return mdto;
	}
	
	// Email을 기준으로 회원 정보 가져오기
	public MemberDTO getFindMember(String inputEmail) {
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		MemberDTO mdto = null;
		
		String sql = "SELECT * FROM MEMBER "
					+ "WHERE EMAIL = ?";
		
		try {
			
			conn = DBControll.getConnection();
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, inputEmail);
			
			rs = psmt.executeQuery();
			
			if ( rs.next() ) {
				
				mdto = new MemberDTO();
				mdto.setId(rs.getString("ID"));
				mdto.setPw(rs.getString("PW"));
				mdto.setNick(rs.getString("NICK"));
				mdto.setAge(rs.getInt("AGE"));
				mdto.setEmail(rs.getString("EMAIL"));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBControll.closeDatabase(conn, psmt, rs);
		}
		
		return mdto;
	}
	
}