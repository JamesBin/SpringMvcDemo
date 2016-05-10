package com.webapp.security.service.impl;

import com.webapp.security.dao.UserDao;
import com.webapp.security.model.User;
import com.webapp.security.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Resource
	private UserDao userDao;
	
	@Override
	public User login(User user) {
		return userDao.login(user);
	}

	@Override
	public int count(User s_user) {
		return userDao.count(s_user);
	}

	@Override
	public void delete(int id) {
		userDao.delete(id);
	}

	@Override
	public void add(User user) {
		userDao.add(user);
	}

	@Override
	public void update(User user) {
		userDao.update(user);
	}

	@Override
	public User loadById(int id) {
		return userDao.loadById(id);
	}

	@Override
	public boolean existUserByDeptId(int deptId) {
		return userDao.existUserByDeptId(deptId);
	}

}
