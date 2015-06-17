package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.CaseInfo;
import vo.Publishing;
import dao.PublishingDAO;
import dao.dbc.DBConnection;

public class PublishingDAOImpl implements PublishingDAO {
	private PreparedStatement pstmt=null;
	private ResultSet rs =null;
	boolean flag=false;
	DBConnection dbc = new DBConnection();
	private Connection conn=dbc.getConnection();

	public ArrayList findAllPublishing(Publishing publishing) throws Exception {
		ArrayList allPublishing = new ArrayList();
		try{
			String sql="select * from publishing";
			pstmt = conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				publishing=new Publishing();
				publishing.setPubid(rs.getInt("pubid"));
				publishing.setPubname(rs.getString("pubname"));
				allPublishing.add(publishing);
			}
			rs.close();
			pstmt.close();
			dbc.closed();
		}catch(Exception e){
			e.printStackTrace();
		}
		return allPublishing;
	}

	@Override
	public boolean doCreate(Publishing publishing) throws Exception {
		try {
			String sql="insert into publishing values(?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, publishing.getPubid());
			pstmt.setString(2, publishing.getPubname());
			
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
	public boolean doUpdate(Publishing publishing) throws Exception {
		try {
			//conn=dbc.getConnection();
			String sql="update publishing set pubname=? where pubid=?";		
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, publishing.getPubname());
			pstmt.setInt(2, publishing.getPubid());
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
	public boolean doDelete(int pubid) throws Exception {
		try {
			conn=dbc.getConnection();
			String sql="delete from publishing where pubid=? ";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, pubid);
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
	public Publishing findPublishingById(int pubid) throws Exception {
		Publishing ps= new Publishing();
		try {
			
			String sql="select * from publishing where pubid=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, pubid);
			rs=pstmt.executeQuery();
			if(rs.next()){
				ps.setPubid(rs.getInt("pubid"));
				ps.setPubname(rs.getString("pubname"));
				
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbc.closed();
		}
		
		return ps;
	}
	
	
}
