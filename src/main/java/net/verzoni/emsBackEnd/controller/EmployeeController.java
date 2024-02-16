package net.verzoni.emsBackEnd.controller;

import lombok.AllArgsConstructor;
import net.verzoni.emsBackEnd.dto.EmployeeDto;
import net.verzoni.emsBackEnd.service.EmployeeService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin( origins = "http://localhost:3000")
@RequestMapping("/api/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    // Build Add Employee REST API
    @PostMapping
    // l'annotation @RequestBody riceve un json e lo mette nel dto
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }


    // Get Employee by Id REST API
    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long Id) {
        EmployeeDto employeeDto = employeeService.getEmployeeById(Id);
        return ResponseEntity.ok(employeeDto);
    }

    // All Employees REST API
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        List<EmployeeDto> employeesList = employeeService.getAllEmployee();
        return ResponseEntity.ok(employeesList);
    }

    // Update (put) Employee by Id REST API
    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto updatedEmployee , @PathVariable("id") Long Id){
        EmployeeDto putEmployee = employeeService.updateEmployee(updatedEmployee,Id);
        return ResponseEntity.ok(putEmployee);
    }

    // Delete Employee by Id REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long Id) {
        employeeService.deleteEmployee(Id);
        return  ResponseEntity.ok("Employee "+Id+ " deleted");
    }
}
