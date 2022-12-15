package main.java.com.study.jdbc.main.dml;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import main.java.com.study.jdbc.util.DBConnection;

public class JdbcUpdate1 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("수정할 계정 id값을 입력하세요");
		int id = scanner.nextInt();
		scanner.nextLine();
		System.out.print("수정할 아이디 입력: ");
		String username = scanner.nextLine();
		
		Connection con = DBConnection.getInstance().getConnection();
		String sql = "update user_mst set username = ? where id = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setInt(2, id);
			int successCount = pstmt.executeUpdate();
			System.out.println(successCount + "건 수정완료!");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
