package com.thales.technicalTest.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeApiResponse {
  private Integer id;
  private String employee_name;
  private Integer employee_salary;
  private Integer employee_age;
  private String profile_image;
}
