package dao;

import java.util.List;

import entity.ShoppingCart;

public interface ShoppingCartDao {
	
	public void addNewRecord(int uid,int bid);//添加购物车新记录
	
	public List<ShoppingCart> personalCart(int uid);//查找某个用户的购物车信息
	
	public void deleteitem(int uid,int bid);//删除一条购物车记录
	
}
