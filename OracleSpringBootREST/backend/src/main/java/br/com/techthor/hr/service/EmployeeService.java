package br.com.techthor.hr.service;

import br.com.techthor.hr.dto.EmployeeDTO;
import br.com.techthor.hr.dto.EmployeePageDTO;
import br.com.techthor.hr.entity.Employee;
import br.com.techthor.hr.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public EmployeePageDTO getAll(int page, int size) {
        Page<Employee> result = repository.findAll(PageRequest.of(page, size));
        List<EmployeeDTO> dtoList = result.getContent().stream().map(this::toDTO).toList();

        EmployeePageDTO dtoPage = new EmployeePageDTO();
        dtoPage.setContent(dtoList);
        dtoPage.setTotalElements(result.getTotalElements());

        return dtoPage;
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
