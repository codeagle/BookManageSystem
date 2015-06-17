package dao;

import java.util.ArrayList;

import vo.BorrowInfo;
import vo.ReaderInfo;

public interface BorrowReaderDAO {
	//查询全部借阅图书档案
	public ArrayList findAllBorrowReader(ReaderInfo readerinfo)throws Exception;
	//统计读者已借阅的图书数量
	public ReaderInfo countBorrowNumber(int readerid) throws Exception;
}
