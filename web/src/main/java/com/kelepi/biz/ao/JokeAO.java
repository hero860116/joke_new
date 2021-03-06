package com.kelepi.biz.ao;

import com.kelepi.common.bean.KeyValue;
import com.kelepi.common.bean.Result;
import com.kelepi.dal.dataobject.JokeDO;
import com.kelepi.dal.queryobject.JokeInteractionRecordQuery;
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

    /**
     * 获得审查joke
     *
     * @param several@return
     */
    Result getReviewJoke(int several);

    void addTopic(JokeDO jokeDO);

    /**
     * 好笑
     * @param id
     */
    void funnyJoke(long id);

    /**
     * 不好笑
     * @param id
     */
    void notFunnyJoke(long id);

    /**
     * 审查通过
     * @param id
     */
    void reviewPass(long id);

    /**
     * 禁止一个笑话
     * @param id
     */
    void forbidJoke(long id);

    /**
     * 顶
     * @param id
     */
    void topJoke(long id);

    /**
     * 踩
     * @param id
     */
    void downJoke(long id);

    /**
     * 获取用户顶过的笑话
     *
     * @param jokeInteractionRecordQuery@return
     */
    List<JokeDO> getTopJokeByQuery(JokeInteractionRecordQuery jokeInteractionRecordQuery);

    /**
     * 获得笑话详情
     * @return
     * @param jokeId
     */
    Result jokeDetail(long jokeId);

    /**
     * 获得下一个笑话
     * id小于
     * @return
     * @param jokeQuery
     */
    JokeDO getNextJoke(JokeQuery jokeQuery);

    /**
     * 获得上一个笑话
     * id小于
     * @return
     * @param jokeQuery
     */
    JokeDO getPreJoke(JokeQuery jokeQuery);

    void updateViewPermissions(long jokeId, int viewPermissionsType);

    /**
     * 设置推荐类别
     * @param recommendType
     * @param jokeId
     */
    void updateRecommendType(int recommendType, long jokeId);

    /**
     * 自动拼接生成图片
     * @param pics
     * @param dialogues
     * @param title
     * @param jokeCategory
     * @param tags
     */
    long buildJoke(String[] pics, String[] dialogues, String title, String serverHomeDir, int jokeCategory, String tags);
}
