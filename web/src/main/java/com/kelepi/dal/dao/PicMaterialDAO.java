package com.kelepi.dal.dao;

import com.kelepi.dal.dataobject.PicMaterialDO;
import com.kelepi.dal.queryobject.PicMaterialQuery;

import java.util.List;

/**
 * User: liWeiLin
 * Date: 13-8-24 下午3:59
 */
public interface PicMaterialDAO {
    // picMaterial  PicMaterial

    /**
     * 保存
     *
     * @param picMaterialDO
     * @return TODO
     */
    long save(PicMaterialDO picMaterialDO);

    /**
     * 更新
     *
     * @param picMaterialDO
     */
    void update(PicMaterialDO picMaterialDO);

    /**
     * 删除
     *
     * @param id
     */
    void delete(long id);

    /**
     * 主键获取
     *
     * @param id
     * @return
     */
    PicMaterialDO getPicMaterial(long id);

    /**
     * 按模板查询
     *
     * @param picMaterialDO
     * @return
     */
    List<PicMaterialDO> getPicMaterialListByTemplate(PicMaterialDO picMaterialDO);


    /**
     * 分页查询
     *
     * @param picMaterialQuery
     * @return
     */
    List<PicMaterialDO> findPicMaterialsByQuery(PicMaterialQuery picMaterialQuery);
}
