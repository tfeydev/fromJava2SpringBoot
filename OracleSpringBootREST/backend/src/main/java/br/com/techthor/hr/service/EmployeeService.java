package br.com.techthor.hr.service;

import br.com.techthor.hr.dto.EmployeeDTO;
import br.com.techthor.hr.dto.EmployeePageDTO;
import br.com.techthor.hr.entity.Employee;
import br.com.techthor.hr.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService implements IEmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public EmployeePageDTO getAll(int page, int size) {
        Page<Employee> employeePage = repository.findAll(PageRequest.of(page, size));

        List<EmployeeDTO> content = employeePage.getContent()
            .stream()
            .map(EmployeeDTO::new)
            .toList();

        return new EmployeePageDTO(content, employeePage.getTotalElements());
    }

    public EmployeeDTO getById(Long id) {
        Employee emp = repository.findById(id).orElseThrow();
        return toDTO(emp);
    }

    private EmployeeDTO toDTO(Employee emp) {
        EmployeeDTO dto = new EmployeeDTO();
        BeanUtils.copyProperties(emp, dto);
        dto.setHireDate(emp.getHireDate().toString());
        return dto;
    }

}
