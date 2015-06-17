package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Publishing;
import vo.ReaderType;
import dao.ReaderTypeDAO;
import dao.dbc.DBConnection;

public class ReaderTypeDAOImpl implements ReaderTypeDAO {
	private PreparedStatement pstmt=null;
	private ResultSet rs =null;
	boolean flag=false;
	DBConnection dbc = new DBConnection();
	private Connection conn=dbc.getConnection();

	public ArrayList findAllReaderType(ReaderType readertype)
			throws Exception {
		ArrayList allReaderType = new ArrayList();
		try {
			String sql="select * from readertype";
			pstmt = conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				readertype=new ReaderType();
				readertype.setRtypeid(rs.getInt("rtypeid"));
				readertype.setRtypename(rs.getString("rtypename"));
				readertype.setNumber(rs.getInt("number"));
				allReaderType.add(readertype);
			}
			rs.close();
			pstmt.close();
			dbc.closed();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allReaderType;
	}

	@Override
	public boolean doCreate(ReaderType readertype) throws Exception {
		try {
			String sql="insert into readertype values(?,?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, readertype.getRtypeid());
			pstmt.setString(2, readertype.getRtypename());
			pstmt.setInt(3, readertype.getNumber());
			
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
	public boolean doUpdate(ReaderType readertype) throws Exception {
		try {
			//conn=dbc.getConnection();
			String sql="update readertype set rtypaname=?,number=? where rtypeid=?";		
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, readertype.getRtypename());
			pstmt.setInt(2, readertype.getNumber());
			pstmt.setInt(3, readertype.getRtypeid());
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
	public boolean doDelete(int rtypeid) throws Exception {
		try {
			conn=dbc.getConnection();
			String sql="delete from readertype where rtypeid=? ";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, rtypeid);
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
	public ReaderType findReaderTypeById(int rtypeid) throws Exception {
		
		ReaderType rt= new ReaderType();
		try {
			
			String sql="select * from readertype where rtypeid=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, rtypeid);
			rs=pstmt.executeQuery();
			if(rs.next()){
				rt.setRtypeid(rs.getInt("rtypeid"));
				rt.setRtypename(rs.getString("rtypename"));
				rt.setNumber(rs.getInt("number"));
				
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbc.closed();
		}
		
		return rt;
	}


}
