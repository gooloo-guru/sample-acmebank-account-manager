package com.acmebank.account.manager.controller;


import com.acmebank.account.manager.constant.ErrorCode;
import com.acmebank.account.manager.dto.ErrorDto;
import com.acmebank.account.manager.dto.ErrorResponseDto;
import com.acmebank.account.manager.exception.AccountManagerException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class AccountManagerExceptionHandlerController {

  protected ErrorResponseDto<ErrorDto> handleException(final Throwable throwable, String errorCode, String errorMessage) {
    log.error(throwable.getMessage(), throwable);

    final ErrorDto errorDto = ErrorDto.builder()
        .errorCode(StringUtils.defaultIfBlank(errorCode, ErrorCode.DEFAULT_ERROR_CODE))
        .errorMessage(StringUtils.defaultIfBlank(errorMessage, ErrorCode.DEFAULT_ERROR_MESSAGE))
        .build();
    return ErrorResponseDto.response(errorDto);
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(AccountManagerException.class)
  public ErrorResponseDto<ErrorDto> handleException(final AccountManagerException ex) {
    return handleException(ex, ex.getErrorCode(), ex.getMessage());
  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler({Exception.class, RuntimeException.class})
  public ErrorResponseDto<ErrorDto> handleException(final RuntimeException e) {
    return handleException(e, null, null);
  }

}
