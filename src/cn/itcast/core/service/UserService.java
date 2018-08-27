package cn.itcast.core.service;

import cn.itcast.core.bean.User;

public interface UserService {

	public void register(User user);
	
	public  User login(String username,String userpassword);


	
}
