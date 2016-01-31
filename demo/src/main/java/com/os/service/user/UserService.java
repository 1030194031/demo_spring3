package com.os.service.user;

import com.os.comment.handle.page.PageEntity;
import com.os.entity.user.QueryUser;
import com.os.entity.user.User;

import java.util.List;

/**
 * User管理接口 User: 
 */
public interface UserService {

	/**
	 * 创建user
	 * @param user
	 * @return
	 */
	public void addUser(User user);

	/**
	 * 根据id删除user
	 * @param id
	 */
	public void deleteUserById(String ids);

	/**
	 * 修改user
	 * @param user
	 */
	public void updateUser(User user);

	/**
	 * 获取单个user对象
	 * @param id
	 * @return
	 */
	public User getUserById(Long id);
	
	/**
	 * 查询列表
	 * @param map
	 * @return
	 */
	public List<User> getUserList(QueryUser queryUser);
	
	/**
	 * 根据id查询用户列表
	 * @param ids
	 * @return
	 */
	public List<User> getUserListByIds(String ids);
	/**
	 * 分页查询用户
	 * @param query
	 * @param page
	 * @return
	 */
	public List<User> queryUserPage(QueryUser query, PageEntity page);
}