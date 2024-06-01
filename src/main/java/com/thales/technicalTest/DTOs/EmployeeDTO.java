package com.thales.technicalTest.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeDTO {
  private Integer id;
  private String name;
  private Integer salary;
  private Integer age;
  private String image;
  private Integer annualSalary;
}
