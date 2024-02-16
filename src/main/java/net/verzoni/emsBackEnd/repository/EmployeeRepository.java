package net.verzoni.emsBackEnd.repository;

import net.verzoni.emsBackEnd.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
