package service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import dao.UserDao;
import model.User;

@Service
public class UserService{
		
	@Autowired
	private UserDao userDao;
	
	@Cacheable(value="myCache", key="'getABy'+#id")  
	public User getUserByUserNo(String userNo) {
		return userDao.getUserByUserNo(userNo);
	}
	
}
