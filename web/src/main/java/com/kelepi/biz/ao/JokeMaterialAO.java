package com.kelepi.biz.ao;

import com.kelepi.dal.dataobject.JokeMaterialDO;
import com.kelepi.dal.queryobject.JokeMaterialQuery;

import java.util.List;

/**
 * User: liWeiLin
 * Date: 13-9-17 下午9:55
 */
public interface JokeMaterialAO {
    //name:jokeMaterial    Name:JokeMaterial

    /**
     * 保存
     * @param jokeMaterialDO
     */
    long save(JokeMaterialDO jokeMaterialDO);

    /**
     * 更新
     * @param jokeMaterialDO
     */
    void update(JokeMaterialDO jokeMaterialDO);

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
    JokeMaterialDO getJokeMaterial(long id);

    /**
     * 按模板查询
     * @param jokeMaterialDOQuery
     * @return
     */
    List<JokeMaterialDO> getJokeMaterialsByTemplate(JokeMaterialDO jokeMaterialDOQuery);

    /**
     * 分页查询
     * @return
     */
    List<JokeMaterialDO> findJokeMaterialsByQuery(JokeMaterialQuery jokeMaterialQuery);

    /**
     *
     * @param id
     */
    void reviewPass(long id);
}
