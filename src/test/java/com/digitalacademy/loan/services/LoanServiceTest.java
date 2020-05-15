package com.digitalacademy.loan.services;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.digitalacademy.loan.exceptions.LoanException;
import com.digitalacademy.loan.models.LoanInfo;
import com.digitalacademy.loan.services.LoanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LoanServiceTest {

  @InjectMocks
  LoanService loanService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    loanService = new LoanService();
  }

  @DisplayName("Test get loan info by id equls 1 should return loan infomation")
  @Test
  void testGetLoanInfoByEquals1() throws Exception {
    LoanInfo resp = loanService.getloanInfoById(1L);

    assertEquals("1", resp.getId().toString());
    assertEquals("OK", resp.getStatus());
    assertEquals("102-444-6666", resp.getAccountPayable());
    assertEquals("102-333-6666", resp.getAccountReceivable());
    assertEquals("2000.00", resp.getPrincipalAmount().toString());
  }

  @DisplayName("Test get loan info by id equls 2 should throws error")
  @Test
  void testGetLoanInfoByEquals2() throws Exception {
    Long reqParam = 2L;

    LoanException thrown = assertThrows(LoanException.class,
      () -> loanService.getloanInfoById(reqParam),
      "Expected loanInfoById(reqParam) to throw, but it didn't"
    );

    assertEquals(400, thrown.getHttpStatus());
    assertEquals("loan4002", thrown.getLoanError().getCode());
    assertEquals("Loan infomation not found", thrown.getLoanError().getMessage());
  }

  @DisplayName("Test get loan info by id equls 3 should throws error")
  @Test
  void testGetLoanInfoByEquals3() throws Exception {
    Long reqParam = 3L;

    Exception thrown = assertThrows(Exception.class,
      () -> loanService.getloanInfoById(reqParam),
      "Expected loanInfoById(reqParam) to throw, but it didn't"
    );

    assertEquals("Error Exception", thrown.getMessage());
  }
}