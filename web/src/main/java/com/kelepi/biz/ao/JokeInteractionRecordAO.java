package com.kelepi.biz.ao;

import com.kelepi.common.bean.Result;
import com.kelepi.dal.dataobject.JokeInteractionRecordDO;

import java.util.List;


public interface JokeInteractionRecordAO {
	
	/**
	 * 保存
	 * @param jokeInteractionRecord
	 */
	long save(JokeInteractionRecordDO jokeInteractionRecord);
	
	/**
	 * 更新
	 * @param jokeInteractionRecord
	 */
	void update(JokeInteractionRecordDO jokeInteractionRecord);
	
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
	JokeInteractionRecordDO getJokeInteractionRecord(long id);
	
	/**
	 * 按模板查询
	 * @param jokeInteractionRecord
	 * @return
	 */
	List<JokeInteractionRecordDO> getJokeInteractionRecordsByTemplate(JokeInteractionRecordDO jokeInteractionRecord);
}
