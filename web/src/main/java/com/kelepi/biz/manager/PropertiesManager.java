package com.kelepi.biz.manager;

import java.util.Date;


public interface PropertiesManager {
	
	Date getDate(String name);
	
	void update(String name, Date value);
	
	String getValue(String name);
	
	int getIntValue(String name);
}
