package br.com.techthor.hr.controller;

import br.com.techthor.hr.dto.EmployeeDTO;
import br.com.techthor.hr.dto.EmployeePageDTO;
import br.com.techthor.hr.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
@Transactional("hrTransactionManager")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService service;

    @GetMapping
    public EmployeePageDTO getAll(@RequestParam int page, @RequestParam int size) {
        return service.getAll(page, size);
    }

    @GetMapping("/{id}")
    public EmployeeDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

}
