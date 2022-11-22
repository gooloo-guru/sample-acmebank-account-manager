package com.acmebank.account.manager.service.request;

import com.acmebank.account.manager.dto.TransferTransactionRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TransferTransactionRequest extends AbstractTransactionRequest {
  private String fromAccountNumber;
  private String toAccountNumber;

  public static TransferTransactionRequest fromTransferTransactionRequestDto(TransferTransactionRequestDto requestDto) {
    var request = TransferTransactionRequest.builder()
        .toAccountNumber(requestDto.getToAccountNumber())
        .fromAccountNumber(requestDto.getFromAccountNumber())
        .build();
    request.setAmount(requestDto.getAmount());
    request.setCurrency(requestDto.getCurrency());
    return request;
  }
}
