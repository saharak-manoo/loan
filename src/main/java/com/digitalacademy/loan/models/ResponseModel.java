package com.digitalacademy.loan.models;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseModel implements Serializable {
  
  @JsonProperty("status")
  private StatusModel status;

  @JsonProperty("data")
  private Object data;

  public ResponseModel() {}

  public ResponseModel(StatusModel status) {
    this.status = status;
  }

  public ResponseModel(StatusModel status, Object data) {
    this.status = status;
    this.data = data;
  }

  public HttpEntity<ResponseModel> build(HttpStatus status) {
    return new ResponseEntity<>(
      new ResponseModel(this.status, this.data), status
    );
  }
}