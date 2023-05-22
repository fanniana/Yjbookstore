package dao;

import java.util.List;

import entity.User;

public interface UserDao {
	
	public int isEmptyUsername(String username); //查询用户名是否已存在
	public String getPassword(String username); //根据用户名获取密码
	public int getState(String username); //获取用户状态（是否为管理员）	
	public void addUser(String username,String password,int state,String tel,String address);//增添新用户
	
	public User getSingleUser(String username);//根据用户名获取用户信息
	public List<User> getUser();//获取全部用户信息
	public void updateUserInfo(String username,String username1,String tel,String address);
	public boolean grantPerm(int uid,int state);//修改用户管理员权限
}
