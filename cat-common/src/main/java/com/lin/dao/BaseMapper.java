package com.lin.dao;

import java.util.List;

/**
 * dao操作接口，用于继承
 * @author hzh 2019-08-23
 */
public interface BaseMapper<M, T> {

	/**
	 * 根据id查询数据
	 * @param id 主键ID
	 * @return 返回实体
	 */
	 M findById(T id);

	/**
	 * 更新数据
	 * @param m 更新数据实体
	 * @return 返回更新记录数
	 */
	 int update(M m);

	/**
	 * 持久化
	 * @param m 持久化实体
	 * @return 返回持久化记录数
	 */
	 int insert(M m);

	/**
	 * 添加多条数据（直接添加到数据库 不做非空判断）
	 * @param list 数据列表
	 * @return 返回持久化记录数
	 */
	 int inserts(List<M> list);

	/**
	 * 根据主键ID删除数据
	 * @param id 主键ID
	 * @return 返回删除记录数
	 */
	 int delete(T id);
}
