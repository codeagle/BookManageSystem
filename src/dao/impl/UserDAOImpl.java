package dao.impl;
import java.sql.*;

import vo.User;
import dao.UserDAO;
import dao.dbc.DBConnection;
public class UserDAOImpl implements UserDAO {
	private  Connection conn = null;
    private PreparedStatement pstmt=null;
    private ResultSet rs=null;
    boolean flag = false;
    DBConnection db=new DBConnection();

	/** 
     * @���� �û���¼��Ԥ���뷽ʽ����ѯ��
     * @���� User
     * @����ֵ boolean��ֵ 
     */
	public boolean findLogin(User user) throws Exception {	
		try {
			conn=db.getConnection();
			String sql = "SELECT uname FROM Users WHERE uname=? AND upwd=?" ;
			pstmt = conn.prepareStatement(sql) ;
			pstmt.setString(1, user.getName()) ;
			pstmt.setString(2, user.getPwd()) ;
			rs = pstmt.executeQuery() ;
			if(rs.next()){
				flag = true ;
				//����
//				System.out.print(user.getUserid());
//			    System.out.print(user.getName());
//				System.out.print(user.getPwd());
			}
			 rs.close();
			 pstmt.close();
		} catch (Exception e) {	e.printStackTrace();			}
		finally {
			db.closed();
		}
		return flag;
	}

}
