package mySQL;


import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BoardSelectExample {

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
					"SELECT bno, btitle, bcontent, bwriter, bdate, bfilename, bfiledata " +
					"FROM boards " +
					" WHERE bwriter=?";
			//PreparedStatement 얻기 및 값 지정
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "winter");
			
			//SQL문 실행 후, ResultSet을 통해 데이터 읽기
			ResultSet rs = pstmt.executeQuery();					
			
			while(rs.next()) {
				
				int bno = rs.getInt("bno");
				String btitle = rs.getString("btitle");
				String bcontent = rs.getString("bcontent");
				String bwriter = rs.getString("bwriter");
				Date bdate = rs.getDate("bdate");
				String bfilename = rs.getString("bfilename");
				Blob bfiledata = rs.getBlob("bfiledata");	
				
				//데이터 행을 읽고 Board 객체 생성
				Board board = new Board();
				board.setBno(bno);
				board.setBtitle(btitle);
				board.setBcontent(bcontent);
				board.setBwriter(bwriter);
				board.setBdate(bdate);
				board.setBfilename(bfilename);
				board.setBfiledata(bfiledata);
				System.out.println(board);
				
				//파일로 저장
				Blob blob = board.getBfiledata();
				if(blob != null) {
					InputStream is = blob.getBinaryStream();
					OutputStream os = new FileOutputStream("C:/Temp/" + board.getBfilename());
					is.transferTo(os);
					os.flush();
					os.close();
					is.close();
				}
		}
			rs.close();
			
			//PreparedStatement 닫기
			pstmt.close();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (conn !=null){
							try {
								//연결 끊기
								conn.close();
							}catch (SQLException e) {}
							}
			}
		}

	}

