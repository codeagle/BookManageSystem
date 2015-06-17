package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.CaseInfo;
import dao.CaseInfoDAO;
import dao.dbc.DBConnection;

public class CaseInfoDAOImpl implements CaseInfoDAO {
	private PreparedStatement pstmt=null;
	private ResultSet rs =null;
	boolean flag=false;
	DBConnection dbc = new DBConnection();
	private Connection conn=dbc.getConnection();

	public ArrayList findAllCaseInfo(CaseInfo caseinfo) throws Exception {
		ArrayList allcaseinfo = new ArrayList();
		try {
			String sql="select * from caseinfo";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				caseinfo=new CaseInfo();
				caseinfo.setCaseid(rs.getInt("caseid"));
				caseinfo.setCname(rs.getString("cname"));
				allcaseinfo.add(caseinfo);
			}
			rs.close();
			pstmt.close();
			dbc.closed();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allcaseinfo;
	}

	@Override
	public boolean doCreate(CaseInfo caseinfo) throws Exception {
			try {
				String sql="insert into caseinfo values(?,?)";
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, caseinfo.getCaseid());
				pstmt.setString(2, caseinfo.getCname());
				
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
	public boolean doUpdate(CaseInfo caseinfo) throws Exception {
		try {
			//conn=dbc.getConnection();
			String sql="update caseinfo set cname=? where caseid=?";		
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, caseinfo.getCname());
			pstmt.setInt(2, caseinfo.getCaseid());
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
	public boolean doDelete(int caseid) throws Exception {
		try {
			conn=dbc.getConnection();
			String sql="delete from caseinfo where caseid=? ";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, caseid);
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
	public CaseInfo findCaseInfoById(int caseid) throws Exception {
		CaseInfo bc= new CaseInfo();
		try {
			
			String sql="select * from caseinfo where caseid=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, caseid);
			rs=pstmt.executeQuery();
			if(rs.next()){
				bc.setCaseid(rs.getInt("caseid"));
				bc.setCname(rs.getString("cname"));
				
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbc.closed();
		}
		
		return bc;
	}





}
