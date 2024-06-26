package com.thales.technicalTest.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Employee {
  private Integer id;
  private String name;
  private Integer salary;
  private Integer age;
  private String image;
}
