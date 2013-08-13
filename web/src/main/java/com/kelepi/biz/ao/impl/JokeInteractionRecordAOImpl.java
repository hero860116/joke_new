package com.kelepi.biz.ao.impl;

import com.kelepi.biz.ao.BaseAO;
import com.kelepi.biz.ao.CategoryAO;
import com.kelepi.biz.ao.JokeInteractionRecordAO;
import com.kelepi.common.bean.Result;
import com.kelepi.dal.dao.JokeInteractionRecordDAO;
import com.kelepi.dal.dao.UserDAO;
import com.kelepi.dal.dataobject.CategoryDO;
import com.kelepi.dal.dataobject.JokeInteractionRecordDO;
import com.kelepi.dal.dataobject.UserDO;
import com.kelepi.dal.enums.MainStatus;
import com.kelepi.dal.enums.RecommendType;
import com.kelepi.util.ListUtil;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component("jokeInteractionRecordAO")
public class JokeInteractionRecordAOImpl extends BaseAO implements JokeInteractionRecordAO {

	@Resource
	private JokeInteractionRecordDAO jokeInteractionRecordDAO;

    @Resource
    private CategoryAO categoryAO;

    @Resource
    private UserDAO userDAO;

    public long save(JokeInteractionRecordDO jokeInteractionRecord) {
		return jokeInteractionRecordDAO.save(jokeInteractionRecord);
	}

	public void update(JokeInteractionRecordDO jokeInteractionRecord) {
		jokeInteractionRecordDAO.update(jokeInteractionRecord);
	}

	public void delete(long id) {
		jokeInteractionRecordDAO.delete(id);
	}

	public JokeInteractionRecordDO getJokeInteractionRecord(long id) {
		return jokeInteractionRecordDAO.getJokeInteractionRecord(id);
	}

	public List<JokeInteractionRecordDO> getJokeInteractionRecordsByTemplate(JokeInteractionRecordDO jokeInteractionRecord) {
		return jokeInteractionRecordDAO.getJokeInteractionRecordListByTemplate(jokeInteractionRecord);
	}
}
