package main.java.com.study.jdbc.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import lombok.RequiredArgsConstructor;
import main.java.com.study.jdbc.entity.User;
import main.java.com.study.jdbc.util.DBConnectionMgr;

//@RequiredArgsConstructor
public class UserDao {
//	private final DBConnectionMgr pool;
	
//	public UserDao(DBConnectionMgr pool) {
//		this.pool = pool;
//	}
	
//위의 3줄을 requiredArgscontructor임
	private DBConnectionMgr pool;
	
	public UserDao() {
		pool = DBConnectionMgr.getInstance();
	}
	public int insertUser(User user) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int successCount = 0;
		
		try {
			con = pool.getConnection();
			sql = "insert into user_mst values(0,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getUsername()); //user에서 이름받아서 user_mst의 username에 넣는다.
			successCount = pstmt.executeUpdate();			
			rs = pstmt.getGeneratedKeys(); //키값
			if(rs.next()) {
				user.setUser_id(rs.getInt(1)); //키값을 user의 user_id에 세팅.
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con,pstmt,null); //객체 소멸 (메모리 해제 )
		}
		
		return successCount;
	}
}
