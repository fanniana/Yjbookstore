package dao;

import java.util.ArrayList;
import java.util.List;

import entity.Book;

public interface BookDao {
	
	public List<Book> getAllBook();
	//获取“精品图书”，即管理员推荐的书
	public List<Book> getBook1();
	//获取销量前4的书
	public List<Book> getBook2();
	//获取推荐图书
	public List<Book> getBook3(int uid);	
	//查找某种类型的书
	public List<Book> findBookBytype(String type);
	//根据书名找书
	public Book findBookByName(String bname);
	//查找与bname同类型的书
	public List<Book> findBookSameType(String bname);
	//通过str找书
	public Book findBookByStr(String str);
	//删除一本图书
	public void deleteABook(int bid);
	
}
