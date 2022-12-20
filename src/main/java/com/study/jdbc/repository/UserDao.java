package main.java.com.study.jdbc.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import main.java.com.study.jdbc.entity.User;
import main.java.com.study.jdbc.util.DBConnectionMgr;

//@RequiredArgsConstructor
public class UserDao {
	
//	private final DBConnectionMgr pool;
	
//	public UserDao(DBConnectionMgr pool) {
//		this.pool = pool;
//	}

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
			sql = "insert into user_mst values(0, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			successCount = pstmt.executeUpdate();
			
//			rs = pstmt.getGeneratedKeys();
//			if(rs.next()) {
//				user.setUser_id(rs.getInt(1));
//			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		
		return successCount;
	}
	
	public User getUser(String username) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		User user = null;
		
		try {
			con = pool.getConnection();
			sql = "select id, username, name, email, phone from user_mst um left outer join user_dtl ud on(ud.id = um.id) where um.username = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				user = User.builder()
						.user_id(rs.getInt(1))
						.username(rs.getString(2))
						.name(rs.getString(3))
						.email(rs.getString(4))
						.phone(rs.getString(5))
						.build();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		
		return user;
		
	}
	
	public void getUserList() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		try {
			con = pool.getConnection();
			sql = "select * from user_mst um left outer join user_dtl ud on(ud.id = um.id) where um.username = ?";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				System.out.println(rs.getInt(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		
	}
	
}
