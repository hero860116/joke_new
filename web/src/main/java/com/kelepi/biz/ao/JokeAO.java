package com.kelepi.biz.ao;

import com.kelepi.common.bean.KeyValue;
import com.kelepi.common.bean.Result;
import com.kelepi.dal.dataobject.JokeDO;
import com.kelepi.dal.queryobject.JokeQuery;

import java.util.List;


public interface JokeAO {
	
	/**
	 * 保存
	 * @param joke
	 */
	long save(JokeDO joke);
	
	/**
	 * 更新
	 * @param joke
	 */
	void update(JokeDO joke);
	
	/**
	 * 删除
	 * @param id
	 */
	void delete(long id);
	
	/**
	 * 根据主键获取
	 * @param id
	 * @return
	 */
	JokeDO getJoke(long id);
	
	/**
	 * 按模板查询
	 * @param joke
	 * @return
	 */
	List<JokeDO> getJokesByTemplate(JokeDO joke);
	
	/**
	 * 分页查询
	 * @return
	 */
	List<JokeDO> findJokesByQuery(JokeQuery jokeQuery);

    /**
     * 首页显示
     * @return
     */
    Result findJokes(JokeQuery jokeQuery);

    void addTopic(JokeDO jokeDO);

}
