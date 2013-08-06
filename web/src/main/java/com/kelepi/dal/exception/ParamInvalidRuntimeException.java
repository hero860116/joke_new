package com.kelepi.dal.exception;

public class ParamInvalidRuntimeException extends BaseRuntimeException{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -9074748500253267557L;

	public ParamInvalidRuntimeException() {
		super();
	}
	
	public ParamInvalidRuntimeException(String msg) {
		super(msg);
	}
	
	public ParamInvalidRuntimeException(String msg, Throwable e) {
		super(msg, e);
	}
}
