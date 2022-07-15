package tech.xiying.template.constant;


public enum ErrorCode {
	SUCCESS("0", null),
	ERR_COMMON_ERROR("0x10f00001", "Common Error"),
	;

	private String errorCode;
	private String message;
	private ErrorCode(String errorCode, String message) {
		this.errorCode = errorCode;
		this.message = message;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public String getMessage() {
		return message;

	}
}
