package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.ReaderInfo;
import vo.ReaderType;
import dao.ReaderInfoDAO;
import dao.dbc.DBConnection;

public class ReaderInfoDAOImpl implements ReaderInfoDAO {
	private PreparedStatement pstmt=null,pstmt1=null;
	private ResultSet rs =null;
	boolean flag=false;
	DBConnection dbc = new DBConnection();
	private Connection conn=dbc.getConnection();
	
	public ArrayList findAllReaderInfo(ReaderInfo readerinfo) throws Exception {
		ArrayList allReaderInfo = new ArrayList();
		try {
			String sql="select readerid ,readername,rtypename ,idcard,number,borrownumber from readerinfo ri,readertype rt  where ri.rtypeid=rt.rtypeid ";
			pstmt = conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				readerinfo=new ReaderInfo();
				readerinfo.setReaderid(rs.getInt("readerid"));
				readerinfo.setReadername(rs.getString("readername"));
				readerinfo.setRtypename(rs.getString("rtypename"));
				readerinfo.setIdcard(rs.getString("idcard"));
				readerinfo.setNumber(rs.getInt("number"));
				readerinfo.setBorrownumber(rs.getInt("borrownumber"));
				allReaderInfo.add(readerinfo);
			}
			rs.close();
			pstmt.close();
			dbc.closed();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 	allReaderInfo;
	}

	@Override
	public boolean doCreate(ReaderInfo readerinfo) throws Exception {
		try {
			String rtypename= readerinfo.getRtypename();
			String sql1="select rtypeid from readertype where rtypename='"+rtypename+"'";
			ReaderType  readertype =new ReaderType();
			pstmt=conn.prepareStatement(sql1);
			rs=pstmt.executeQuery();
			while(rs.next()){
				readertype =new ReaderType();
				readertype.setRtypeid(rs.getInt("rtypeid"));
			}
			int rtypeid=readertype.getRtypeid();
			rs.close();
			String sql="insert into readerinfo  values(?,?,?,?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, readerinfo.getReaderid());
			pstmt.setInt(3, rtypeid);
			pstmt.setString(2, readerinfo.getReadername());
			pstmt.setString(4, readerinfo.getIdcard());
			pstmt.setInt(5, readerinfo.getBorrownumber());
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
	public boolean doUpdate(ReaderInfo readerinfo) throws Exception {
		try {
			String rtypename= readerinfo.getRtypename();
			String sql1="select rtypeid from readertype where rtypename='"+rtypename+"'";
			ReaderType readertype =new ReaderType();
			pstmt1=conn.prepareStatement(sql1);
			int rtypeid=readertype.getRtypeid();
			//conn=dbc.getConnection();
			String sql="update readerinfo set rtypeid=?,readername=?,idcard=? where readerid=?";		
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, rtypeid);
			pstmt.setString(2, readerinfo.getReadername());
			pstmt.setString(3, readerinfo.getIdcard());
			pstmt.setInt(4, readerinfo.getReaderid());
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
	public boolean doDelete(int readerid) throws Exception {
		try {
			conn=dbc.getConnection();
			String sql="delete from readerinfo where readerid=? ";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, readerid);
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
	public ReaderInfo findReaderInfoById(int readerid) throws Exception {
		
		ReaderInfo readerinfo= new ReaderInfo();
		try {
			
			String sql="select readerid ,readername,rtypename ,idcard,number,borrownumber from readerinfo ri,readertype rt "
					+ "where ri.rtypeid=rt.rtypeid and readerid=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, readerid);
			rs=pstmt.executeQuery();
			if(rs.next()){			
				readerinfo.setReaderid(rs.getInt("readerid"));
				readerinfo.setReadername(rs.getString("readername"));
				readerinfo.setRtypename(rs.getString("rtypename"));
				readerinfo.setIdcard(rs.getString("idcard"));
				readerinfo.setNumber(rs.getInt("number"));
				readerinfo.setBorrownumber(rs.getInt("borrownumber"));		
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbc.closed();
		}
		
		return readerinfo;
	}


}
