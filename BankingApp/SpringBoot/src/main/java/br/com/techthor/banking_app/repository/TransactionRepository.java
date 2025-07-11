package br.com.techthor.banking_app.repository;

import br.com.techthor.banking_app.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
