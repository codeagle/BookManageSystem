package dao;

import java.util.ArrayList;

import vo.BookInfo;
import vo.BorrowInfo;

public interface BorrowBookDAO {
	public ArrayList findAllBorrowBookWithTeacher(BorrowInfo borrow,int readerno)throws Exception;
	public ArrayList findAllBorrowBookWithStudent(BorrowInfo borrow,int readerno)throws Exception;
	//����ı��в�������
	public boolean insertBorrowBook(BorrowInfo borrow)throws Exception;
	public BookInfo findBookByISBN(String isbn)throws Exception;
	//�黹ͼ��
	public boolean backBookById(int borrowid,String backdate) throws Exception;
	//����ͼ��
	public boolean renewBookById(BorrowInfo borrow)throws Exception;
	//����ͼ���Ƿ��������
	public ArrayList findBorrowBookNoYes(BorrowInfo borrow,int readerno)throws Exception;
}
