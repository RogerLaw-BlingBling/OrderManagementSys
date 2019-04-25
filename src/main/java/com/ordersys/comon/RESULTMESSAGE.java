package com.ordersys.comon;

/**
 * Created by admin on 2019/1/7.
 */
public enum RESULTMESSAGE {
    SUCCESS(200 , "操作成功"),
    FAIL(1, "操作失败"),
    PARAM_ERROR(2, "参数有误"),
    DB_EXCEPTION(-4, "数据库异常"),

    //文件
    FILE_DATA_ERROR(901, "文件信息有误"),
    FILE_NOT_FOUND(902, "找不到文件"),
    FILE_IO_EXCEPTION(903, "文件处理IO异常"),

    //用户模块
    USER_NOT_EXIST(1001, "用户不存在"),
    USER_PASSWORD_ERROR(1002, "用户密码错误"),
    USET_TOKEN_FAILURE(1003, "TOKEN无效或已过期"),
    USER_STATUS_DISABLE(1004, "用户已被禁用"),


    //角色模块
    ROLE_NOT_EXIST(2001, "角色不存在"),
    ROLE_STATUS_DISABLE(2002, "角色已被禁用"),
    ROLE_DELETE_NOT_ALLOWED(2003, "超级管理员不可删除"),

    //机构模块
    ORG_LEVEL_MAX(3001, "机构层数已达上限"),
    ORG_NUMBER_MAX(3002, "当前层级机构数量已达上限"),
    ORG_NOT_EXIST(3003, "机构不存在或者机构信息有误"),

    //站牌管理
    BOARD_ROUTE_REL_NOEXIT(10000, "站牌未绑定线路信息")

    ;

    private Integer code;
    private String message;

    RESULTMESSAGE(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
