package DB;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBconn {

	public Connection conn;
	
	// 싱글톤 패턴: 단일 객체
	private static DBconn instance = new DBconn();
	public static DBconn getInstance() {
		
		return instance;
	}
	
	public Connection getConnection() throws Exception {
		
		conn = null;
		Context initContext = new InitialContext();
		Context envContext = (Context) initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource) envContext.lookup("jdbc/myoracle");
		conn = ds.getConnection();
		
		return conn;
		
	}
}