package com.tsingyun.common.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum HttpErrorCode implements ErrorCode {
  /**
   * 请求相关错误
   */
  REQUEST_PARAM_MISSING(10201, "缺乏请求参数"),
  REQUEST_NOT_VALID(10202, "请求校验出错"),
  REQUEST_NOT_READABLE(10203, "请求解析出错"),
  REQUEST_PARAM_TYPE_MISMATCH(10204, "请求参数类型不匹配"),
  REQUEST_METHOD_NOT_ALLOWED(10205, "请求方法不合法"),
  REQUEST_ITEM_NOT_EXIST(10206, "请求的返回值内容为空"),
  /**
   * 权限相关错误
   */
  AUTH_ERROR_UNKNOWN(10300, "未知权限错误"),
  AUTH_NO_LOGIN(10301, "用户未登陆"),
  AUTH_NO_PERMISSION(10302, "无相关权限"),
  /**
   * 用户登陆相关错误
   */
  USER_NOT_EXIST(20101, "用户不存在"),
  USER_PASSWORD_MISMATCH(20102, "密码不对"),
  USER_IS_LOCKED(20103, "用户锁定"),
  USER_ERROR_UNKNOWN(20104, "未知认证错误"),
  USER_PASSWORD_NOT_SET(20105, "密码未设置");

  private Integer code;
  private String message;

  HttpErrorCode(Integer code, String message) {
    this.code = code;
    this.message = message;
  }

  @Override
  public Integer getCode() {
    return this.code;
  }

  @Override
  public String getMessage() {
    return this.message;
  }
}
