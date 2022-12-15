package main.java.com.study.jdbc.main.dml;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import main.java.com.study.jdbc.util.DBConnection;

public class JdbcSelect2 {

	public static void main(String[] args) {
		Connection connection = DBConnection.getInstance().getConnection();
		Scanner scanner = new Scanner(System.in);
		System.out.println("작성자 id: ");
		int writerId =scanner.nextInt(); 
		
		String sql = "select * from board_mst where writer_id = ?"; 
		
		PreparedStatement pstmt;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, writerId); //1은 첫번째 ? 의미
			ResultSet rs = pstmt.executeQuery(); //select때만 결과가 있으니까 select때만 필요
			
			System.out.println("id\ttitle\t\tcontent\t\t\tread_count\twriter_id");
			
			while(rs.next()) { 
				System.out.println("id : " + rs.getInt(1) 
				+ "\t title : " + rs.getString(2) 
				+ "\t content : " + rs.getString(3)
				+ "\t read_count " + rs.getInt(4)
				+ "\t writer_id " + rs.getInt(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
	}
	

}
