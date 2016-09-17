package com.eli.calc.shape.service.ws.types;

public class SuccessOrErrorResponse {

	private RespCode code;
	
	private String description;
	
	private Class clazz;
	
	private String errMsg;
	
	private String causeMsg;
	
	public SuccessOrErrorResponse() {
		
		this.code = RespCode.ERROR;
		this.description = "DESCRIPTION NEEDS TO BE SET";
		Exception e = new Exception(
				"NEED AN EXCEPTION CLASS",
				new IllegalArgumentException("FAKE CAUSE"));
		this.clazz = e.getClass();
		this.errMsg = e.getMessage();
		this.causeMsg = e.getCause().getMessage();
	}

	public SuccessOrErrorResponse(RespCode code, String description) {

		this();

		this.code = code;
		this.description = description;

		if (RespCode.SUCCESS.equals(code)) {
			this.clazz = null;
			this.errMsg = null;
			this.causeMsg = null;
		}
	}

	public SuccessOrErrorResponse(String description, Exception e) {

		this(RespCode.ERROR,description);
		
		if (null!=e) {
			this.clazz = e.getClass();
			this.errMsg = e.getMessage();
			if (e.getCause()!=null) { this.causeMsg = e.getCause().getMessage(); }
			else { this.causeMsg = null; }
		}
	}

	public RespCode getCode() {
		return code;
	}

	public void setCode(RespCode code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Class getClazz() {
		return clazz;
	}

	public void setClazz(Class clazz) {
		this.clazz = clazz;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public String getCauseMsg() {
		return causeMsg;
	}

	public void setCauseMsg(String causeMsg) {
		this.causeMsg = causeMsg;
	}

	@Override
	public String toString() {
		return "SuccessOrErrorResponse [code=" + code + ", description=" + description + ", clazz=" + clazz
				+ ", errMsg=" + errMsg + ", causeMsg=" + causeMsg + "]";
	}
	

	
}
