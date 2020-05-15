package com.digitalacademy.loan.constants;

import lombok.Getter;

@Getter
public enum LoanError {
  GET_LOAN_INFO_EXCEPTION("LOAN4001", "Cannot get loan infomation"),
  GET_LOAN_NOT_FOUND("LOAN4002", "Loan infomation not found");
  
  private String code;
  private String message;

  LoanError(String code, String message) {
    this.code = code;
    this.message = message;
  }
}