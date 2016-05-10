package com.webapp.security.dao;

import com.webapp.security.model.User;

public interface UserDao {

	public User login(User user);
	
	public int count(User s_user);
	
	public void delete(int id);
	
	public void add(User user);
	
	public void update(User user);
	
	public User loadById(int id);
	
	public boolean existUserByDeptId(int deptId);
}
