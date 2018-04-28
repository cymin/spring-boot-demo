package com.tsingyun.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

import java.lang.*;

/**
 * Created by cuckoo on 17/12/2016.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FieldError implements ErrorCode {
  /**
   * GET、POST参数请求出错
   */
  protected String field;

  protected String message;

  public FieldError(String field, String message) {
    this.field = field;
    this.message = message;
  }

  @ApiModelProperty(value = "GET、POST参数请求校验出错字段")
  public String getField() {
    return field;
  }

  public void setField(String field) {
    this.field = field;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
