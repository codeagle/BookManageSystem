package dao;

import java.util.ArrayList;

import vo.CaseInfo;
public interface CaseInfoDAO {
	//��ѯȫ���������
	public ArrayList findAllCaseInfo(CaseInfo bookcase)throws Exception;
	//��������Ϣ
	public boolean doCreate(CaseInfo bookcase)throws Exception;
	//�޸������Ϣ
	public boolean doUpdate(CaseInfo bookcase)throws Exception;
	//ɾ�����������Ϣ
	public boolean doDelete(int caseid)throws Exception;
	//����ָ��caseid��ѯ�����Ϣ
	public CaseInfo findCaseInfoById(int caseid)throws Exception;
}
