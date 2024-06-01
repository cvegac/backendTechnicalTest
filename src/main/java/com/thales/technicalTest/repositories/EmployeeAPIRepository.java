package com.thales.technicalTest.repositories;

import com.thales.technicalTest.exceptions.ResourceNotFoundException;
import com.thales.technicalTest.models.Employee;
import com.thales.technicalTest.models.EmployeeApiResponse;
import com.thales.technicalTest.models.GetAllEmployeeApiResponse;
import com.thales.technicalTest.models.GetByIdEmployeeApiResponse;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EmployeeAPIRepository implements EmployeeRepository{

  private final RestTemplate restTemplate;

  public EmployeeAPIRepository(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Override
  public List<Employee> findAll() {
    String allEmployeesUrl = "http://dummy.restapiexample.com/api/v1/employees";

    GetAllEmployeeApiResponse getAllEmployeeApiResponse = restTemplate.getForObject(allEmployeesUrl, GetAllEmployeeApiResponse.class);

    assert getAllEmployeeApiResponse != null;

    if (getAllEmployeeApiResponse.getData() == null) {
      throw new ResourceNotFoundException("Employees not found");
    }

    return getAllEmployeeApiResponse.getData().stream()
            .map(this::convertToEmployee)
            .collect(Collectors.toList());
  }

  @Override
  public Employee findById(Integer id) {
    String byIdEmployeeUrl = "http://dummy.restapiexample.com/api/v1/employee/" + id;

    GetByIdEmployeeApiResponse getByIdEmployeeApiResponse = restTemplate.getForObject(byIdEmployeeUrl, GetByIdEmployeeApiResponse.class);

    assert getByIdEmployeeApiResponse != null;

    if (getByIdEmployeeApiResponse.getData() == null) {
      throw new ResourceNotFoundException("Employee with id " + id + " not found");
    }

    return convertToEmployee(getByIdEmployeeApiResponse.getData());
  }

  private Employee convertToEmployee(EmployeeApiResponse employeeApiResponse) {
    return new Employee(
            employeeApiResponse.getId(),
            employeeApiResponse.getEmployee_name(),
            employeeApiResponse.getEmployee_salary(),
            employeeApiResponse.getEmployee_age(),
            employeeApiResponse.getProfile_image()
    );
  }

}
