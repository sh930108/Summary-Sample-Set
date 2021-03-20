package tech.xiying.template.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "接口返回参数说明")
@JsonInclude(value = Include.NON_NULL)
public class ControllerResult<T> extends Object {
	@ApiModelProperty(required = true, allowEmptyValue = false, value = "接口返回的结果码,返回 0 表示业务处理正常,其他为失败(例如：0x06b00001)", dataType = "String", example = "0", position = 1)
	protected String code;
	@ApiModelProperty(required = false, allowEmptyValue = true, value = "接口调用的结果说明", dataType = "String", example = "success", position = 2)
	protected String msg;
	@ApiModelProperty(required = false, allowEmptyValue = true, value = "接口返回的业务数据", position = 3)
	protected T data;

	public ControllerResult(String code) {
		this.code = code;
	}

	public ControllerResult(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public ControllerResult() {
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}

	public String getMsg() {
		return this.msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return (T) this.data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
