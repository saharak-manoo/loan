package com.digitalacademy.loan.exceptions;

import com.digitalacademy.loan.constants.LoanError;
import org.springframework.http.HttpStatus;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoanException extends Exception{
  private LoanError loanError;
  private HttpStatus httpStatus = HttpStatus.OK;

  public LoanException(LoanError loanError, HttpStatus httpStatus) {
    this.loanError = loanError;
    this.httpStatus = httpStatus;
  }
}