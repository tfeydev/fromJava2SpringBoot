package br.com.techthor.hr.dto;

import br.com.techthor.hr.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String hireDate;
    private String jobId;

    public EmployeeDTO(Employee e) {
        this.id = e.getId() != null ? e.getId() : 0L;
        this.firstName = e.getFirstName() != null ? e.getFirstName() : "";
        this.lastName = e.getLastName() != null ? e.getLastName() : "";
        this.email = e.getEmail() != null ? e.getEmail() : "";
        this.phone = e.getPhone() != null ? e.getPhone() : "";
        this.hireDate = e.getHireDate() != null ? e.getHireDate().toString() : "";
        this.jobId = e.getJobId() != null ? e.getJobId() : "";
    }
    
}
