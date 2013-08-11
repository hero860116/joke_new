package com.kelepi.dal.dao;

import com.kelepi.dal.dataobject.JokeDO;
import com.kelepi.dal.queryobject.JokeQuery;

import java.util.List;


public interface JokeDAO {
	
	/**
	 * 保存
	 *
     * @param jokeDO
     * @return TODO
	 */
	long save(JokeDO jokeDO);
	
	/**
	 * 更新
     * @param jokeDO
     */
	void update(JokeDO jokeDO);
	
	/**
	 * 删除
	 * @param id
	 */
	void delete(long id);
	
	/**
	 * 主键获取
	 * @param id
	 * @return
	 */
	JokeDO getJoke(long id);
	
	/**
	 * 按模板查询
	 *
     * @param jokeDO
     * @return
	 */
	List<JokeDO> getJokeListByTemplate(JokeDO jokeDO);
	
	
	/**
	 * 分页查询
	 * @param jokeQuery
	 * @return
	 */
	List<JokeDO> findJokeListByQuery(JokeQuery jokeQuery);
}
