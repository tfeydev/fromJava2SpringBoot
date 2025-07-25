package br.com.techthor.oraclerest.repository;

import br.com.techthor.oraclerest.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
