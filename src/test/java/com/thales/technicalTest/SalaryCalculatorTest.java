package com.thales.technicalTest;

import com.thales.technicalTest.services.SalaryCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SalaryCalculatorTest {

  @InjectMocks
  private SalaryCalculator salaryCalculator;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testGetAnnualSalary() {
    Integer monthlySalary = 320800;
    Integer annualSalaryExpected = 3849600;

    Integer annualSalary = salaryCalculator.calculateAnnualSalary(monthlySalary);

    assertEquals(annualSalaryExpected, annualSalary);
  }
}
