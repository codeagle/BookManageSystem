package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.*;
import dao.BookInfoDAO;
import dao.dbc.DBConnection;

public class BookInfoDAOImpl implements BookInfoDAO {
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	boolean flag = false;
	DBConnection dbc = new DBConnection();
	private Connection conn = dbc.getConnection();

	public ArrayList findAllBookInfo(BookInfo bookinfo) throws Exception {
		ArrayList allBookInfo = new ArrayList();
		try {
			String sql = "select bookid,bookname,author,price,isbn, nownumber,total,btypename,pubname,cname "
					+ "from bookinfo bi,caseinfo ci,publishing pub,booktype bt "
					+ "where bi.pubid=pub.pubid and bi.btypeid=bt.btypeid and bi.caseid=ci.caseid";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				bookinfo = new BookInfo();
				bookinfo.setBookid(rs.getInt("bookid"));
				bookinfo.setBookname(rs.getString("bookname"));
				bookinfo.setAuthor(rs.getString("author"));
				bookinfo.setCname(rs.getString("cname"));
				bookinfo.setIsbn(rs.getString("isbn"));
				bookinfo.setPrice(rs.getDouble("price"));
				bookinfo.setPubname(rs.getString("pubname"));
				bookinfo.setBtypename(rs.getString("btypename"));
				bookinfo.setNownumber(rs.getInt("nownumber"));
				bookinfo.setTotal(rs.getInt("total"));
				allBookInfo.add(bookinfo);
			}
			rs.close();
			pstmt.close();
			dbc.closed();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allBookInfo;
	}

	public boolean doCreate(BookInfo bookinfo) throws Exception {
		try {
			String pubname = bookinfo.getPubname();
			String cname = bookinfo.getCname();
			String btypename = bookinfo.getBtypename();
			int caseid = 0, pubid = 0, btypeid = 0;
			String sql1 = "select caseid,pubid,btypeid from caseinfo ci,publishing pub,booktype bt "
					+ "where cname='"
					+ cname
					+ "'and pubname='"
					+ pubname
					+ "'and btypename='" + btypename + "' ";
			pstmt = conn.prepareStatement(sql1);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				btypeid = rs.getInt("btypeid");
				caseid = rs.getInt("caseid");
				pubid = rs.getInt("pubid");
			}
			rs.close();
			String sql = "insert into BookInfo values(?,?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bookinfo.getBookid());
			pstmt.setString(2, bookinfo.getBookname());
			pstmt.setString(3, bookinfo.getAuthor());
			pstmt.setDouble(4, bookinfo.getPrice());
			pstmt.setString(5, bookinfo.getIsbn());
			pstmt.setInt(6, bookinfo.getTotal());
			pstmt.setInt(7, bookinfo.getTotal());
			pstmt.setInt(8, pubid);
			pstmt.setInt(9, btypeid);
			pstmt.setInt(10, caseid);
			int count = pstmt.executeUpdate();
			if (count > 0) {
				flag = true;
			}
			pstmt.close();
			dbc.closed();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public boolean doUpdate(BookInfo bookinfo) throws Exception {
		try {
			String pubname = bookinfo.getPubname();
			String cname = bookinfo.getCname();
			String btypename = bookinfo.getBtypename();
			BookType booktype = new BookType();
			CaseInfo caseinfo = new CaseInfo();
			Publishing pl = new Publishing();
			String sql1 = "select caseid from caseinfo where cname='" + cname
					+ "'";
			String sql2 = "select pubid from publishing where pubname='"
					+ pubname + "'";
			String sql3 = "select btypeid from booktype where btypename='"
					+ btypename + "'";
			pstmt = conn.prepareStatement(sql1);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				caseinfo = new CaseInfo();
				caseinfo.setCaseid(rs.getInt("caseid"));
			}
			pstmt = conn.prepareStatement(sql3);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				booktype = new BookType();
				booktype.setBtypeid(rs.getInt("btypeid"));
			}
			pstmt = conn.prepareStatement(sql2);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				pl = new Publishing();
				pl.setPubid(rs.getInt("pubid"));
			}
			rs.close();
			System.out.println(pl.getPubid());
			String sql = "update bookinfo set bookname=?,btypeid=?,author=?,pubid=?,isbn=?,price=?,caseid=?,nownumber=?,total=? "
					+ "where bookid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bookinfo.getBookname());
			pstmt.setInt(2, booktype.getBtypeid());
			pstmt.setString(3, bookinfo.getAuthor());
			pstmt.setInt(4, pl.getPubid());
			pstmt.setString(5, bookinfo.getIsbn());
			pstmt.setDouble(6, bookinfo.getPrice());
			pstmt.setInt(7, caseinfo.getCaseid());
			pstmt.setInt(8, bookinfo.getNownumber());
			pstmt.setInt(9, bookinfo.getTotal());
			pstmt.setInt(10, bookinfo.getBookid());
			int count = pstmt.executeUpdate();
			if (count > 0) {
				flag = true;
			}
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbc.closed();
		}
		return flag;
	}

	public boolean doDelete(int bookid) throws Exception {
		try {
			String sql = "delete from BookInfo where bookid=? ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bookid);
			int count = pstmt.executeUpdate();
			if (count > 0) {
				flag = true;
			}
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbc.closed();
		}
		return flag;
	}

	public BookInfo findBookInfoById(int bookid) throws Exception {
		BookInfo bookinfo = new BookInfo();
		try {
			String sql = "SELECT bookid,bookname,author,price,isbn, nownumber,total,btypename,pubname,cname "
					+ "FROM bookinfo bi,caseinfo ci,publishing pub,booktype bt"
					+ " WHERE bi.pubid=pub.pubid AND bi.btypeid=bt.btypeid AND bi.caseid=ci.caseid AND bookid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bookid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				bookinfo = new BookInfo();
				bookinfo.setBookid(rs.getInt("bookid"));
				bookinfo.setBookname(rs.getString("bookname"));
				bookinfo.setAuthor(rs.getString("author"));
				bookinfo.setCname(rs.getString("cname"));
				bookinfo.setIsbn(rs.getString("isbn"));
				bookinfo.setPrice(rs.getDouble("price"));
				bookinfo.setPubname(rs.getString("pubname"));
				bookinfo.setBtypename(rs.getString("btypename"));
				bookinfo.setNownumber(rs.getInt("nownumber"));
				bookinfo.setTotal(rs.getInt("total"));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbc.closed();
		}

		return bookinfo;
	}

	@Override
	public BookInfo findBookInfoByISBN(String isbn) throws Exception {
		BookInfo bookinfo = new BookInfo();
		try {
			String sql = "SELECT bookid,bookname,author,price,isbn, nownumber,total,btypename,pubname,cname "
					+ "FROM bookinfo bi,caseinfo ci,publishing pub,booktype bt"
					+ " WHERE bi.pubid=pub.pubid AND bi.btypeid=bt.btypeid AND bi.caseid=ci.caseid AND isbn=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, isbn);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				bookinfo = new BookInfo();
				bookinfo.setBookid(rs.getInt("bookid"));
				bookinfo.setBookname(rs.getString("bookname"));
				bookinfo.setAuthor(rs.getString("author"));
				bookinfo.setCname(rs.getString("cname"));
				bookinfo.setIsbn(rs.getString("isbn"));
				bookinfo.setPrice(rs.getDouble("price"));
				bookinfo.setPubname(rs.getString("pubname"));
				bookinfo.setBtypename(rs.getString("btypename"));
				bookinfo.setNownumber(rs.getInt("nownumber"));
				bookinfo.setTotal(rs.getInt("total"));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbc.closed();
		}

		return bookinfo;
	}

	/**
	 * 分权限后，读者部分的图书信息查询，采用模糊查询方式
	 */
	@Override
	public ArrayList findBookInfoByIndistinct(BookInfo bookinfo, String bookname)
			throws Exception {
		ArrayList allBookInfo = new ArrayList();
		try {
			String sql = "select bookid,bookname,author,price,isbn, nownumber,total,btypename,pubname,cname "
					+ "from bookinfo bi,caseinfo ci,publishing pub,booktype bt "
					+ "where bi.pubid=pub.pubid and bi.btypeid=bt.btypeid and bi.caseid=ci.caseid and bookname like '%"+ bookname + "%'";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				bookinfo = new BookInfo();
				bookinfo.setBookid(rs.getInt("bookid"));
				bookinfo.setBookname(rs.getString("bookname"));
				bookinfo.setAuthor(rs.getString("author"));
				bookinfo.setCname(rs.getString("cname"));
				bookinfo.setIsbn(rs.getString("isbn"));
				bookinfo.setPrice(rs.getDouble("price"));
				bookinfo.setPubname(rs.getString("pubname"));
				bookinfo.setBtypename(rs.getString("btypename"));
				bookinfo.setNownumber(rs.getInt("nownumber"));
				bookinfo.setTotal(rs.getInt("total"));
				allBookInfo.add(bookinfo);
			}
			rs.close();
			pstmt.close();
			dbc.closed();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allBookInfo;
	}

	@Override
	public ArrayList borrowRankingList(BookInfo bookinfo) throws Exception {
		ArrayList allRankingList= new ArrayList();
		try {
			String sql="select bi.bookid,bi.bookname,bt.btypename,nownumber,ci.cname,pl.pubname,bi.author,price,count(bw.bookid) as times "
					+ "from borrowinfo bw,bookinfo bi,booktype bt,publishing pl,caseinfo ci  "
					+ "where bi.bookid=bw.bookid and bt.btypeid=bi.btypeid"
					+ " and ci.caseid=bi.caseid and pl.pubid=bi.pubid group by bi.bookid,nownumber,bi.bookname,bt.btypename,ci.cname,pl.pubname,bi.author,price "
					+ "order by times desc";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				bookinfo = new BookInfo();
				bookinfo.setBookid(rs.getInt("bookid"));
				bookinfo.setBookname(rs.getString("bookname"));
				bookinfo.setBtypename(rs.getString("btypename"));
				bookinfo.setNownumber(rs.getInt("nownumber"));
				bookinfo.setCname(rs.getString("cname"));
				bookinfo.setPubname(rs.getString("pubname"));
				bookinfo.setPrice(rs.getDouble("price"));
				bookinfo.setAuthor(rs.getString("author"));
				bookinfo.setTimes(rs.getInt("times"));
				allRankingList.add(bookinfo);
			}
			rs.close();
			pstmt.close();
			dbc.closed();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allRankingList;
	}


}
