package dao;

import model.User;

public interface UserDao{
	
	public User getUserByUserNo(String userNo);
	
	public int updatePassword(String userNo, String password);
	
}
