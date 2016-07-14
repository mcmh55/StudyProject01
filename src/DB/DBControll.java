package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBControll {

	public static Connection getConnection() {
		
		/*
		// 기본 DB 접속
		conn = null;
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "hr";
		String pw = "hr";
		conn = DriverManager.getConnection(url, id, pw);
		*/
		
		// 커넥션 풀 처리 사용
		Connection conn = null;
		
		try {
			
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource) envContext.lookup("jdbc/myoracle");
			conn = ds.getConnection();
			
		} catch (NamingException ne) {	ne.printStackTrace(); }
		  catch (SQLException se) {		se.printStackTrace(); }
		
		return conn;
	}
	
	public static void closeDatabase(Connection conn, PreparedStatement psmt, ResultSet rs) {
		
		try {
			
			if ( conn != null )	conn.close();
			if ( psmt != null )	psmt.close();
			if ( rs != null)	rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}