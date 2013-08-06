package com.kelepi.common.bean;

public class EquivalenceProperty {
	private String srcName;
	private String destName;
	
	public EquivalenceProperty(String srcName, String destName) {
		super();
		this.srcName = srcName;
		this.destName = destName;
	}
	public String getSrcName() {
		return srcName;
	}
	public void setSrcName(String srcName) {
		this.srcName = srcName;
	}
	public String getDestName() {
		return destName;
	}
	public void setDestName(String destName) {
		this.destName = destName;
	}
	
	
}
