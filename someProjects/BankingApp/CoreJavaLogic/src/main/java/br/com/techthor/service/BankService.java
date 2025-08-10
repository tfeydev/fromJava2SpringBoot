package br.com.techthor.service;

import br.com.techthor.domain.Account;
import br.com.techthor.domain.Customer;
import br.com.techthor.domain.Transaction;

import java.util.ArrayList;
import java.util.List;

public class BankService {

    private List<Customer> customers = new ArrayList<>();
    private List<Account> accounts = new ArrayList<>();
    private List<Transaction> transactions = new ArrayList<>();

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void deposit(Long accountId, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        Account account = accounts.stream()
                .filter(a -> a.getId().equals(accountId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
        account.setBalance(account.getBalance() + amount);
        transactions.add(new Transaction(transactions.size() + 1L, accountId, amount, "deposit"));
    }

    public void withdraw(Long accountId, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        Account account = accounts.stream()
                .filter(a -> a.getId().equals(accountId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
        if (account.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        account.setBalance(account.getBalance() - amount);
        transactions.add(new Transaction(transactions.size() + 1L, accountId, amount, "withdrawal"));
    }

    public List<Customer> getCustomers() {
        return new ArrayList<>(customers);
    }

    public List<Account> getAccounts() {
        return new ArrayList<>(accounts);
    }

    public List<Transaction> getTransactions() {
        return new ArrayList<>(transactions);
    }

}
