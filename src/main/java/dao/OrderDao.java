package dao;

import java.util.List;

import entity.Order;
import entity.OrderDetail;
import entity.User;

public interface OrderDao {
	
	public List<Order> getOrderAll();
	
	public List<Order> getOrderAllToDo();//获取所有需要处理的订单
	
	public List<Order> getOrderPerson(int uid);
	
	public int addNewOrder();//添加新订单，返回订单编号
	
	public void addOrderDetail(int oid,int uid,int bid);
	
	public void payMoney(int oid);//用户支付某笔订单
	
	public void shipped(int oid);//商家发货
	
	public Order getAOrder(int oid);//根据oid获取一个订单的大致信息
	
	public User getUserByOid(int oid);
	
	public List<OrderDetail> getOrderDetail(int oid);
	
}
