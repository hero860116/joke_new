package com.kelepi.common.bean;

import java.util.HashMap;

import org.apache.commons.lang.StringUtils;

public class MultipleKeyHashMap<V> extends HashMap<String, V>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5041725700941549376L;
	
	private static final String KEY_LINK = "_";
	
	public V put(V value, Object... keys) {
		return super.put(connectionKeys(keys), value);
	}
	
	public V get(Object... keys) {
		return get(connectionKeys(keys));
	}
	
	private String connectionKeys(Object... keys) {
		return StringUtils.join(keys, KEY_LINK);
	}
}
