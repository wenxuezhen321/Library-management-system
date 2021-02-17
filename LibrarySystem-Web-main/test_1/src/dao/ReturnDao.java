package dao;

import java.sql.Connection;
import java.sql.SQLException;

import tools.JDbcUtil;

public class ReturnDao {

	
	public boolean returnbook(String BookID,String userName){
		boolean success = false;
		
		JDbcUtil dbc = new JDbcUtil();
		Connection con = dbc.getConnection();
		
		
		try{
			//3.�������
			String sql= "delete from brh where BookID=?,userName=?";
			
			java.sql.PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, BookID);
			pst.setString(2, userName);
			
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
