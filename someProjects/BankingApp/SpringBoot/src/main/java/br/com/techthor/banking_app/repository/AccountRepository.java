package br.com.techthor.banking_app.repository;

import br.com.techthor.banking_app.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
