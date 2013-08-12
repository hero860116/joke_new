package com.kelepi.biz.ao.impl;

import com.kelepi.biz.ao.BaseAO;
import com.kelepi.biz.ao.CategoryAO;
import com.kelepi.biz.ao.JokeAO;
import com.kelepi.common.bean.Result;
import com.kelepi.dal.dao.JokeDAO;
import com.kelepi.dal.dao.UserDAO;
import com.kelepi.dal.dataobject.CategoryDO;
import com.kelepi.dal.dataobject.JokeDO;
import com.kelepi.dal.dataobject.UserDO;
import com.kelepi.dal.enums.MainStatus;
import com.kelepi.dal.enums.RecommendType;
import com.kelepi.dal.queryobject.JokeQuery;
import com.kelepi.util.ListUtil;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Component("jokeAO")
public class JokeAOImpl extends BaseAO implements JokeAO {

	@Resource
	private JokeDAO jokeDAO;

    @Resource
    private CategoryAO categoryAO;

    @Resource
    private UserDAO userDAO;

    public long save(JokeDO joke) {
		return jokeDAO.save(joke);
	}

	public void update(JokeDO joke) {
		jokeDAO.update(joke);
	}

	public void delete(long id) {
		jokeDAO.delete(id);
	}

	public JokeDO getJoke(long id) {
		return jokeDAO.getJoke(id);
	}

	public List<JokeDO> getJokesByTemplate(JokeDO joke) {
		return jokeDAO.getJokeListByTemplate(joke);
	}

	public List<JokeDO> findJokesByQuery(JokeQuery jokeQuery) {
		return jokeDAO.findJokeListByQuery(jokeQuery);
	}

    public Result findJokes(JokeQuery jokeQuery) {
        Result result = createResult(true);
        jokeQuery.setFirstOrder("gmtCreate");
        jokeQuery.setFirstOrderSort("desc");
        jokeQuery.setStatus(MainStatus.NORMAL.getType());

        List<JokeDO> jokeDOs = jokeDAO.findJokeListByQuery(jokeQuery);

        List<Long> userIds = ListUtil.getPropertiesFromList(jokeDOs, "userId");

        List<UserDO> userDOs = new ArrayList<UserDO>();
        if (userIds.size() > 0) {
            userDOs = userDAO.getUserDOs(userIds);
        }

        result.setModel("jokeDOs", jokeDOs);
        result.setModel("userDOs", userDOs);


        return result;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Result getReviewJoke(JokeQuery jokeQuery) {

        Result result = createResult(true);

        jokeQuery.setPageSize(1);
        jokeQuery.setStatus(MainStatus.TO_REVIEW.getType());
        jokeQuery.setFirstOrder("gmtCreate");
        jokeQuery.setFirstOrderSort("desc");
        List<JokeDO> jokeDOs = jokeDAO.findJokeListByQuery(jokeQuery);

        JokeDO jokeDO = null;
        if (jokeDOs.size() > 0) {
            jokeDO = jokeDOs.get(0);
        }

        result.setModel("jokeDO", jokeDO);

        if (jokeDO != null) {
            UserDO userDO = userDAO.get(jokeDO.getUserId());
            result.setModel("userDO", userDO);
        }

        return result;
    }

    public void addTopic(JokeDO jokeDO) {
        //权限校验

        CategoryDO categoryDO = categoryAO.getCategoryFromCache((long) jokeDO.getJokeCategory());
        jokeDO.setMediumCategory(categoryDO.getParentCategoryDOs().get(1).getId().intValue());
        jokeDO.setStatus(MainStatus.TO_REVIEW.getType());
        jokeDO.setRecommendType(RecommendType.NORMAL.getType());
        jokeDO.setUserId(getCurrentLoginUser().getId());
        jokeDO.setUserNickName(getCurrentLoginUser().getNickName());
        jokeDAO.save(jokeDO);
    }

    @Transactional
    public void funnyJoke(long id) {
         JokeDO jokeDO = jokeDAO.getJoke(id);

         int funnySize = jokeDO.getFunnySize() + 1;
         int notFunnySize = jokeDO.getNotFunnySize();

         if (funnySize + notFunnySize >= 50 && funnySize >= 35) {
                jokeDAO.updateStatus(MainStatus.NORMAL.getType(), id);
         }

        jokeDAO.addFunnySize(1, id);
    }

    @Transactional
    public void notFunnyJoke(long id) {
        JokeDO jokeDO = jokeDAO.getJoke(id);

        int funnySize = jokeDO.getFunnySize();
        int notFunnySize = jokeDO.getNotFunnySize() + 1;

        if (funnySize + notFunnySize >= 50 && funnySize >= 35) {
            jokeDAO.updateStatus(MainStatus.NORMAL.getType(), id);
        }

        jokeDAO.addNotFunnySize(1, id);
    }

    public void reviewPass(long id) {
        jokeDAO.updateStatus(MainStatus.NORMAL.getType(), id);
    }
}