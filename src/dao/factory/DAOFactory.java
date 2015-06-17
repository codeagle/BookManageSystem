package dao.factory;

import dao.BookInfoDAO;
import dao.BookTypeDAO;
import dao.BorrowBookDAO;
import dao.BorrowReaderDAO;
import dao.CaseInfoDAO;
import dao.PublishingDAO;
import dao.ReaderInfoDAO;
import dao.ReaderTypeDAO;
import dao.UserDAO;
import dao.impl.BookInfoDAOImpl;
import dao.impl.BookTypeDAOImpl;
import dao.impl.BorrowBookDAOImpl;
import dao.impl.BorrowReaderDAOImpl;
import dao.impl.CaseInfoDAOImpl;
import dao.impl.PublishingDAOImpl;
import dao.impl.ReaderInfoDAOImpl;
import dao.impl.ReaderTypeDAOImpl;
import dao.impl.UserDAOImpl;

public class DAOFactory {
    public static UserDAO getUserDAOInstance(){
    	return new UserDAOImpl();
    }
    public static BookTypeDAO getBookTypeDAOInstance(){
    	return new BookTypeDAOImpl();
    }
    public static CaseInfoDAO getCaseInfoDAOInstance(){
    	return new CaseInfoDAOImpl();
 }
    public static PublishingDAO getPublishingDAOInstance(){
    	return  new PublishingDAOImpl();
    }
    public static BookInfoDAO getBookInfoDAOInstance(){
    	return new BookInfoDAOImpl();
    }
    public static ReaderTypeDAO getReaderTypeDAOInstance(){
    	return new ReaderTypeDAOImpl();
    }
    public static ReaderInfoDAO getReaderInfoDAOInstance(){
    	return new ReaderInfoDAOImpl();
    }
   
    public static BorrowReaderDAO getBorrowReaderDAOInstance(){
    	return new BorrowReaderDAOImpl();
    }
    public static BorrowBookDAO getBorrowBookDAOInstance(){
    	return new  BorrowBookDAOImpl();
    }
    
}
