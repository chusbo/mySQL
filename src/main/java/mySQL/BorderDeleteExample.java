package mySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BorderDeleteExample {

	public static void main(String[] args) {
		Connection conn = null;
		try {
			//JDBC Driver 등록
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//연결하기
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/servletex?useUnicode=true&characterEncoding=utf8",
					"root",
					"1234");
			
			//매개변수화된 SQL문 ㅈ가성
				String sql = "DELETE FROM boards WHERE bwriter=?";
				
				//PreparedStatement 얻기 및 지정
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "winter");

				//SQL문 실행
				int rows = pstmt.executeUpdate();
				System.out.println("삭제된 행 수:" + rows);
				
				//PreparedStatement 닫기
				pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) {
				try {
					//연겨 끊기
					conn.close();
				} catch (SQLException e) {}
				}
			}
		}

}
