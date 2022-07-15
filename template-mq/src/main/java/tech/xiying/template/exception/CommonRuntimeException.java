package tech.xiying.template.exception;

import org.springframework.http.HttpStatus;

/**
 * @ClassName CommonRuntimeException
 * @Description
 * @Author shanghao5
 * @Date 2021/3/20 11:39
 **/
public class CommonRuntimeException extends RuntimeException {

    private static final long serialVersionUID = -2575681791792926769L;

    private String code = "0";

    private String msg;

    private int status = HttpStatus.OK.value();

    public CommonRuntimeException(String code) {
        super();
        this.code = code;
    }

    public CommonRuntimeException(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public CommonRuntimeException(String msg, String code, int status) {
        super(msg);
        this.code = code;
        this.msg = msg;
        this.status = status;
    }

    public CommonRuntimeException(String code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    public CommonRuntimeException(Throwable cause, String code, int status) {
        super(cause);
        this.code = code;
        this.status = status;
    }

    public CommonRuntimeException(String code, String msg, Throwable cause) {
        super(msg, cause);
        this.code = code;
        this.msg = msg;
    }

    public CommonRuntimeException(String msg, Throwable cause, String code, int status) {
        super(msg, cause);
        this.code = code;
        this.msg = msg;
        this.status = status;
    }

    protected CommonRuntimeException(String message, Throwable cause, boolean enableSuppression,
                                     boolean writableStackTrace, String code) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
        this.msg = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
}
