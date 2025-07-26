package br.com.techthor.hr.controller;

import br.com.techthor.hr.entity.Employee;
import br.com.techthor.hr.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@Transactional("hrTransactionManager")
public class EmployeeController {

    private final EmployeeRepository repository;

    public EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Page<Employee> getAll(@RequestParam int page, @RequestParam int size) {
        return repository.findAll(PageRequest.of(page, size));
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable("id") Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Employee with ID "
                        + id + " not found")
                );
    }

}
