package dao.impl;

import java.awt.print.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.BookType;
import dao.BookTypeDAO;
import dao.dbc.DBConnection;

public class BookTypeDAOImpl implements BookTypeDAO {
	private PreparedStatement pstmt=null;
	private ResultSet rs =null;
	boolean flag=false;
	DBConnection dbc = new DBConnection();
	private Connection conn=dbc.getConnection();
	public ArrayList findAllBookType(BookType booktype) throws Exception {
		ArrayList allBookType = new ArrayList();
		try {
			
			String sql="select * from booktype";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				booktype = new BookType();
				booktype.setBtypeid(rs.getInt("btypeid"));
				booktype.setBtypename(rs.getString("btypename"));
				allBookType.add(booktype);
			}
			rs.close();
			pstmt.close();
			dbc.closed();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allBookType;
	}

	@Override
	public boolean doCreate(BookType booktype) throws Exception {
		try {
			conn=dbc.getConnection();
			String sql="insert into BookType values(?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,booktype.getBtypeid());
			pstmt.setString(2, booktype.getBtypename());
			
			int count=pstmt.executeUpdate();
			if(count>0){
				flag=true;
			}
			pstmt.close();
			dbc.closed();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean doUpdate(BookType booktype) throws Exception {
		try {
			//conn=dbc.getConnection();
			String sql="update booktype set btypename=? where btypeid=?";		
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, booktype.getBtypename());
			pstmt.setInt(2, booktype.getBtypeid());
			int count=pstmt.executeUpdate();
			if(count>0){
				flag=true;
			}
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbc.closed();
		}
		return flag;
	}

	@Override
	public boolean doDelete(int typeid) throws Exception {
		try {
			conn=dbc.getConnection();
			String sql="delete from BookType where btypeid=? ";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, typeid);
			int count=pstmt.executeUpdate();
			if(count>0){
				flag=true;
			}
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbc.closed();
		}
		return flag;
	}

	@Override
	public BookType findBookTypeById(int typeid) throws Exception {
		BookType by = new BookType();
		try {
			
			String sql="select * from booktype where btypeid=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, typeid);
			rs=pstmt.executeQuery();
			if(rs.next()){
				by.setBtypeid(rs.getInt("btypeid"));
				by.setBtypename(rs.getString("btypename"));
				
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbc.closed();
		}
		
		return by;
	}

}
