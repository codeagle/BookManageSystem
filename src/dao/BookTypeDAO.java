package dao;

import java.util.ArrayList;

import vo.BookType;

public interface BookTypeDAO {
	//查询全部图书类型
	public ArrayList findAllBookType(BookType booktype)throws Exception;
	//添加图书类型信息
	public boolean doCreate(BookType booktype)throws Exception;
	//修改图书类型信息
	public boolean doUpdate(BookType booktype)throws Exception;
	//删除图书类型信息
	public boolean doDelete(int typeid)throws Exception;
	//根据指定typeid查询图书类型信息
	public BookType findBookTypeById(int typeid)throws Exception;
}