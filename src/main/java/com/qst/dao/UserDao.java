package com.qst.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.qst.entiy.User;

public class UserDao {

	/**
	 *查看是否有此人
	 * @param id
	 * @return
	 */
	public static int selectByName(String id){
		int count=0;
		ResultSet rs = null;
		Connection conn = Basedao.getconn();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("select count(*) from vote_user where VU_USER_NAME=?");
			ps.setString(1, id);
			rs = ps.executeQuery();
			while(rs.next()){
				count=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Basedao.closeall(rs, ps, conn);
		}
		return count;
	}
	
	/**
	 *根据名称查找用户
	 */
	public static User selectByName1(String name){
		User user=null;
		ResultSet rs = null;
		Connection conn = Basedao.getconn();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("select * from vote_user where VU_USER_NAME=?");
			ps.setString(1, name);
			
			rs = ps.executeQuery();
			while(rs.next()){
				user = new User(rs.getString("VU_USER_ID"),
						rs.getString("VU_USER_NAME"), 
						rs.getString("VU_PASSWORD"),
						
						rs.getInt("VU_STATUS"),
						rs.getInt("VU_VERSION"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Basedao.closeall(rs, ps, conn);
		}
		return user;
	}
	/**
	 *验证用户名和密码
	 */
	public static int selectByNM(String name,String pwd){
		int count=0;
		ResultSet rs = null;
		Connection conn = Basedao.getconn();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("select count(*) from vote_user where VU_USER_NAME=? and VU_PASSWORD=?");
			ps.setString(1, name);
			ps.setString(2, pwd);
			rs = ps.executeQuery();
			while(rs.next()){
				count=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Basedao.closeall(rs, ps, conn);
		}
		return count;
	}
	/**
	 * 判断是普通用户还是管理员
	 * @param name
	 * @param pwd
	 * @return
	 */
	public static User selectAdmin(String name,String pwd){
		User user=null;
		ResultSet rs = null;
		Connection conn = Basedao.getconn();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("select * from vote_user where VU_USER_ID=? and VU_PASSWORD=?");
			ps.setString(1, name);
			ps.setString(2, pwd);
			rs = ps.executeQuery();
			while(rs.next()){
				user = new User(rs.getString("VU_USER_ID"),
						rs.getString("VU_USER_NAME"), 
						rs.getString("VU_PASSWORD"),
						
						rs.getInt("VU_STATUS"),
						rs.getInt("VU_VERSION"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Basedao.closeall(rs, ps, conn);
		}
		return user;
	}
	
	/**
	 * 
	 * 添加用户
	 * @return
	 */
	public static int insert(User u){
		String sql = "insert into vote_user values(?,?,?,?,?)";
		Object[] params = {u.getVU_USER_ID(),
							u.getVU_USER_NAME(),
							u.getVU_PASSWORD(),
							u.getVU_STATUS(),
						
							u.getVU_VERSION()};
		return Basedao.exectuIUD(sql, params);
	}
	

	
	public static int update(User u){
		String sql = "update vote_user set VU_USER_NAME=?," +
											"VU_PASSWORD=?," +
											"VU_STATUS=?," +
											
											"VU_VERSION=?," +
											
											"where VU_USER_ID=?";
		Object[] params = {u.getVU_USER_NAME(),
							u.getVU_PASSWORD(),
							u.getVU_STATUS(),
							u.getVU_VERSION(),
							u.getVU_USER_ID()
							};
		return Basedao.exectuIUD(sql, params);
	}
	
	public static int del(String id){
		String sql = "delete from vote_user where VU_USER_ID=? and VU_USER_ID!='admin'";
		Object[] params = {id};
		return Basedao.exectuIUD(sql, params);
	}
}
