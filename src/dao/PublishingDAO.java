package dao;

import java.util.ArrayList;

import vo.Publishing;

public interface PublishingDAO {
	//查询全部书架名称
	public ArrayList findAllPublishing(Publishing publishing)throws Exception;
	//添加书架信息
	public boolean doCreate(Publishing publishing)throws Exception;
	//修改书架信息
	public boolean doUpdate(Publishing publishing)throws Exception;
	//删除书架类型信息
	public boolean doDelete(int pubid)throws Exception;
	//根据指定caseid查询书架信息
	public Publishing findPublishingById(int pubid)throws Exception;
}
