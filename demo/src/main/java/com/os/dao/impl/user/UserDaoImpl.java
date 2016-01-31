package com.os.dao.impl.user;

import com.os.comment.base.BaseDaoImpl;
import com.os.comment.handle.page.PageEntity;
import com.os.dao.user.UserDao;
import com.os.entity.user.QueryUser;
import com.os.entity.user.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl implements UserDao {
	
	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		this.insert("UserMapper.createUser", user);
	}

	@Override
	public void deleteUserById(String ids) {
		// TODO Auto-generated method stub
		this.delete("UserMapper.deleteUserById", ids);
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		this.update("UserMapper.updateUser", user);
	}

	@Override
	public User getUserById(Long id) {
		return this.selectOne("UserMapper.getUserById", id);
	}

	@Override
	public List<User> getUserList(QueryUser queryUser) {
		// TODO Auto-generated method stub
		return this.selectList("UserMapper.getUserList", queryUser);
	}

	@Override
	public List<User> getUserListByIds(String ids) {
		// TODO Auto-generated method stub
		return this.selectList("UserMapper.getUserListByIds", ids);
	}

	@Override
	public List<User> queryUserPage(QueryUser query, PageEntity page) {
		// TODO Auto-generated method stub
		return this.queryForPage("UserMapper.queryUserPage", query, page);
	}
}
