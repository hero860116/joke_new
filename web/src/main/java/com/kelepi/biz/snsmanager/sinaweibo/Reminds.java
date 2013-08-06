package com.kelepi.biz.snsmanager.sinaweibo;

import com.kelepi.biz.snsmanager.sinaweibo.model.PostParameter;
import com.kelepi.biz.snsmanager.sinaweibo.model.WeiboException;
import com.kelepi.biz.snsmanager.sinaweibo.org.json.JSONObject;
import com.kelepi.biz.snsmanager.sinaweibo.util.WeiboConfig;

public class Reminds extends Weibo{

	private static final long serialVersionUID = 1L;

	/**
	 * 获取某个用户的各种消息未读数 
	 * 
	 */
	public JSONObject getUnreadCountOfRemind () throws WeiboException {
		return client.get(WeiboConfig.getValue("rmURL") + "remind/unread_count.json").asJSONObject();
	}
	
	public JSONObject getUnreadCountOfRemind (String callback) throws WeiboException {
		return client.get(WeiboConfig.getValue("rmURL") + "remind/unread_count.json",new PostParameter[] {
			new PostParameter("callback", callback)
		}).asJSONObject();
	}
	
}
