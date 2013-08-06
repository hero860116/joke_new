package com.kelepi.dal.exception;

public class NoLoginRuntimeException extends BaseRuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4232410091401952145L;
	
	private boolean withTarget;

	public NoLoginRuntimeException() {
		super();
	}
	
	public NoLoginRuntimeException(String msg) {
		super(msg);
	}
	
	public NoLoginRuntimeException(String msg, boolean withTarget) {
		super(msg);
		this.withTarget = withTarget;
	}
	
	public NoLoginRuntimeException(String msg, Throwable e) {
		super(msg, e);
	}

	public boolean isWithTarget() {
		return withTarget;
	}

	public void setWithTarget(boolean withTarget) {
		this.withTarget = withTarget;
	}
}
