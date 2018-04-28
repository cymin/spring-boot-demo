package com.tsingyun.common.exception;

import com.tsingyun.common.model.ErrorCode;
import org.springframework.http.HttpStatus;

/**
 * Created by cuckoo on 21/12/2016.
 */
public class CommonHttpException extends RuntimeException {
  protected HttpStatus status = HttpStatus.BAD_REQUEST;

  protected ErrorCode code;

  protected String message;

  public CommonHttpException(HttpStatus status, ErrorCode code, String message) {
    this.status = status;
    this.code = code;
    this.message = message;
  }

  public CommonHttpException(HttpStatus status, ErrorCode code) {
    this(status, code, null);
  }

  public CommonHttpException(ErrorCode code, String message) {
    this(HttpStatus.BAD_REQUEST, code, message);
  }

  public CommonHttpException(ErrorCode code) {
    this(code, null);
  }

  public HttpStatus getStatus() {
    return this.status;
  }

  public Integer getCode() {
    return this.code.getCode();
  }

  @Override
  public String getMessage() {
    if (this.message != null) {
      return this.message;
    } else {
      return this.code.getMessage();
    }
  }
}
