package cn.itcast.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.core.bean.User;
import cn.itcast.core.dao.UserDao;
import cn.itcast.core.service.UserService;


@Service
@Transactional
public class UserServiceImpl implements UserService {

	
	@Autowired
	private UserDao userdao;
	
	
	@Override
	public void register(User user) {
		// TODO Auto-generated method stub
       userdao.register(user);
	}
	
	@Override
	public User login(String username,String userpassword) {
		 User user = userdao.login(username);
		 if(user != null && user.getUserpassword().equals(userpassword)) {
			 return user;
		 }
		 
		 return null;
	}


	

}
