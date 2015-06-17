package dao;

import java.util.ArrayList;

import vo.ReaderType;

public interface ReaderTypeDAO {
	//查询全部读者类型
	public ArrayList findAllReaderType(ReaderType readertype)throws Exception;
	//添加读者类型
	public boolean doCreate(ReaderType readertype)throws Exception;
	//修改读者类型
	public boolean doUpdate(ReaderType readertype)throws Exception;
	//删除读者类型
	public boolean doDelete(int rtypeid)throws Exception;
	//根据指定rtypeid查询读者类型
	public ReaderType findReaderTypeById(int rtypeid)throws Exception;
}
