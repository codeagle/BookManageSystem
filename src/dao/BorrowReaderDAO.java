package dao;

import java.util.ArrayList;

import vo.BorrowInfo;
import vo.ReaderInfo;

public interface BorrowReaderDAO {
	//��ѯȫ������ͼ�鵵��
	public ArrayList findAllBorrowReader(ReaderInfo readerinfo)throws Exception;
	//ͳ�ƶ����ѽ��ĵ�ͼ������
	public ReaderInfo countBorrowNumber(int readerid) throws Exception;
}
