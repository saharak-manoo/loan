// package com.digitalacademy.loan.controllers;

// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import static org.junit.Assert.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertThrows;
// import static org.mockito.Mockito.when;
// import com.digitalacademy.loan.constants.LoanError;
// import com.digitalacademy.loan.exception.LoanException;
// import com.digitalacademy.loan.model.LoanInfo;
// import com.digitalacademy.loan.service.LoanService;
// import static org.mockito.Matchers.any;
// import static org.mockito.Mockito.times;
// import static org.mockito.Mockito.verify;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.MediaType;
// import org.springframework.test.context.junit.jupiter.SpringExtension;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.MvcResult;
// import org.springframework.test.web.servlet.setup.MockMvcBuilders;

// import net.minidev.json.JSONObject;

// @ExtendWith(SpringExtension.class)
// @AutoConfigureMockMvc
// @SpringBootTest
// public class LoanControllerTest {

//   @Mock
//   LoanService loanServive;

//   @InjectMocks
//   LoanController loanController;

//   private MockMvc mvc;

//   @DisplayName("Test get loan by id 1")
//   @Test
//   void testGetLoanInfoByCustomerIdEquals1() {
//     Long reqParam = 1L;
//     LoanInfo loanInfo = new LoanInfo();
//     loanInfo.setId(reqParam);
//     loanInfo.setStatus("OK");

//     when(loanServive.getloanInfoById(reqParam)).thenReturn(loanInfo);

//     MvcResult mvcResult = mvc.perform(get("/loan/info" + reqParam))
//                              .andExpect(status().isOk())
//                              .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//                              .andReturn();

//     JSONObject resp = new JSONObject(mvcResult.getResponse().getContentAsString());
//     JSONObject status = new JSONObject(resp.getString("status"));
//     JSONObject data = new JSONObject(resp.getAsString("data"));

//     assertEquals("0", status.get("code").toString());
//     assertEquals("success", status.get("message").toString());

//     assertEquals(1, data.get("id"));
//     assertEquals("OK", data.get("status"));

//     verify(loanServive, times(1)).getloanInfoById(reqParam);
//   }

//   @DisplayName("Test get loan by id 2 and throws error")
//   @Test
//   void testGetLoanInfoByCustomerIdEquals2() throws Exception {
//     Long reqParam = 2L;

//     when(loanServive.getloanInfoById(reqParam)).thenThrow(
//       new LoanException(LoanError.GET_LOAN_NOT_FOUND, HttpStatus.BAD_REQUEST)
//     );

//     MvcResult mvcResult = mvc.perform(get("/loan/info" + reqParam))
//                              .andExpect(status().isOk())
//                              .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//                              .andReturn();

//     JSONObject resp = new JSONObject(mvcResult.getResponse().getContentAsString());
//     JSONObject status = new JSONObject(resp.getAsString("status"));

//     assertEquals("loan4002", status.get("code").toString());
//     assertEquals("Loan infomation not found", status.get("message").toString());

//     verify(loanServive, times(1)).getloanInfoById(reqParam);
//   }

//   @DisplayName("Test get loan by id 3 and throws error")
//   @Test
//   void testGetLoanInfoByCustomerIdEquals3() throws Exception {
//     Long reqParam = 3L;
//     when(loanServive.getloanInfoById(reqParam)).thenThrow(
//       new Exception("Error Exception")
//     );

//     MvcResult mvcResult = mvc.perform(get("/loan/info" + reqParam))
//                              .andExpect(status().isInternalServerError())
//                              .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//                              .andReturn();

//     JSONObject resp = new JSONObject(mvcResult.getResponse().getContentAsString());
//     JSONObject status = new JSONObject(resp.getString("status"));

//     assertEquals("loan4001", status.get("code").toString());
//     assertEquals("Cannot get loan infomation", status.get("message").toString());

//     verify(loanServive, times(1)).getloanInfoById(reqParam);
//   }
// }