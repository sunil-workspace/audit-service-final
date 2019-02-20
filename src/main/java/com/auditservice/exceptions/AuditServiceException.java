package com.auditservice.exceptions;

public class AuditServiceException  extends RuntimeException {

	private static final long serialVersionUID = 4428604001833644329L;

	private String errCode;
	private String errMsg;

	public AuditServiceException(String errMsg) {
		this.errMsg = errMsg;
	}
	public AuditServiceException(String errCode, String errMsg) {
		this.errCode = errCode;
		this.errMsg = errMsg;
	}
	public AuditServiceException(final String message, final Throwable t) {
		super(message, t);
	}

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

}
