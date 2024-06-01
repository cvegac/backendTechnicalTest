package com.thales.technicalTest;

import com.thales.technicalTest.DTOs.EmployeeDTO;
import com.thales.technicalTest.models.Employee;
import com.thales.technicalTest.repositories.EmployeeRepository;
import com.thales.technicalTest.services.EmployeeService;
import com.thales.technicalTest.services.SalaryCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

public class EmployeeServiceTest {
  @Mock
  private EmployeeRepository employeeRepository;

  @Mock
  private SalaryCalculator salaryCalculator;

  @InjectMocks
  private EmployeeService employeeService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testGetAll() {
    List<Employee> employees = Arrays.asList(
            new Employee(1, "Tiger Nixon", 320800, 61, ""),
            new Employee(2, "Garrett Winters", 170750, 63, "")
    );

    List<EmployeeDTO> expectedEmployeeDTOs = Arrays.asList(
            new EmployeeDTO(1, "Tiger Nixon", 320800, 61, "", 3849600),
            new EmployeeDTO(2, "Garrett Winters", 170750, 63, "", 2049000)
    );

    when(employeeRepository.findAll()).thenReturn(employees);

    when(salaryCalculator.calculateAnnualSalary(anyInt())).thenReturn(3849600, 2049000);

    List<EmployeeDTO> actualEmployeeDTOs = employeeService.getAll();

    assertEquals(expectedEmployeeDTOs, actualEmployeeDTOs);
  }

  @Test
  void testGetById() {
    Integer id = 1;

    Employee employee = new Employee(id, "Tiger Nixon", 320800, 61, "");

    EmployeeDTO expectedEmployeeDTO = new EmployeeDTO(id, "Tiger Nixon", 320800, 61, "", 3849600);

    when(employeeRepository.findById(id)).thenReturn(employee);

    when(salaryCalculator.calculateAnnualSalary(anyInt())).thenReturn(3849600);

    EmployeeDTO actualEmployeeDTO = employeeService.getById(id);

    assertEquals(expectedEmployeeDTO, actualEmployeeDTO);
  }
}
