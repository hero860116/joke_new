package com.kelepi.web.common;

import com.alibaba.citrus.util.StringUtil;
import com.kelepi.dal.dataobject.UserDO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;


public class BaseSession{
	
	@Autowired
	private HttpSession session;
	
	/**
	 * 加入session
	 */
	public <T, K> void putToSession(K value, String areaName, String prefix, T... keys) {
		session.setAttribute(conectKey(areaName, prefix, keys), value);
	}
	
	/**
	 * 获取session
	 */
	@SuppressWarnings("unchecked")
	public <T, K> K getFromSession(String areaName, String prefix, T... keys) {
		return (K)session.getAttribute(conectKey(areaName, prefix, keys));
	}
	
	/**
	 * 删除session
	 */
	public <T> void removeFromSession(String areaName, String prefix, T... keys) {
		session.removeAttribute(conectKey(areaName, prefix, keys));
	}
	
	/**
	 * 拼接key（由区域+前缀+key值组成）
	 * 如：cus_phoneorder_mobile_15869016641
	 * @return
	 */
	private <T> String conectKey(String areaName, String prefix, T... keys) {
		return areaName + '_' + prefix + '_' + StringUtil.join(keys, "_");
	}
	
	public HttpSession getSession() {
		return session;
	}
	
	public UserDO getCurrentLoginUser() {
		return (UserDO)session.getAttribute("currentLoginUser");
	}
	
	public void setCurrentLoginUser(UserDO userDO) {
        session.setAttribute("currentLoginUser", userDO);
	}

    public void removeCurrentLoginUser() {
        session.removeAttribute("currentLoginUser");
    }

    /*  记录表态数据 */
    public void addPositionJokeList(Long jokeId) {
        List<Long> positionJokeList = (List<Long>)session.getAttribute("positionJokeList");
        if (positionJokeList == null) {
            positionJokeList = new LinkedList<Long>();
        }

        positionJokeList.add(jokeId);

        session.setAttribute("positionJokeList", positionJokeList);
    }

    public List<Long> getPositionJokeList() {
        return (List<Long>)getSession().getAttribute("positionJokeList");
    }

}
