package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.BookInfo;
import vo.BorrowInfo;
import dao.BorrowBookDAO;
import dao.dbc.DBConnection;

public class BorrowBookDAOImpl implements BorrowBookDAO {
	private PreparedStatement pstmt=null;
	private ResultSet rs =null;
	boolean flag=false;
	DBConnection dbc = new DBConnection();
	private Connection conn=dbc.getConnection();
	
	//����ͼ��
	public boolean insertBorrowBook(BorrowInfo borrow) throws Exception {
		try {
			String sql="insert into borrowinfo(bookid,readerid,borrowdate,returndate)  values(?,?,?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, borrow.getBookid());
			pstmt.setInt(2, borrow.getReaderid() );
			pstmt.setString(3, borrow.getBorrowdate());
			pstmt.setString(4, borrow.getReturndate());			
			int count = pstmt.executeUpdate();
			String sql1="update bookinfo set nownumber=nownumber-1 where bookid=? ";
			pstmt=conn.prepareStatement(sql1);
			pstmt.setInt(1, borrow.getBookid());
			int count1=pstmt.executeUpdate();
			if(count >0&&count1>0){
				flag=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	//��ѯ��������δ�黹��ͼ��
	@Override
	public ArrayList findAllBorrowBookWithTeacher(BorrowInfo borrow,int readerno) throws Exception {
		ArrayList allBorrowBook = new ArrayList();
		try {
			String sql="select bri.bookid,readerid,CONVERT(varchar(20) , borrowdate, 111) as borrowdate,"
					+ " CONVERT(varchar(20) , returndate, 111 ) as returndate, nownumber,total,"
					+ "CONVERT(varchar(20) , borrowdate+60 ,111 ) as orderdate,bookname,"
					+ "renew,fine from borrowinfo bri,bookinfo bki where bri.bookid=bki.bookid "
					+ "and readerid=? and returndate is null";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, readerno);
			rs=pstmt.executeQuery();
			while(rs.next()){
				 borrow =new BorrowInfo();
				borrow.setBookid(rs.getInt("bookid"));
				borrow.setBookname(rs.getString("bookname"));
				borrow.setBorrowdate(rs.getString("borrowdate"));
				borrow.setOrderdate(rs.getString("orderdate"));
				borrow.setNownumber(rs.getInt("nownumber"));
				borrow.setTotal(rs.getInt("total"));
				allBorrowBook.add(borrow);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allBorrowBook;
	}
	//ͨ��ISBN����ͼ��
	@Override
	public BookInfo findBookByISBN(String isbn) throws Exception {
		BookInfo bookinfo = new BookInfo();
		try {
			String sql ="select * from bookinfo where isbn=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, isbn);
			rs=pstmt.executeQuery();
			while(rs.next()){
				bookinfo = new BookInfo();
				bookinfo.setBookname(rs.getString("bookname"));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookinfo;
	}
	//�黹ͼ��
	@Override
	public boolean backBookById(int borrowid,String backdate) throws Exception {
		try {
			String sql="update  Borrow set backdate=? where borrowid=? ";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, backdate);
			pstmt.setInt(2, borrowid);
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
	//
	/**
	 * ���谴ť���ܣ�ʵ�����蹦��,
	 * ʵ��ÿ�����ں���ʱ����ֻ��������һ��
	 * ���������״̬��Ϊ����
	 */
	@Override
	public boolean renewBookById(BorrowInfo borrow) throws Exception {
		try {
			String sql="update borrow set borrowdate=?,renew='��' where bookid=? and renew='��' ";		
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, borrow.getBorrowdate());
			pstmt.setInt(2, borrow.getBookid());
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

	/**
	 * ��ѯ�Ƿ���������ͼ�顣
	 * ���������� 
	 * 		������״̬Ϊ���ǡ�
	 * 		��δ�黹
	 *  	�۽���ʱ����30�ڣ���û�г��ڣ�
	 */		
	@Override
	public ArrayList findBorrowBookNoYes(BorrowInfo borrow, int readerno)
			throws Exception {
		ArrayList allBorrowBook = new ArrayList();
		try {
			String sql="select bi.bookname,bi.casename,borrowid,b.isbn,b.readerNo,b.readername,CONVERT(varchar(12) , borrowdate, 111) as borrowdate , CONVERT(varchar(12) , backdate, 111 ) as backdate,renew, CONVERT(varchar(12) , borrowdate+30 , 111 ) as orderdate "
					+ "from bookinfo bi,borrow b,readerinfo r where r.readerno=b.readerNo and backdate is  null"
					+ " and bi.isbn=b.isbn and renew='��' and b.readerNo=? and  DATEDIFF(dd,borrowdate,GETDATE())<=30";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, readerno);
			rs=pstmt.executeQuery();
			while(rs.next()){
				 borrow =new BorrowInfo();
				 borrow.setBookid(rs.getInt("borrowid"));
				borrow.setBorrowdate(rs.getString("borrowdate"));
				borrow.setOrderdate(rs.getString("orderdate"));
	//			borrow.setIsbn(rs.getString("isbn"));
		//		borrow.setCasename(rs.getString("casename"));
				allBorrowBook.add(borrow);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allBorrowBook;
	}
	@Override
	public ArrayList findAllBorrowBookWithStudent(BorrowInfo borrow,
			int readerno) throws Exception {
		ArrayList allBorrowBook = new ArrayList();
		try {
			String sql="select borrowinfo.bookid,readerid,CONVERT(varchar(12) , borrowdate, 111) as borrowdate ,"
					+ " CONVERT(varchar(12) , returndate, 111 ) as returndate, nownumber,total"
					+ "CONVERT(varchar(12) , borrowdate+30 , 111 ) as orderdate,bookname"
					+ "renew,fine from borrowinfo bri,bookinfo bki"
					+ "where bri.bookid=bki.bookid and readerid=? and returndate is null";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, readerno);
			rs=pstmt.executeQuery();
			while(rs.next()){
				 borrow =new BorrowInfo();
				borrow.setBookid(rs.getInt("bookid"));
				borrow.setBookname(rs.getString("bookname"));
				borrow.setBorrowdate(rs.getString("borrowdate"));
				borrow.setOrderdate(rs.getString("orderdate"));
				borrow.setNownumber(rs.getInt("nownumber"));
				borrow.setTotal(rs.getInt("total"));
				allBorrowBook.add(borrow);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allBorrowBook;
	}
	
}
