package com.kelepi.dal.dao;

import com.kelepi.dal.dataobject.JokeDO;
import com.kelepi.dal.queryobject.JokeInteractionRecordQuery;
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

    /**
     * 根据序号查找一条review记录
     *
     * @param several
     * @param userId
     * @return
     */
    JokeDO findReviewJoke(int several, long userId);

    /**
     * 增加funnySize
     * @param addSize
     * @param id
     */
    void addFunnySize(int addSize, long id);

    /**
     * 增加不好笑size
     * @param addSize
     * @param id
     */
    void addNotFunnySize(int addSize, long id);

    /**
     * 增加顶的数量
     * @param addSize
     * @param id
     */
    void addTopSize(int addSize, long id);

    /**
     * 增加踩的数量
     * @param addSize
     * @param id
     */
    void addDownSize(int addSize, long id);

    /**
     * 修改笑话状态
     * @param status
     * @param id
     */
    void updateStatus(int status, long id);

    /**
     * 获取用户顶过的笑话
     *
     *
     * @param jokeInteractionRecordQuery@return
     */
    List<JokeDO> getTopJokeByUserId(JokeInteractionRecordQuery jokeInteractionRecordQuery);

    /**
     * 跟新查看权限
     * @param jokeId
     * @param viewPermissionsType
     */
    void updateViewPermissions(long jokeId, int viewPermissionsType);

    /**
     * 设置推荐类别
     * @param recommendType
     * @param jokeId
     */
   void updateRecommendType(int recommendType, long jokeId);
}
