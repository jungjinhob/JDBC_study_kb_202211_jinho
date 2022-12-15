package main.java.com.study.jdbc.main.dml;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.w3c.dom.NameList;

import main.java.com.study.jdbc.util.DBConnection;

public class JdbcInsert2 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		List<String> usernameList = new ArrayList<>();
		
		while(true) {
			System.out.println("등록할 아이디 입력");
			usernameList.add(scanner.nextLine());
			System.out.print("아이디를 추가로 등록하시겠습니까? (Y/y, 취소하려면 아무키나 입력하세요.)");
			String selected = scanner.nextLine();
			if(!"yY".contains(selected.isBlank() ?  "n" : selected)){ //아무것도 입력안했을때 방어적 코드
				break;
			}
		}
		
		Connection con = DBConnection.getInstance().getConnection();
		String prefixSql = "insert into user_mst values";
		String valuesBody = "";
		String suffixSql = ";";
		
		for(int i = 0 ; i< usernameList.size();i++) {
			valuesBody += "(0, ?)";
			if(i < usernameList.size() - 1) {
				valuesBody += ", ";
			}
		}
		System.out.println(valuesBody);
		
		try {
			PreparedStatement pstmt = con.prepareStatement(prefixSql + valuesBody + suffixSql);
			for(int i = 0 ; i < usernameList.size();i++) {
				pstmt.setString(i + 1, usernameList.get(i));
			}
			int successCount  = pstmt.executeUpdate(); 
			System.out.println(successCount + " 건 등록완료!");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		//쿼리문만 update나 delete 하면 그대로 실행됨
		
		
		
		
		
		
	}

}
