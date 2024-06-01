package com.thales.technicalTest.controllers;

import com.thales.technicalTest.DTOs.EmployeeDTO;
import com.thales.technicalTest.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

  @Autowired
  protected EmployeeService employeeService;

  @CrossOrigin(origins = "http://localhost:4200")
  @GetMapping("")
  public List<EmployeeDTO> getAll() {
    return employeeService.getAll();
  }

  @CrossOrigin(origins = "http://localhost:4200")
  @GetMapping("/{id}")
  public EmployeeDTO getById(@PathVariable Integer id) {
    return employeeService.getById(id);
  }
}
