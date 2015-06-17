package dao;

import java.util.ArrayList;

import vo.BookInfo;

public interface BookInfoDAO {
	//查询全部图书档案
	public ArrayList findAllBookInfo(BookInfo bookinfo)throws Exception;
	//添加图书档案信息
	public boolean doCreate(BookInfo bookinfo)throws Exception;
	//修改图书档案信息
	public boolean doUpdate(BookInfo bookinfo)throws Exception;
	//删除图书档案信息
	public boolean doDelete(int bookid)throws Exception;
	//根据指定bookid查询图书档案
	public BookInfo findBookInfoById(int bookid)throws Exception;
	public BookInfo findBookInfoByISBN(String isbn)throws Exception;
}
