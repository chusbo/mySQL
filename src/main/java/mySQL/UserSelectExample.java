package mySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserSelectExample {

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
				
				//매개변수화딘 SQL문 작성
				String sql = ""+
						"SELECT userid, username, userpassword, userage, useremail " +
						"FROM users " +
						"WHERE userid=?";
				
				//PreparedStatement 얻기 및 값 지정
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "spring");
				
				//SQL문 실행 후, ResultSet을 통해 데이터 읽기
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()) {
					User user = new User();
					user.setUserid(rs.getString("userid"));
					user.setUsername(rs.getString("username"));
					user.setUserpassword(rs.getString("userpassword"));
					user.setUserage(rs.getInt("userage"));
					user.setUseremail(rs.getString("useremail"));
					System.out.println(user);
				}else {	//데이터 행을 가져오지 않았을 경우
					System.out.println("사용자 아이디가 존재하지 않음");
				}
				rs.close();
				
				//PreparedStatement 닫기
				pstmt.close();
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(conn !=null) {
							try {
								//연결 끊기
								conn.close();
								}catch (SQLException e) {}
				}
			}

	} 
}