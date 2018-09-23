package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import dao.UserDao;
import model.User;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;

	@Cacheable(value="user", key="#userNo")
	public User getUserByUserNo(String userNo) {
		return userDao.getUserByUserNo(userNo);
	}
	
	@CachePut(value="user", key="#user.userNo")
	public int insertUser(User user) {
		return 1;
	}
	
	@CacheEvict(value="user", key="#user.userNo")
	public int deleteUser(User user) {
		return 1;
	}
		
}
