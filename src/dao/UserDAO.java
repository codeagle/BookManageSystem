package dao;

import vo.User;

public interface UserDAO {
	//µÇÂ¼
	public boolean findLogin(User user) throws Exception ;
	//ĞŞ¸ÄÃÜÂë
	public boolean updatePwd(User user)throws Exception;
}

