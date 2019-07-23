package com.qst.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	static Connection con=null;
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");	
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection(){
		 try {
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/voting", "root", "123456");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	public static void closeConnection(Connection con){
		if(con!=null){
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		System.out.println(DBUtil.getConnection()+"%%%%%%%%%%%%%%%%");

	}

}
