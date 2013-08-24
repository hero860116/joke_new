package com.kelepi.biz.ao;

import com.kelepi.dal.dataobject.PicMaterialDO;
import com.kelepi.dal.queryobject.PicMaterialQuery;

import java.util.List;

/**
 * User: liWeiLin
 * Date: 13-8-24 下午4:25
 */
public interface PicMaterialAO {
    //name:picMaterial    Name:PicMaterial

    /**
     * 保存
     * @param picMaterialDO
     */
    long save(PicMaterialDO picMaterialDO);

    /**
     * 更新
     * @param picMaterialDO
     */
    void update(PicMaterialDO picMaterialDO);

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
    PicMaterialDO getPicMaterial(long id);

    /**
     * 按模板查询
     * @param picMaterial
     * @return
     */
    List<PicMaterialDO> getPicMaterialsByTemplate(PicMaterialDO picMaterial);

    /**
     * 分页查询
     * @return
     */
    List<PicMaterialDO> findPicMaterialsByQuery(PicMaterialQuery picMaterialQuery);
}
