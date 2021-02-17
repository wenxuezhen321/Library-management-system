package dao;

import tools.JDbcUtil;
import vo.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BookDao {
//	public ArrayList<Book> queryBook(){
//		ArrayList<Book> list=new ArrayList<Book>();
//		JDbcUtil dbc = new JDbcUtil();
//		Connection con = dbc.getConnection();
//		try {
//
//			// 3.�������
//			String sql = "select * from book ";
//			PreparedStatement pst = con.prepareStatement(sql);
//			// 4.ִ�����
//			ResultSet rs = pst.executeQuery();
//			// 5.�������
//			while (rs.next()) {
//				Book book = new Book(rs.getString("bookID"), 
//						rs.getString("bookName"),
//						rs.getString("bookCategory"), 
//						rs.getString("bookIntroduction"));
//
//				list.add(book); // ���������ڼ�����
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			// 6.�ر�����
//			dbc.close();
//		}
//		
//		return list;
//	}
	
	public  Book get(String bookName){
		
		Book book = null;
		JDbcUtil dbc = new JDbcUtil();
		Connection con = dbc.getConnection();
		try {

			// 3.�������
			String sql = "select * from book where bookName=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, bookName);
			// 4.ִ�����
			ResultSet rs = pst.executeQuery();
			// 5.�������
			if(rs.next()) {
				book = new Book(rs.getString("bookID"), 
						rs.getString("bookName"),
						rs.getString("bookCategory"), 
						rs.getString("bookIntroduction"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 6.�ر�����
			dbc.close();
		}
		
		return book;
	}
}
