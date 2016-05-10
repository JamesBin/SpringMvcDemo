package com.webapp.security.service;

import com.webapp.security.model.User;

public interface UserService {

	public User login(User user);
	
	public int count(User s_user);
	
	public void delete(int id);
	
	public void add(User user);
	
	public void update(User user);
	
	public User loadById(int id);
	
	public boolean existUserByDeptId(int deptId);
}
