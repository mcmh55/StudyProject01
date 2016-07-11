package DB;

import java.sql.Connection;
//import java.sql.DriverManager;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBconn {

	public static Connection getConnection() throws Exception {
		
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
		Context initContext = new InitialContext();
		Context envContext = (Context) initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource) envContext.lookup("jdbc/myoracle");
		conn = ds.getConnection();
		
		return conn;
		
	}
}