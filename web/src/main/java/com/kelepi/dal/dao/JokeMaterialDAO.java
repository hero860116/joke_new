package com.kelepi.dal.dao;

import com.kelepi.dal.dataobject.JokeMaterialDO;
import com.kelepi.dal.queryobject.JokeMaterialQuery;

import java.util.List;

/**
 * User: liWeiLin
 * Date: 13-9-17 下午9:50
 */
public interface JokeMaterialDAO {
      // jokeMaterial  JokeMaterial
      /**
       * 保存
       *
       * @param jokeMaterialDO
       * @return
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
       * 主键获取
       * @param id
       * @return
       */
      JokeMaterialDO getJokeMaterial(long id);

      /**
       * 按模板查询
       *
       * @param jokeMaterialDO
       * @return
       */
      List<JokeMaterialDO> getJokeMaterialListByTemplate(JokeMaterialDO jokeMaterialDO);


      /**
       * 分页查询
       * @param jokeMaterialQuery
       * @return
       */
      List<JokeMaterialDO> findJokeMaterialsByQuery(JokeMaterialQuery jokeMaterialQuery);

    /**
     * 更新笑话素材的状态
     * @param status
     * @param id
     */
      void updateStatus(int status, long id);
}
