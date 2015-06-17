package dao;

import java.util.ArrayList;

import vo.BookInfo;
import vo.BorrowInfo;

public interface BorrowBookDAO {
	public ArrayList findAllBorrowBookWithTeacher(BorrowInfo borrow,int readerno)throws Exception;
	public ArrayList findAllBorrowBookWithStudent(BorrowInfo borrow,int readerno)throws Exception;
	//向借阅表中插入数据
	public boolean insertBorrowBook(BorrowInfo borrow)throws Exception;
	public BookInfo findBookByISBN(String isbn)throws Exception;
	//归还图书
	public boolean backBookById(int borrowid,String backdate) throws Exception;
	//续借图书
	public boolean renewBookById(BorrowInfo borrow)throws Exception;
	//查找图书是否可以续借
	public ArrayList findBorrowBookNoYes(BorrowInfo borrow,int readerno)throws Exception;
}
