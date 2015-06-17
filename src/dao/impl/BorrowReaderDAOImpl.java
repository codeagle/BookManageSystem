package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.BorrowInfo;
import vo.ReaderInfo;
import dao.BorrowReaderDAO;
import dao.dbc.DBConnection;

public class BorrowReaderDAOImpl implements BorrowReaderDAO {
	private PreparedStatement pstmt=null;
	private ResultSet rs =null;
	boolean flag=false;
	DBConnection dbc = new DBConnection();
	private Connection conn=dbc.getConnection();
	@Override
	public ArrayList findAllBorrowReader(ReaderInfo readerinfo) throws Exception {
		ArrayList allBorrowReader = new ArrayList();
		try {
			String sql ="select readerid,readername,rtypename,idcard,number,borrownumber from readerinfo  , readertype  where readerinfo.rtypeid=readertype.rtypeid and readerid=?";

			System.out.println("SQL÷–ªÒ»°readerid"+readerinfo.getReaderid());
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, readerinfo.getReaderid());	
			rs=pstmt.executeQuery();
			while(rs.next()){
				readerinfo = new ReaderInfo();
				readerinfo.setReaderid(rs.getInt("readerid"));
				readerinfo.setReadername(rs.getString("readername"));
				readerinfo.setRtypename(rs.getString("rtypename"));
				readerinfo.setIdcard(rs.getString("idcard"));
				readerinfo.setNumber(rs.getInt("number"));
				readerinfo.setBorrownumber(rs.getInt("borrownumber"));
				allBorrowReader.add(readerinfo);
			}
			rs.close();
			pstmt.close();
			dbc.closed();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allBorrowReader;
	}
	@Override
	public ReaderInfo countBorrowNumber(int readerid) throws Exception {
		ReaderInfo readerinfo = new ReaderInfo();
		try {
			String sql="select count(*) as borrownumber  from borrowinfo b,readerinfo r where b.readerid=r.readerid and b.readerid=? ";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, readerid);
			rs=pstmt.executeQuery();
			while(rs.next()){
				readerinfo = new ReaderInfo();
				readerinfo.setBorrownumber(rs.getInt("borrownumber"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return readerinfo;
	}



}
