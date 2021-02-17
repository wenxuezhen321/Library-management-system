package dao;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



import tools.JDbcUtil;
import vo.User;


public class UserDao {
	public User get(String userName) {
		User user = null;
		
		JDbcUtil dbc = new JDbcUtil();
		Connection con = dbc.getConnection();

		try{
			//3.�������
			String sql= "select * from t_user where username=?";
			java.sql.PreparedStatement pst = con.prepareStatement(sql);
			
			pst.setString(1, userName);
			//4��ִ�����
			ResultSet rs = pst.executeQuery();
			//5���������
			if(rs.next()){
				user=new User(rs.getString("userName"),
						rs.getString("password")
						,rs.getString("chrName"));
			}
			//6.�ر�����
			dbc.close();
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return user;
		
	}
	//�޸�����
	public boolean update(User user) {
		boolean success = false;

		JDbcUtil dbc = new JDbcUtil();
		Connection con = dbc.getConnection();
		try {

			// 3.�������
			String sql = "update t_user set password=?";
			sql += " where userName =? ";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, user.getPassword());
			
			// 4.ִ�����
			int i = pst.executeUpdate();
			// 5.�������
			if (i > 0) {
				success = true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 6.�ر�����
			dbc.close();
		}

		return success;

	}
	public boolean changepassword(String password,String userName){
		
		boolean success = false;
		
		JDbcUtil dbc = new JDbcUtil();
		Connection con = dbc.getConnection();
		
		try{
			//3.�������
			String sql= "update t_user set password=?";
				sql	+= "where userName=?";
			java.sql.PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, password);
			pst.setString(2,userName);
			
			//4��ִ�����
			int i = pst.executeUpdate();
			
			//5���������
			if(i>0){
				success = true;
			}
		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 6.�ر�����
			dbc.close();
		}

		return success;

	}

}
