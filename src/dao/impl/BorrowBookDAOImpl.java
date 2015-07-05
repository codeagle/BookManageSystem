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
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	boolean flag = false;
	DBConnection dbc = new DBConnection();
	private Connection conn = dbc.getConnection();

	// ����ͼ��
	public boolean insertBorrowBook(BorrowInfo borrow) throws Exception {
		try {
			//�ж�ͼ������Ƿ���ڸ�ͼ�飬������ִ�н��Ĳ��룬�����ڲ�ִ��
			Integer bookid = 0;
			String sql_check = "select * from bookinfo where bookid=?";
			pstmt = conn.prepareStatement(sql_check);
			pstmt.setInt(1, borrow.getBookid());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				bookid = rs.getInt("bookid");
			}
			if (bookid != 0) {
				String sql = "insert into borrowinfo(bookid,readerid,borrowdate,returndate)  values(?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, borrow.getBookid());
				pstmt.setInt(2, borrow.getReaderid());
				pstmt.setString(3, borrow.getBorrowdate());
				pstmt.setString(4, borrow.getReturndate());
				int count = pstmt.executeUpdate();
				// �鼮�ֿ��-1
				String sql1 = "update bookinfo set nownumber=nownumber-1 where bookid=? ";
				pstmt = conn.prepareStatement(sql1);
				pstmt.setInt(1, borrow.getBookid());
				int count1 = pstmt.executeUpdate();
				// ���߽�����+1
				String sql2 = "update readerinfo set borrownumber=(select count(*) from borrowinfo where readerid=?  and returndate is null) where readerid=?";
				pstmt = conn.prepareStatement(sql2);
				pstmt.setInt(1, borrow.getReaderid());
				pstmt.setInt(2, borrow.getReaderid());
				int count2 = pstmt.executeUpdate();
				if (count > 0 && count1 > 0 && count2 > 0) {
					flag = true;
				}
				pstmt.close();
				dbc.closed();
			}else{
				System.out.println("û�и�ͼ�飬���ɽ���");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	// ��ѯ��������δ�黹��ͼ��
	@Override
	public ArrayList findAllBorrowBookWithTeacher(BorrowInfo borrow,
			int readerno) throws Exception {
		ArrayList allBorrowBook = new ArrayList();
		try {
			String sql = "select id,bri.bookid,readerid,CONVERT(varchar(20) , borrowdate, 111) as borrowdate,"
					+ " CONVERT(varchar(20) , returndate, 111 ) as returndate, nownumber,total,"
					+ "CONVERT(varchar(20) , borrowdate+60 ,111 ) as orderdate,bookname, datediff(dd, borrowdate+60,getdate()) as overdate,"
					+ "renew,datediff(day, borrowdate+60,getdate()) *0.1 as fine from borrowinfo bri,bookinfo bki where bri.bookid=bki.bookid "
					+ "and readerid=? and returndate is null";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, readerno);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				borrow = new BorrowInfo();
				borrow.setId(rs.getInt("id"));
				borrow.setBookid(rs.getInt("bookid"));
				borrow.setBookname(rs.getString("bookname"));
				borrow.setBorrowdate(rs.getString("borrowdate"));
				borrow.setOrderdate(rs.getString("orderdate"));
				borrow.setFine(rs.getDouble("fine"));
				borrow.setOverdate(rs.getInt("overdate"));
				borrow.setNownumber(rs.getInt("nownumber"));
				borrow.setTotal(rs.getInt("total"));
				allBorrowBook.add(borrow);

			}
			rs.close();
			pstmt.close();
			dbc.closed();
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
			String sql = "select id, bri.bookid,readerid,CONVERT(varchar(20) , borrowdate, 111) as borrowdate,"
					+ " CONVERT(varchar(20) , returndate, 111 ) as returndate, nownumber,total,"
					+ "CONVERT(varchar(20) , borrowdate+30 ,111 ) as orderdate,bookname,datediff(d, borrowdate+30,getdate()) as overdate,"
					+ "renew,datediff(day, borrowdate+30,getdate()) *0.1 as fine from borrowinfo bri,bookinfo bki where bri.bookid=bki.bookid "
					+ "and readerid=? and returndate is null";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, readerno);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				borrow = new BorrowInfo();
				borrow.setId(rs.getInt("id"));
				borrow.setBookid(rs.getInt("bookid"));
				borrow.setBookname(rs.getString("bookname"));
				borrow.setBorrowdate(rs.getString("borrowdate"));
				borrow.setOrderdate(rs.getString("orderdate"));
				borrow.setFine(rs.getDouble("fine"));
				borrow.setOverdate(rs.getInt("overdate"));
				borrow.setNownumber(rs.getInt("nownumber"));
				borrow.setTotal(rs.getInt("total"));
				allBorrowBook.add(borrow);

			}
			rs.close();
			pstmt.close();
			dbc.closed();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allBorrowBook;
	}

	// �黹ͼ��
	@Override
	public boolean borrowBackById(BorrowInfo borrow, int readerno)
			throws Exception {
		try {
			String sql = "update borrowinfo set returndate=? where bookid=? and readerid=? and id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, borrow.getReturndate());
			pstmt.setInt(2, borrow.getBookid());
			pstmt.setInt(3, readerno);
			pstmt.setInt(4, borrow.getId()); 
			int count = pstmt.executeUpdate();
			pstmt.close();
			// �鼮�ֿ��+1
			String sql1 = "update bookinfo set nownumber=nownumber+1 where bookid=? ";
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, borrow.getBookid());
			int count1 = pstmt.executeUpdate();
			pstmt.close();
			// ͳ�ƶ��߽�����
			String sql2 = "update readerinfo set borrownumber=(select count(*) from borrowinfo where readerid=?  and returndate is null) where readerid=?";
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, borrow.getReaderid());
			pstmt.setInt(2, borrow.getReaderid());
			int count2 = pstmt.executeUpdate();
			if (count > 0 && count1 > 0 && count2 >=0) {
				flag = true;
			}
			pstmt.close();
			dbc.closed();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	//���裺ֻ��������һ��
	@Override
	public boolean renewBookById(BorrowInfo borrow, int readerno) throws Exception {
		try {
			String sql = "update borrowinfo set borrowdate=? ,renew=?  where bookid=? and readerid=? and id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, borrow.getReturndate());
			pstmt.setString(2,"��");
			pstmt.setInt(3, borrow.getBookid());
			pstmt.setInt(4, readerno);
			pstmt.setInt(5, borrow.getId()); 
			int count = pstmt.executeUpdate();
			if (count > 0 ) {
				flag = true;
			}
			pstmt.close();
			dbc.closed();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/*
	 * ��ѯ���裺
	 * ������1������δ�黹 2�����뵽��ʱ�仹��10��֮�ڿ�������
	 * */
	@Override
	public ArrayList findBookRenewWithTeacher(BorrowInfo borrow, int readerno)
			throws Exception {
		ArrayList allBorrowBook = new ArrayList();
		try {
			String sql = "select id,bri.bookid,readerid,CONVERT(varchar(20) , borrowdate, 111) as borrowdate,"
					+ " CONVERT(varchar(20) , returndate, 111 ) as returndate, nownumber,total,"
					+ "CONVERT(varchar(20) , borrowdate+60 ,111 ) as orderdate,bookname, datediff(dd, borrowdate+60,getdate()) as overdate,"
					+ "renew,datediff(day, borrowdate+60,getdate()) *0.1 as fine from borrowinfo bri,bookinfo bki where bri.bookid=bki.bookid "
					+ "and readerid=? and returndate is null and renew=? and datediff(day,borrowdate+60,getdate()) between -10 and 0 ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, readerno);
			pstmt.setString(2, "��");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				borrow = new BorrowInfo();
				borrow.setId(rs.getInt("id"));
				borrow.setBookid(rs.getInt("bookid"));
				borrow.setBookname(rs.getString("bookname"));
				borrow.setBorrowdate(rs.getString("borrowdate"));
				borrow.setOrderdate(rs.getString("orderdate"));
				borrow.setNownumber(rs.getInt("nownumber"));
				borrow.setFine(rs.getDouble("fine"));
				borrow.setOverdate(rs.getInt("overdate"));
				borrow.setTotal(rs.getInt("total"));
				allBorrowBook.add(borrow);
			}
			rs.close();
			pstmt.close();
			dbc.closed();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allBorrowBook;
	}

	@Override
	public ArrayList findBookRenewWithStudent(BorrowInfo borrow, int readerno)
			throws Exception {
		ArrayList allBorrowBook = new ArrayList();
		try {
			String sql = "select id,bri.bookid,readerid,CONVERT(varchar(20) , borrowdate, 111) as borrowdate,"
					+ " CONVERT(varchar(20) , returndate, 111 ) as returndate, nownumber,total,"
					+ "CONVERT(varchar(20) , borrowdate+30 ,111 ) as orderdate,bookname,datediff(day, borrowdate+30,getdate()) as overdate,"
					+ "renew ,datediff(day, borrowdate+60,getdate()) *0.1 as fine from borrowinfo bri,bookinfo bki where bri.bookid=bki.bookid "
					+ "and readerid=? and returndate is null and renew=? and  datediff(day,borrowdate+30,getdate()) between -10 and 0 ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, readerno);
			pstmt.setString(2, "��");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				borrow = new BorrowInfo();
				borrow.setId(rs.getInt("id"));
				borrow.setBookid(rs.getInt("bookid"));
				borrow.setBookname(rs.getString("bookname"));
				borrow.setBorrowdate(rs.getString("borrowdate"));
				borrow.setOrderdate(rs.getString("orderdate"));
				borrow.setNownumber(rs.getInt("nownumber"));
				borrow.setFine(rs.getDouble("fine"));
				borrow.setOverdate(rs.getInt("overdate"));
				borrow.setTotal(rs.getInt("total"));
				allBorrowBook.add(borrow);
			}
			rs.close();
			pstmt.close();
			dbc.closed();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allBorrowBook;
	}

	
	

}
