package com.duyb.pi.web.controller.exception;

/**
 * @author duybui
 *
 */
public class PiCalculatorException extends RuntimeException{

	private static final long serialVersionUID = -8540120964105124022L;

	private String errCode;
	private String errMsg;

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public PiCalculatorException(String errCode, String errMsg) {
		this.errCode = errCode;
		this.errMsg = errMsg;
	}
}
