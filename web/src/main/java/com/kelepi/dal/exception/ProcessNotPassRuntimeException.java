package com.kelepi.dal.exception;

public class ProcessNotPassRuntimeException extends BaseRuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4086514173687413415L;
	
	private boolean hasPrevious = false;

	public ProcessNotPassRuntimeException(boolean hasPrevious) {
		super();
	}
	
	public ProcessNotPassRuntimeException(String msg) {
		super(msg);
	}
	
	public ProcessNotPassRuntimeException(String msg, boolean hasPrevious) {
		super(msg);
		
		this.hasPrevious = hasPrevious;
	}
	
	public ProcessNotPassRuntimeException(String msg, Throwable e) {
		super(msg, e);
	}

	public boolean isHasPrevious() {
		return hasPrevious;
	}

	public void setHasPrevious(boolean hasPrevious) {
		this.hasPrevious = hasPrevious;
	}
}
