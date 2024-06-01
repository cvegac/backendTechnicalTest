package com.thales.technicalTest;

import com.thales.technicalTest.DTOs.EmployeeDTO;
import com.thales.technicalTest.controllers.EmployeeController;
import com.thales.technicalTest.services.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class EmployeeControllerTest {

  @Mock
  private EmployeeService employeeService;

  @InjectMocks
  private EmployeeController employeeController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testGetAll() {
    List<EmployeeDTO> employeesDTO = Arrays.asList(
            new EmployeeDTO(1, "Tiger Nixon", 320800, 61, "", 3849600),
            new EmployeeDTO(2, "Garrett Winters", 170750, 63, "", 2049000)
    );

    when(employeeService.getAll()).thenReturn(employeesDTO);

    List<EmployeeDTO> actualEmployeesDto = employeeController.getAll();

    assertEquals(employeesDTO, actualEmployeesDto);
  }

  @Test
  void testGetById() {
    Integer employeeId = 1;

    EmployeeDTO employee = new EmployeeDTO(employeeId, "Tiger Nixon", 320800, 61, "", 3849600);

    when(employeeService.getById(employeeId)).thenReturn(employee);

    EmployeeDTO actualEmployee = employeeController.getById(employeeId);

    assertEquals(employee, actualEmployee);
  }
}