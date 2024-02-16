package net.verzoni.emsBackEnd.service.impl;

import lombok.AllArgsConstructor;
import net.verzoni.emsBackEnd.dto.EmployeeDto;
import net.verzoni.emsBackEnd.entity.Employee;
import net.verzoni.emsBackEnd.exception.DuplicateEmailException;
import net.verzoni.emsBackEnd.exception.ResourceNotFoundException;
import net.verzoni.emsBackEnd.mapper.EmployeeMapper;
import net.verzoni.emsBackEnd.repository.EmployeeRepository;
import net.verzoni.emsBackEnd.service.EmployeeService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto)  {

        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        try {
            Employee savedEmployee = employeeRepository.save(employee);
            return EmployeeMapper.mapToEmployeeDto(savedEmployee);
        } catch (DataAccessException ex) {
            System.out.println("createEmployee exception : " + ex.getCause().getMessage() + "*****");
            throw new DuplicateEmailException("Duplicate Email");
        }
    }


    @Override
    public EmployeeDto getEmployeeById(Long Id) {

       Employee employee = employeeRepository.findById(Id)
               .orElseThrow( ()-> new ResourceNotFoundException("Employee Not Found with Id="+Id));
        return EmployeeMapper.mapToEmployeeDto(employee);

    }

    @Override
    public List<EmployeeDto> getAllEmployee() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map( (employee)-> EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto updatedEmployee, Long Id) {
        Employee employee = employeeRepository.findById(Id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee Not Found with Id="+Id));

        if ( updatedEmployee.getFirstName() != null) {
            employee.setFirstName(updatedEmployee.getFirstName());
        }
        if ( updatedEmployee.getLastName() != null) {
            employee.setLastName(updatedEmployee.getLastName());
        }
        if ( updatedEmployee.getEmail() != null) {
            employee.setEmail(updatedEmployee.getEmail());
        }
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);

    }

    @Override
    public void deleteEmployee(Long Id) {
        Employee employee = employeeRepository.findById(Id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee Not Found with Id="+Id));
        employeeRepository.deleteById(Id);
        long count = employeeRepository.count();
        System.out.println("Number of rows now is = "+count);
    }
}
