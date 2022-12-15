package main.java.com.study.jdbc.main;


import java.sql.Connection;

import main.java.com.study.jdbc.util.DBConnection;

public class JdbcTest1 {

	public static void main(String[] args) {

//		System.out.println(Driver.class.getName());
//동일	System.out.println("com.mysql.cj.jdbc.Driver");  
		Connection connection = DBConnection.getInstance().getConnection();
		System.out.println(connection);
	}

}
