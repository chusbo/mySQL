package mySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {

	public static void main(String[] args) {
		Connection conn = null;
		try { //JDBC Driver org.mariadb.jdbc.Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
		
			//연결하기
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/servletex",
					"root",
					"1234");
		System.out.println("연결 성공"); 
		} catch	(ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn != null) {
				try {
						//연결 끊기
					conn.close();
					System.out.println("연결 끊기");
				} catch (SQLException e) {}
			}
		}
	}
}

