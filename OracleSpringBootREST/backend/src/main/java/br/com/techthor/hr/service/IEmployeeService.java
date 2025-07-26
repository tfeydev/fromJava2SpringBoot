package br.com.techthor.hr.service;

import br.com.techthor.hr.dto.EmployeeDTO;
import br.com.techthor.hr.dto.EmployeePageDTO;

public interface IEmployeeService {
    EmployeePageDTO getAll(int page, int size);
    EmployeeDTO getById(Long id);
}