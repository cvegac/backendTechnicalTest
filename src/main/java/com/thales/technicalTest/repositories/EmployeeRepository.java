package com.thales.technicalTest.repositories;

import com.thales.technicalTest.models.Employee;

import java.util.List;

public interface EmployeeRepository {
  List<Employee> findAll();
  Employee findById(Integer id);
}
