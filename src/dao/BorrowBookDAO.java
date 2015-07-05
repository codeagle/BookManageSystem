package dao;

import java.util.ArrayList;

import vo.BorrowInfo;

public interface BorrowBookDAO {
	//��ѯȫ��ͼ����Ϣ
	public ArrayList findAllBorrowBookWithTeacher(BorrowInfo borrow,
			int readerno) throws Exception;

	public ArrayList findAllBorrowBookWithStudent(BorrowInfo borrow,
			int readerno) throws Exception;

	// ����ı��в�������--���Ĺ���
	public boolean insertBorrowBook(BorrowInfo borrow) throws Exception;

	// �黹ͼ��
	public boolean borrowBackById(BorrowInfo borrow, int readerno)
			throws Exception;
	// ����ͼ��
	public boolean renewBookById(BorrowInfo borrow, int readerno) throws Exception;

	// ����ͼ���Ƿ��������
	public ArrayList findBookRenewWithTeacher(BorrowInfo borrow,
			int readerno) throws Exception;

	public ArrayList  findBookRenewWithStudent(BorrowInfo borrow,
			int readerno) throws Exception;
	

}
