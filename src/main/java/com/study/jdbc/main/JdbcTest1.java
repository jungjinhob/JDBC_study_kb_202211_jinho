package main.java.com.study.jdbc.main;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.com.study.jdbc.util.DBConnection;

public class JdbcTest1 {

	public static void main(String[] args) {

//		System.out.println(Driver.class.getName());
//동일	System.out.println("com.mysql.cj.jdbc.Driver");  
		Connection connection = DBConnection.getInstance().getConnection(); //싱글톤 (데이터베이스 연결)
		
		String sql = "select * from score_mst"; //쿼리문 
		
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql); //연결된 db안에 sql 작성. 쿼리를 들고만 있는중.
			ResultSet rs = pstmt.executeQuery(); // 쿼리 실행. 리턴이 resultset(결과를 갖고 있는 set)
			//executeupdate 
			System.out.println("id\tname\t\tscore");
			
			while(rs.next()) { //rs.next가 false될때까지 반복.
										// sql변수에 저장되있는 데이터를 한줄씩 (rs) 꺼내서 (push 즉, 없어짐) 출력하다가 줄이 비어버리면 그만(false)
				System.out.println("id : " + rs.getInt(1) //1번칼럼 ~3번칼럼 (컬럼은 1번부터 시작)
				+ "\t name : " + rs.getString(2) 
				+ "\t score : " + rs.getInt(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
