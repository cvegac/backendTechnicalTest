package com.thales.technicalTest.controllers;

import com.thales.technicalTest.DTOs.EmployeeDTO;
import com.thales.technicalTest.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

  @Autowired
  protected EmployeeService employeeService;

  @GetMapping("")
  public List<EmployeeDTO> getAll() {
    return employeeService.getAll();
  }

  @GetMapping("/{id}")
  public EmployeeDTO getById(@PathVariable Integer id) {
    return employeeService.getById(id);
  }
}
