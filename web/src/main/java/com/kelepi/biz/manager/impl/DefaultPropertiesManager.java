package com.kelepi.biz.manager.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import com.kelepi.biz.manager.PropertiesManager;
import com.kelepi.dal.dao.PropertiesDAO;
import com.kelepi.dal.dataobject.PropertiesDO;
import org.springframework.stereotype.Component;

@Component("propertiesManager")
public class DefaultPropertiesManager implements PropertiesManager {
	@Resource
	private PropertiesDAO propertiesDAO;

	public String getValue(String name) {
		PropertiesDO propertiesDO = propertiesDAO.getPropertiesByName(name);
		String value =  propertiesDO.getValue();
		if (value == null) {
			value = propertiesDO.getDefaultValue();
		}
		
		return value;
	}

    public int getIntValue(String name) {
	    int intValue = 0;
	    
	     String value = getValue(name);
	     if (value != null) {
	         intValue = Integer.parseInt(value);
	     }
	    
        return intValue;
    }

	public Date getDate(String name) {
		//获得上次执行的时间，如果没有则没有下限时间点
		PropertiesDO propertiesDO = propertiesDAO.getPropertiesByName(name);
		
		//默认为1年以前
		Calendar yearsAgo = Calendar.getInstance();
		yearsAgo.add(Calendar.YEAR, -1);
		Date lastModifyTime = yearsAgo.getTime();
		if (propertiesDO != null) {
			try {
				lastModifyTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(propertiesDO.getValue());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			propertiesDO = new PropertiesDO();
			propertiesDO.setName(name);
			propertiesDO.setValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(lastModifyTime));
			propertiesDO.setDefaultValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(lastModifyTime));
			
			propertiesDAO.save(propertiesDO);
		}
		
		return lastModifyTime;
	}

	public void update(String name, Date value) {
		propertiesDAO.updatePropertiesValue(name, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(value));
	}

}
