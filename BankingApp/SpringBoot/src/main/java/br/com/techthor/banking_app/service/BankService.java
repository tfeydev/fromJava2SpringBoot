package br.com.techthor.banking_app.service;


import br.com.techthor.banking_app.domain.Account;
import br.com.techthor.banking_app.domain.Customer;
import br.com.techthor.banking_app.domain.Transaction;
import br.com.techthor.banking_app.repository.AccountRepository;
import br.com.techthor.banking_app.repository.CustomerRepository;
import br.com.techthor.banking_app.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BankService {
    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public BankService(CustomerRepository customerRepository, AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public void addCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public void addAccount(Account account) {
        accountRepository.save(account);
    }

    public void deposit(Long accountId, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
        account.setBalance(account.getBalance() + amount);
        transactionRepository.save(new Transaction(transactionRepository.count() + 1L, accountId, amount, "deposit"));
    }

    public void withdraw(Long accountId, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
        if (account.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        account.setBalance(account.getBalance() - amount);
        transactionRepository.save(new Transaction(transactionRepository.count() + 1L, accountId, amount, "withdrawal"));
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    public List<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

}
