package com.kelepi.biz.ao.impl;

import com.kelepi.biz.ao.BaseAO;
import com.kelepi.biz.ao.PicMaterialAO;
import com.kelepi.dal.constants.JokeConstants;
import com.kelepi.dal.dao.PicMaterialDAO;
import com.kelepi.dal.dataobject.PicMaterialDO;
import com.kelepi.dal.enums.MainStatus;
import com.kelepi.dal.queryobject.PicMaterialQuery;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: liWeiLin
 * Date: 13-8-24 下午4:26
 */
@Component
public class PicMaterialAOImpl extends BaseAO implements PicMaterialAO{
    // name:picMaterial   Name:PicMaterial

    @Resource
    private PicMaterialDAO picMaterialDAO;

    public long save(PicMaterialDO picMaterialDO) {
        picMaterialDO.setStatus(MainStatus.TO_REVIEW.getType());
        return picMaterialDAO.save(picMaterialDO);
    }

    public void update(PicMaterialDO picMaterialDO) {
        picMaterialDAO.update(picMaterialDO);
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
        picMaterialQuery.setPageSize(JokeConstants.FRONT_PAGE_SZIE);
        return picMaterialDAO.findPicMaterialsByQuery(picMaterialQuery);
    }

    public void reviewPass(long id) {
        picMaterialDAO.updateStatus(MainStatus.NORMAL.getType(), id);
    }
}
