package com.os.comment.base;

import com.os.comment.handle.page.PageEntity;

import java.util.List;

/**
 * @author Administrator
 * 2015-5-12
 * @param <T>
 */
public interface BaseDao{
	
	/**
	 * 保存数据
	 * @param namespace mybatis命名空间
	 * @param t 保存的数据
	 * @return
	 */
	public abstract int insert(String namespace, Object obj);
	
	/**
	 * 查询单值
	 * @param namespace mybatis命名空间
	 * @param obj 传入的参数值
	 * @return
	 */
	public <T>T selectOne(String namespace, Object obj);
	
	/**
	 * 查询列表
	 * @param namespace mybatis命名空间
	 * @param map 查询条件
	 * @return
	 */
	public <T>List<T> selectList(String namespace, Object obj);
	
	/**
	 * 修改数据
	 * @param namespace  mybatis命名空间
	 * @param obj 修改的数据
	 * @return
	 */
	public int update(String namespace, Object obj);
	
	/**
	 * 删除数据
	 * @param namespace mybatis命名空间
	 * @param obj 删除的数据
	 * @return
	 */
	public int delete(String namespace, Object obj);
	/**
	 * 分页查询集合
	 * @param namespace
	 * @param obj
	 * @param page
	 * @return
	 */
	public <T>List<T> queryForPage(String namespace, Object obj, PageEntity page);
}
