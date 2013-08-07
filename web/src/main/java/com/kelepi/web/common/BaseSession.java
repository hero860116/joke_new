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
	
	public UserDO getUserDO() {
		return (UserDO)session.getAttribute("userDO");
	}
	
	public void setUserDO(UserDO userDO) {
        session.setAttribute("userDO", userDO);
	}

    public void removeUserDO() {
        session.removeAttribute("userDO");
    }
}
