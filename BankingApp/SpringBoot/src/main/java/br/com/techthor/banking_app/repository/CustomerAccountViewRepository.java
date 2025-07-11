package br.com.techthor.banking_app.repository;

import br.com.techthor.banking_app.domain.CustomerAccountView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerAccountViewRepository extends JpaRepository<CustomerAccountView, Long> {
}
