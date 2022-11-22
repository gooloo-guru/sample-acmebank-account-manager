package com.acmebank.account.manager.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDto<T> {
  T data = null;

  public ResponseDto(T data) {
    this.data = data;
  }

  public static <T> ResponseDto<T> response(T data) {
    return new ResponseDto<>(data);
  }
}
