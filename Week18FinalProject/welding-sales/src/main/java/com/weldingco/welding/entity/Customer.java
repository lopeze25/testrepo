package com.weldingco.welding.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Customer {
  private Long customerPK;
  private String customerId;
  private String email;
  private String phone;
  
  @JsonIgnore
  public Long getCustomerPK() {
    return customerPK;
}

}