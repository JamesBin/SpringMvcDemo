package com.webapp.security.dao.impl;

import com.webapp.security.dao.UserDao;
import com.webapp.security.model.User;
import com.webapp.util.StringUtil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository("userDao")
public class UserDaoImpl implements UserDao{

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public User login(User user) {
		String sql="select * from t_user where userName=? and password=?";
		final User resultUser=new User();
		jdbcTemplate.query(sql, new Object[]{user.getUserName(),user.getPassword()}, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				resultUser.setId(rs.getInt("id"));
				resultUser.setUserName(rs.getString("userName"));
				resultUser.setPassword(rs.getString("password"));
				resultUser.setRoleName(rs.getString("roleName"));
			}
		});
		return resultUser;
	}

	@Override
	public int count(User s_user) {
		StringBuffer sb=new StringBuffer("select count(*) from t_user t1,t_department t2 where t1.deptId=t2.id");
		if(s_user!=null){
			if(StringUtil.isNotEmpty(s_user.getUserName())){
				sb.append(" and userName like '%"+s_user.getUserName()+"%'");
			}
		}
		return jdbcTemplate.queryForObject(sb.toString(), Integer.class);
	}

	@Override
	public void delete(int id) {
		String sql="delete from t_user where id=?";
		jdbcTemplate.update(sql, new Object[]{id});
	}

	@Override
	public void add(User user) {
		String sql="insert into t_user values(null,?,?,?,?,?)";
		jdbcTemplate.update(sql, new Object[]{user.getUserName(),user.getPassword(),user.getTrueName(),user.getRoleName(),user.getDeptId()});
	}

	@Override
	public void update(User user) {
		String sql="update t_user set userName=?,password=?,trueName=?,roleName=?,deptId=? where id=?";
		jdbcTemplate.update(sql, new Object[]{user.getUserName(),user.getPassword(),user.getTrueName(),user.getRoleName(),user.getDeptId(),user.getId()});
	}

	@Override
	public User loadById(int id) {
		String sql="select * from t_user t1,t_department t2 where t1.deptId=t2.id and t1.id=?";
		
		final User user=new User();
		jdbcTemplate.query(sql, new Object[]{id}, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				user.setId(rs.getInt("id"));
				user.setUserName(rs.getString("userName"));
				user.setPassword(rs.getString("password"));
				user.setTrueName(rs.getString("trueName"));
				user.setRoleName(rs.getString("roleName"));
				user.setDeptId(rs.getInt("deptId"));
				user.setDeptName(rs.getString("deptName"));
			}
		});
		return user;
	}

	@Override
	public boolean existUserByDeptId(int deptId) {
		String sql="select count(*) from t_user where deptId=?";
		int result=jdbcTemplate.queryForObject(sql,new Object[]{deptId},Integer.class);
		if(result>0){
			return true;
		}else{
			return false;			
		}
	}

}
