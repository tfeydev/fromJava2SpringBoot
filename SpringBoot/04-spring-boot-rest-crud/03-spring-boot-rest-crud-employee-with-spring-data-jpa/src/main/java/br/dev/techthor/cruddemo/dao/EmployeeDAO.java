    package br.dev.techthor.cruddemo.dao;

    import br.dev.techthor.cruddemo.entity.Employee;

    import java.util.List;

    public interface EmployeeDAO {

        List<Employee> findAll();

        Employee findById(int theId);

        Employee save(Employee theEmployee);

        void deleteById(int theId);
    }

