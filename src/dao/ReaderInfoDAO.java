package dao;

import java.util.ArrayList;

import vo.ReaderInfo;


public interface ReaderInfoDAO {
	//查询全部读者类型
		public ArrayList findAllReaderInfo(ReaderInfo readerinfo)throws Exception;
		//添加读者类型
		public boolean doCreate(ReaderInfo readerinfo)throws Exception;
		//修改读者类型
		public boolean doUpdate(ReaderInfo readerinfo)throws Exception;
		//删除读者类型
		public boolean doDelete(int readerid)throws Exception;
		//根据指定readerid查询读者类型
		public ReaderInfo findReaderInfoById(int readerid)throws Exception;
		
}
