package com.kelepi.dal.dataobject;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

public class BaseDO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8555161031265129735L;

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
