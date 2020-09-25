package com.wzp.module.core.result;

/**
 * 错误码规范：A-BB-CCC；如：101001
 * A:错误级别，如1代表系统级错误，2代表服务级错误；
 * B:项目或模块名称，一般公司不会超过99个项目；
 * C:具体错误编号，一个系统模块错误码一般不会大于999，三位应该够使了
 */
public enum ErrorCodeEnum {

    /**
     * ###################################   系统级错误码：1xxxxx   ###########################################
     */

    SYS_COMMON_CUSTOM_MSG(-1, "《《《《《注意：通用错误码，用于系统内部自定义异常消息》》》》》》"),
    SYS_SUCCESS(0, "请求成功"),
    SYS_SERVER_ERROR(1, "系统错误"),
    SYS_SERVER_BUSY(2, "系统繁忙"),

    PARAM_NOT_NULL_ERROR(3, "参数不能为空！"),
    PAGE_PARAM_NOT_NULL_ERROR(4, "分页查询参数不能为空！"),
    //用于用户传入参数异常
    SYS_PARAM_ERROR(5, "参数错误"),


    ACCESS_DENIED(403,"权限不足"),
    NOT_LOG_IN(401,"用户未登录或Token过期")

    ;

    private int code;
    private String message;

    ErrorCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 根据错误码返回错误消息
     * @param errCode
     * @return
     */
    public ErrorCodeEnum getErrorCodeEnum(int errCode) {
        ErrorCodeEnum codeEnum = null;
        for (ErrorCodeEnum errorCodeEnum : ErrorCodeEnum.values()) {
            if(errorCodeEnum.getCode() == errCode) {
                codeEnum = errorCodeEnum;
                break;
            }
        }
        return codeEnum;
    }

    public static String getMessage(int errCode) {
        return "";
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
