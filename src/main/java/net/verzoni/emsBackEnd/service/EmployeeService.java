package net.verzoni.emsBackEnd.service;

import net.verzoni.emsBackEnd.dto.EmployeeDto;
import net.verzoni.emsBackEnd.exception.DuplicateEmailException;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto) throws DuplicateEmailException;

    EmployeeDto getEmployeeById(Long Id);

    List<EmployeeDto> getAllEmployee();

    EmployeeDto updateEmployee(EmployeeDto updatedEmployee , Long Id);

    void deleteEmployee(Long Id);
}
