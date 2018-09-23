package model;

import java.io.Serializable;

public class User implements Serializable {
	
	private static final long serialVersionUID = 2629983876059197650L;

	private long userNo;
	private String username;
	private String password;
	
	public long getUserNo() {
		return userNo;
	}
	public void setUserNo(long userNo) {
		this.userNo = userNo;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
