package com.tsingyun.common.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by cuckoo on 16/12/2016.
 */
public interface ErrorCode {

  @ApiModelProperty(example = "请求参数类型检查出错", value = "错误详情", required = true)
  String getMessage();

  @ApiModelProperty(example = "10202", value = "错误代码", required = true)
  default Integer getCode() {
    return null;
  }
}
