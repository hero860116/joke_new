package com.kelepi.common.bean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ParamInstance {
	
	private Map<String, Object> paras = new ConcurrentHashMap<String, Object>();

	private static ParamInstance instance = new ParamInstance();
	
	
	private ParamInstance() {
		
	}
	
	public static ParamInstance getInstance() {
		return instance;
	}
	

	public static Object get(String key) {
		return ParamInstance.getInstance().getParas().get(key);
	}
	
	public static void set(String key, Object value) {
		ParamInstance.getInstance().getParas().put(key, value);
	}

	public Map<String, Object> getParas() {
		return paras;
	}
	
}
