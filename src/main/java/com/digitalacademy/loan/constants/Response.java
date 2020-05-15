package com.digitalacademy.loan.constants;

import lombok.Getter;

@Getter
public enum Response {
  SUCCESS_CODE("0"),
  FAIL_CODE("1"),
  SUCCESS("success");
  
  private final String content;

  Response(String content) {
    this.content = content;
  }

}