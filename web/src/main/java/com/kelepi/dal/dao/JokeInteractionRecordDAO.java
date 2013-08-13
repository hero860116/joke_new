package com.kelepi.dal.dao;

import com.kelepi.dal.dataobject.JokeInteractionRecordDO;

import java.util.List;

/**
 * User: liWeiLin
 * Date: 13-8-13 下午10:18
 */
public interface JokeInteractionRecordDAO {

    /**
     * 保存
     *
     * @param jokeInteractionRecordDO
     * @return TODO
     */
    long save(JokeInteractionRecordDO jokeInteractionRecordDO);

    /**
     * 更新
     * @param jokeInteractionRecordDO
     */
    void update(JokeInteractionRecordDO jokeInteractionRecordDO);

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
    JokeInteractionRecordDO getJokeInteractionRecord(long id);

    /**
     * 按模板查询
     *
     * @param jokeInteractionRecordDO
     * @return
     */
    List<JokeInteractionRecordDO> getJokeInteractionRecordListByTemplate(JokeInteractionRecordDO jokeInteractionRecordDO);
}
