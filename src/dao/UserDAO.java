package dao;

import vo.User;

public interface UserDAO {
	//��¼
	public boolean findLogin(User user) throws Exception ;
	//�޸�����
	public boolean updatePwd(User user)throws Exception;
}

