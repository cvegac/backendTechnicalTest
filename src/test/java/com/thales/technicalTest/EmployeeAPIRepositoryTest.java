package com.thales.technicalTest;

import com.thales.technicalTest.exceptions.ResourceNotFoundException;
import com.thales.technicalTest.models.Employee;
import com.thales.technicalTest.models.EmployeeApiResponse;
import com.thales.technicalTest.models.GetAllEmployeeApiResponse;
import com.thales.technicalTest.models.GetByIdEmployeeApiResponse;
import com.thales.technicalTest.repositories.EmployeeAPIRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EmployeeAPIRepositoryTest {
  @Mock
  private RestTemplate restTemplate;

  @InjectMocks
  private EmployeeAPIRepository employeeAPIRepository;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testFindAll() {
    List<EmployeeApiResponse> employeesApi = Arrays.asList(
            new EmployeeApiResponse(1, "Tiger Nixon", 320800, 61, ""),
            new EmployeeApiResponse(2, "Garrett Winters", 170750, 63, "")
    );

    GetAllEmployeeApiResponse getAllEmployeeApiResponse = new GetAllEmployeeApiResponse("success", employeesApi, "Successfully! All records has been fetched.");

    when(restTemplate.getForObject(anyString(), eq(GetAllEmployeeApiResponse.class))).thenReturn(getAllEmployeeApiResponse);

    List<Employee> employees = employeeAPIRepository.findAll();

    EmployeeApiResponse employeeApi = employeesApi.getFirst();
    Employee employee = employees.getFirst();

    assertNotNull(employees);
    assertFalse(employees.isEmpty());
    assertEquals(2, employees.size());

    assertEquals(employeeApi.getId(), employee.getId());
    assertEquals(employeeApi.getEmployee_name(), employee.getName());
    assertEquals(employeeApi.getEmployee_age(), employee.getAge());
    assertEquals(employeeApi.getEmployee_salary(), employee.getSalary());
    assertEquals(employeeApi.getProfile_image(), employee.getImage());
  }

  @Test
  void testFindById() {
    int employeeId = 1;

    EmployeeApiResponse employeeApi = new EmployeeApiResponse(employeeId, "Tiger Nixon", 320800, 61, "");

    GetByIdEmployeeApiResponse getByIdEmployeeApiResponse = new GetByIdEmployeeApiResponse("success", employeeApi, "Successfully! Record has been fetched.");

    when(restTemplate.getForObject(anyString(), eq(GetByIdEmployeeApiResponse.class))).thenReturn(getByIdEmployeeApiResponse);

    Employee employee = employeeAPIRepository.findById(employeeId);

    assertNotNull(employee);
    assertEquals(employeeId, employee.getId());

    assertEquals(employeeApi.getEmployee_name(), employee.getName());
    assertEquals(employeeApi.getEmployee_age(), employee.getAge());
    assertEquals(employeeApi.getEmployee_salary(), employee.getSalary());
    assertEquals(employeeApi.getProfile_image(), employee.getImage());
  }

  @Test
  void testFindAll_WhenNoDataReturned() {
    GetAllEmployeeApiResponse getAllEmployeeApiResponse = new GetAllEmployeeApiResponse("success", null, "Successfully! All records has been fetched.");

    when(restTemplate.getForObject(anyString(), eq(GetAllEmployeeApiResponse.class))).thenReturn(getAllEmployeeApiResponse);

    assertThrows(ResourceNotFoundException.class, () -> employeeAPIRepository.findAll());
  }

  @Test
  void testFindById_WhenNoDataReturned() {
    int id = 1;
    GetByIdEmployeeApiResponse getByIdEmployeeApiResponse = new GetByIdEmployeeApiResponse("success", null, "Successfully! Record has been fetched.");
    getByIdEmployeeApiResponse.setData(null);
    when(restTemplate.getForObject(anyString(), eq(GetByIdEmployeeApiResponse.class))).thenReturn(getByIdEmployeeApiResponse);

    assertThrows(ResourceNotFoundException.class, () -> employeeAPIRepository.findById(id));
  }
}
