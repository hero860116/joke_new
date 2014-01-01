package com.kelepi.biz.ao.impl;

import com.kelepi.biz.ao.BaseAO;
import com.kelepi.biz.ao.PicMaterialAO;
import com.kelepi.common.bean.ParamInstance;
import com.kelepi.dal.constants.JokeConstants;
import com.kelepi.dal.dao.CategoryExtensionDAO;
import com.kelepi.dal.dao.PicMaterialDAO;
import com.kelepi.dal.dataobject.CategoryDO;
import com.kelepi.dal.dataobject.CategoryExtensionDO;
import com.kelepi.dal.dataobject.PicMaterialDO;
import com.kelepi.dal.enums.MainStatus;
import com.kelepi.dal.queryobject.PicMaterialQuery;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * User: liWeiLin
 * Date: 13-8-24 下午4:26
 */
@Component
public class PicMaterialAOImpl extends BaseAO implements PicMaterialAO{
    // name:picMaterial   Name:PicMaterial

    @Resource
    private PicMaterialDAO picMaterialDAO;

    @Resource
    private CategoryExtensionDAO categoryExtensionDAO;

    public long save(PicMaterialDO picMaterialDO) {
        picMaterialDO.setStatus(MainStatus.TO_REVIEW.getType());

        CategoryDO seriesCategoryDO = ParamInstance.getCategory(picMaterialDO.getSeriesId());
        CategoryDO jokeSeriesCategoryDO = ParamInstance.getCategory(picMaterialDO.getRoleId());

        picMaterialDO.setSeriesName(seriesCategoryDO.getName());
        picMaterialDO.setSeriesId(seriesCategoryDO.getId());
        picMaterialDO.setRoleName(jokeSeriesCategoryDO.getName());
        picMaterialDO.setRoleId(jokeSeriesCategoryDO.getId());
        Map<String, String> nameValueMap = categoryExtensionDAO.getCategoryExtensionMap(jokeSeriesCategoryDO.getId());
        picMaterialDO.setActorName(nameValueMap.get("actorName"));

        return picMaterialDAO.save(picMaterialDO);
    }

    public void update(PicMaterialDO picMaterialDO) {

        PicMaterialDO srcPicMaterialDO = picMaterialDAO.getPicMaterial(picMaterialDO.getId());

        CategoryDO jokeSeriesCategoryDO = ParamInstance.getCategory(picMaterialDO.getRoleId());

        srcPicMaterialDO.setRoleName(jokeSeriesCategoryDO.getName());
        srcPicMaterialDO.setRoleId(jokeSeriesCategoryDO.getId());
        Map<String, String> nameValueMap = categoryExtensionDAO.getCategoryExtensionMap(jokeSeriesCategoryDO.getId());
        srcPicMaterialDO.setActorName(nameValueMap.get("actorName"));
        srcPicMaterialDO.setFace(picMaterialDO.getFace());
        srcPicMaterialDO.setScene(picMaterialDO.getScene());

        picMaterialDAO.update(srcPicMaterialDO);
    }

    public void delete(long id) {
        picMaterialDAO.delete(id);
    }

    public PicMaterialDO getPicMaterial(long id) {
        return picMaterialDAO.getPicMaterial(id);
    }

    public List<PicMaterialDO> getPicMaterialsByTemplate(PicMaterialDO picMaterialDOQuery) {
        return picMaterialDAO.getPicMaterialListByTemplate(picMaterialDOQuery);
    }

    public List<PicMaterialDO> findPicMaterialsByQuery(PicMaterialQuery picMaterialQuery) {
        picMaterialQuery.setFirstOrder("gmtCreate");
        picMaterialQuery.setFirstOrderSort("desc");
        return picMaterialDAO.findPicMaterialsByQuery(picMaterialQuery);
    }

    public void reviewPass(long id) {
        picMaterialDAO.updateStatus(MainStatus.NORMAL.getType(), id);
    }
}
