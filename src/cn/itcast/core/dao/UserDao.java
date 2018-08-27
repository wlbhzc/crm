package cn.itcast.core.dao;

import cn.itcast.core.bean.Customer;
import cn.itcast.core.bean.User;

public interface UserDao {
  
	void register(User user);
	
	User login(String username);
	

	
}
