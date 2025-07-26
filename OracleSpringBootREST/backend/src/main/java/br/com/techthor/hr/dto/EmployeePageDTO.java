package br.com.techthor.hr.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EmployeePageDTO {

    private List<EmployeeDTO> content;
    private long totalElements;

}
