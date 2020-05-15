package com.digitalacademy.loan.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import com.digitalacademy.loan.constants.LoanError;
import com.digitalacademy.loan.constants.Response;
import com.digitalacademy.loan.exception.LoanException;
import com.digitalacademy.loan.model.LoanInfo;
import com.digitalacademy.loan.model.ResponseModel;
import com.digitalacademy.loan.model.StatusModel;
import com.digitalacademy.loan.service.LoanService;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping(path = "loan")
public class LoanController {
  private static final Logger log = LogManager.getLogger(LoanController.class.getName());

  @Autowired
  private LoanService loanService;

  @GetMapping("/info/{id}")
  public HttpEntity<ResponseModel> getLongInfoByCustomerId(@PathVariable Long id) 
  throws Exception {
    try {
      LoanInfo loanInfo = loanService.getloanInfoById(id);
      log.info("Get loan info by customer id: {} status is {}",
        loanInfo.getId(),
        loanInfo.getStatus()
      );
      StatusModel status = new StatusModel(
        Response.SUCCESS_CODE.getContent(),
        Response.SUCCESS.getContent()
      );

      return new ResponseModel(status,loanInfo).build(HttpStatus.OK);
    } catch(LoanException le) {
      log.error(le.getLoanError().getMessage());
      LoanError loanError = le.getLoanError();

      return new ResponseModel(
        new StatusModel(loanError.getCode(), loanError.getMessage())
      ).build(le.getHttpStatus());
    } catch(Exception e) {
      log.error(e.getMessage());
      LoanError loanError = LoanError.GET_LOAN_INFO_EXCEPTION;

      return new ResponseModel(
        new StatusModel(
          loanError.getCode(),
          loanError.getMessage()
        )
      ).build(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}