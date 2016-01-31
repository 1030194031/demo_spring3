package com.os.service.impl.user;

import com.os.comment.handle.page.PageEntity;
import com.os.dao.user.UserDao;
import com.os.entity.user.QueryUser;
import com.os.entity.user.User;
import com.os.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		userDao.addUser(user);
	}

	@Override
	public void deleteUserById(String ids) {
		// TODO Auto-generated method stub
		userDao.deleteUserById(ids);
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		userDao.updateUser(user);
	}

	@Override
	public User getUserById(Long id) {
		// TODO Auto-generated method stub
		return userDao.getUserById(id);
	}

	@Override
	public List<User> getUserList(QueryUser queryUser) {
		// TODO Auto-generated method stub
		return userDao.getUserList(queryUser);
	}

	@Override
	public List<User> getUserListByIds(String ids) {
		// TODO Auto-generated method stub
		return userDao.getUserListByIds(ids);
	}

	@Override
	public List<User> queryUserPage(QueryUser query, PageEntity page) {
		// TODO Auto-generated method stub
		return userDao.queryUserPage(query, page);
	}
}
