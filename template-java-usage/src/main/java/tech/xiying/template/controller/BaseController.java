package tech.xiying.template.controller;

import org.apache.commons.lang3.StringUtils;
import tech.xiying.template.api.ControllerResult;

import javax.servlet.http.HttpServletResponse;

/**
 * @author shanghao5
 */
public class BaseController {

    protected <T> ControllerResult<T> getControllerResult(HttpServletResponse response, String errorCode, String errorMsg, int status) {
        return getControllerResult(status,response,null,errorCode,errorMsg);
    }

    protected <T> ControllerResult<T> getControllerResult(HttpServletResponse response, String errorCode, String errorMsg) {
        return getControllerResult(response,null,errorCode,errorMsg);
    }

    protected <T> ControllerResult<T> getControllerResult(HttpServletResponse response, int errorCode, String errorMsg) {
        return getControllerResult(response,null,errorCode+"",errorMsg);
    }

    protected <T> ControllerResult<T> getControllerResult(HttpServletResponse response, T data) {
        return getControllerResult(response,data,"0", StringUtils.EMPTY);
    }


    protected <T> ControllerResult<T> getControllerResult(HttpServletResponse response, T data, String errorCode, String errorMsg) {
        if(StringUtils.isNotEmpty(errorCode) && !"0".equals(errorCode)) {
            return getControllerResult(500,response,data,errorCode,errorMsg);
        }else {
            return getControllerResult(200,response,data,errorCode,errorMsg);
        }
    }

    protected <T> ControllerResult<T> getControllerResult(int status, HttpServletResponse response, T data, String errorCode, String errorMsg) {
        response.setStatus(status);
        ControllerResult<T> ControllerResult = new ControllerResult<T>();
        ControllerResult.setData(data);
        ControllerResult.setCode(errorCode);
        ControllerResult.setMsg(errorMsg);
        return ControllerResult;
    }

    /**
     * 添加跨域参数
     *
     * @param response
     * @return
     */
    protected HttpServletResponse addCrossDomainParam(HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT, PATCH, HEAD");
        response.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With, userId, token");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Max-Age", "0");
        return response;
    }
}
