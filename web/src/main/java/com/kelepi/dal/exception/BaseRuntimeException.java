package com.kelepi.dal.exception;

public class BaseRuntimeException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1784229232034064936L;
	
	public BaseRuntimeException() {
		super();
	}
	
	public BaseRuntimeException(String msg) {
		super(msg);
	}
	
	public BaseRuntimeException(String msg, Throwable e) {
		super(msg, e);
	}
	
	
	
}
