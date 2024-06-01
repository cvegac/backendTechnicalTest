package com.thales.technicalTest.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetByIdEmployeeApiResponse {
  private String status;
  private EmployeeApiResponse data;
  private String message;
}
