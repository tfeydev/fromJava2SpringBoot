package br.com.techthor.banking_app.repository;

import br.com.techthor.banking_app.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
