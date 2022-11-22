package com.acmebank.account.manager.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDto {
  String errorCode;
  String errorMessage;
}
