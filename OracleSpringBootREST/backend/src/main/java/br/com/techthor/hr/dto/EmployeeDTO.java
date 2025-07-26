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
        this.id = e.getId();
        this.firstName = e.getFirstName();
        this.lastName = e.getLastName();
        this.email = e.getEmail();
        this.phone = e.getPhone();
        this.hireDate = e.getHireDate().toString();
        this.jobId = e.getJobId();
    }
    
}
