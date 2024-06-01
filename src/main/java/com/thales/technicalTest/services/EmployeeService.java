package com.thales.technicalTest.services;

import com.thales.technicalTest.DTOs.EmployeeDTO;
import com.thales.technicalTest.models.Employee;
import com.thales.technicalTest.repositories.EmployeeAPIRepository;
import com.thales.technicalTest.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

  private final EmployeeRepository employeeRepository;

  @Autowired
  private SalaryCalculator salaryCalculator;

  @Autowired
  public EmployeeService(RestTemplate restTemplate) {
    this.employeeRepository = new EmployeeAPIRepository(restTemplate);
  }

  public List<EmployeeDTO> getAll(){
    List<Employee> employees = employeeRepository.findAll();

    return employees.stream()
            .map(this::convertToEmployeeDTO)
            .collect(Collectors.toList());
  }

  public EmployeeDTO getById(Integer id){
    Employee employee = employeeRepository.findById(id);
    return convertToEmployeeDTO(employee);
  }

  private EmployeeDTO convertToEmployeeDTO(Employee employee) {
    return new EmployeeDTO(
            employee.getId(),
            employee.getName(),
            employee.getSalary(),
            employee.getAge(),
            employee.getImage(),
            salaryCalculator.calculateAnnualSalary(employee.getSalary())
    );
  }
}
