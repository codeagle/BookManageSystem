package dao;

import java.util.ArrayList;

import vo.CaseInfo;
public interface CaseInfoDAO {
	//查询全部书架名称
	public ArrayList findAllCaseInfo(CaseInfo bookcase)throws Exception;
	//添加书架信息
	public boolean doCreate(CaseInfo bookcase)throws Exception;
	//修改书架信息
	public boolean doUpdate(CaseInfo bookcase)throws Exception;
	//删除书架类型信息
	public boolean doDelete(int caseid)throws Exception;
	//根据指定caseid查询书架信息
	public CaseInfo findCaseInfoById(int caseid)throws Exception;
}
