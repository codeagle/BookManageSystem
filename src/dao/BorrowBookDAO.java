package dao;

import java.util.ArrayList;

import vo.BorrowInfo;

public interface BorrowBookDAO {
	//查询全部图书信息
	public ArrayList findAllBorrowBookWithTeacher(BorrowInfo borrow,
			int readerno) throws Exception;

	public ArrayList findAllBorrowBookWithStudent(BorrowInfo borrow,
			int readerno) throws Exception;

	// 向借阅表中插入数据--借阅功能
	public boolean insertBorrowBook(BorrowInfo borrow) throws Exception;

	// 归还图书
	public boolean borrowBackById(BorrowInfo borrow, int readerno)
			throws Exception;
	// 续借图书
	public boolean renewBookById(BorrowInfo borrow, int readerno) throws Exception;

	// 查找图书是否可以续借
	public ArrayList findBookRenewWithTeacher(BorrowInfo borrow,
			int readerno) throws Exception;

	public ArrayList  findBookRenewWithStudent(BorrowInfo borrow,
			int readerno) throws Exception;
	

}
