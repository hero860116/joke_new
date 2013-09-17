package com.kelepi.biz.ao.impl;

import com.kelepi.biz.ao.BaseAO;
import com.kelepi.biz.ao.JokeMaterialAO;
import com.kelepi.common.bean.ParamInstance;
import com.kelepi.dal.dao.JokeMaterialDAO;
import com.kelepi.dal.dataobject.JokeMaterialDO;
import com.kelepi.dal.enums.MainStatus;
import com.kelepi.dal.queryobject.JokeMaterialQuery;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: liWeiLin
 * Date: 13-9-17 下午9:56
 */
@Component("jokeMaterialAO")
public class JokeMaterialAOImpl extends BaseAO implements JokeMaterialAO {

    // name:jokeMaterial   Name:JokeMaterial

    @Resource
    private JokeMaterialDAO jokeMaterialDAO;

    public long save(JokeMaterialDO jokeMaterialDO) {
        return jokeMaterialDAO.save(jokeMaterialDO);
    }

    public void update(JokeMaterialDO jokeMaterialDO) {
        JokeMaterialDO srcJokeMaterialDO = jokeMaterialDAO.getJokeMaterial(jokeMaterialDO.getId());

        srcJokeMaterialDO.setContent(jokeMaterialDO.getContent());
        srcJokeMaterialDO.setCategoryId(jokeMaterialDO.getCategoryId());
        srcJokeMaterialDO.setCategoryName(ParamInstance.getCategory(jokeMaterialDO.getCategoryId()).getName());

        jokeMaterialDAO.update(srcJokeMaterialDO);
    }

    public void delete(long id) {
        jokeMaterialDAO.delete(id);
    }

    public JokeMaterialDO getJokeMaterial(long id) {
        return jokeMaterialDAO.getJokeMaterial(id);
    }

    public List<JokeMaterialDO> getJokeMaterialsByTemplate(JokeMaterialDO jokeMaterialDOQuery) {
        return jokeMaterialDAO.getJokeMaterialListByTemplate(jokeMaterialDOQuery);
    }

    public List<JokeMaterialDO> findJokeMaterialsByQuery(JokeMaterialQuery jokeMaterialQuery) {
        jokeMaterialQuery.setFirstOrder("gmtCreate");
        jokeMaterialQuery.setFirstOrderSort("desc");
        return jokeMaterialDAO.findJokeMaterialsByQuery(jokeMaterialQuery);
    }

    public void reviewPass(long id) {
        //To change body of implemented methods use File | Settings | File Templates.
        jokeMaterialDAO.updateStatus(MainStatus.NORMAL.getType(), id);
    }
}
