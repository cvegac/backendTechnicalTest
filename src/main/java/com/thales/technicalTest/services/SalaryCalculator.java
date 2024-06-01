package com.thales.technicalTest.services;

import org.springframework.stereotype.Service;

@Service
public class SalaryCalculator {

  public Integer calculateAnnualSalary(Integer monthlySalary) {
    return monthlySalary * 12;
  }

}
