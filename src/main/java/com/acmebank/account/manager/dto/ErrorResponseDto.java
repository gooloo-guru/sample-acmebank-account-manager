package com.acmebank.account.manager.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponseDto<T> {
  T error = null;

  public ErrorResponseDto(T error) {
    this.error = error;
  }

  public static <T> ErrorResponseDto<T> response(T error) {
    return new ErrorResponseDto<>(error);
  }
}
