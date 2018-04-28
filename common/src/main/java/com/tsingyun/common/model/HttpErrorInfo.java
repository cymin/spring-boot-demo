package com.tsingyun.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.servlet.http.HttpServletRequest;
import java.lang.*;
import java.util.Calendar;
import java.util.List;

/**
 * 错误信息类
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HttpErrorInfo implements ErrorCode {
  /**
   * 出错时间
   */
  protected Calendar timestamp;

  /**
   * 请求URL
   */
  protected String path;

  /**
   * 错误编码
   */
  protected Integer code;

  /**
   * 错误信息
   */
  protected String message;

  /**
   * 多条错误信息
   */
  protected List<ErrorCode> errors;

  public HttpErrorInfo() {
    this.timestamp = Calendar.getInstance();
  }

  public HttpErrorInfo(HttpServletRequest request, Integer code) {
    this(request, code, null);
  }

  public HttpErrorInfo(HttpServletRequest request, ErrorCode code) {
    this(request, code.getCode(), code.getMessage());
  }

  public HttpErrorInfo(HttpServletRequest request, ErrorCode code, String message) {
    this(request, code.getCode(), message);
  }

  public HttpErrorInfo(HttpServletRequest request, Integer code, String message) {
    this.timestamp = Calendar.getInstance();
    if (request != null) {
      this.path = request.getRequestURI();
    }
    this.code = code;
    this.message = message;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public List<ErrorCode> getErrors() {
    return errors;
  }

  public void setErrors(List<ErrorCode> errors) {
    this.errors = errors;
  }

  @Override
  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  @Override
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Calendar getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Calendar timestamp) {
    this.timestamp = timestamp;
  }
}
