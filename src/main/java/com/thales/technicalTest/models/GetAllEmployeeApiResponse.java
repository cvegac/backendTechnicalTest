package com.thales.technicalTest.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GetAllEmployeeApiResponse {
  private String status;
  private List<EmployeeApiResponse> data;
  private String message;
}
