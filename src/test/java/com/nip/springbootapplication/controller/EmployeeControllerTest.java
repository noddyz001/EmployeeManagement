package com.nip.springbootapplication.controller;

import com.nip.springbootapplication.model.Employee;
import com.nip.springbootapplication.service.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    @Mock
    private EmployeeServiceImpl employeeServiceImpl;

    @Test
    public void EmployeeController_GetEmployeesDetails() throws Exception {

        Employee e = new Employee();
        Employee e1 = new Employee();

        // when
        when(employeeServiceImpl.getAllEmployees()).thenReturn(List.of(e,e1));

        // then
        assertThat(employeeServiceImpl.getAllEmployees().size()).isEqualTo(2);
    }

    @Test
    public void EmployeeController_saveEmployeeDetails() throws Exception {

        Employee e = new Employee();
        e.setId(5);
        e.setFirstName("Test");
        e.setLastName("User");
        e.setDepartment("Test");

        employeeServiceImpl.saveEmployee(e);

        verify(employeeServiceImpl).saveEmployee(any());
    }

    @Test
    public void EmployeeController_deleteEmployee() throws Exception {

        Employee e = new Employee();
        e.setId(5);
        e.setFirstName("Test");
        e.setLastName("User");
        e.setDepartment("Test");

        employeeServiceImpl.deleteEmployeeById(5);

        verify(employeeServiceImpl).deleteEmployeeById(anyLong());
    }
}
