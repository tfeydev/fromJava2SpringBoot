package br.com.techthor;

import br.com.techthor.domain.Account;
import br.com.techthor.domain.Customer;
import br.com.techthor.service.BankService;

public class Main {

    public static void main(String[] args) {

        BankService bankService = new BankService();

        // sample data
        Customer customer = new Customer(1L, "John Doe", "john@example.com");
        Account account = new Account(1L, 1L, "1234567890", 1000.00);

        bankService.addCustomer(customer);
        bankService.addAccount(account);

        // test operations
        try {
            bankService.deposit(1L, 500.00);
            bankService.withdraw(1L, 200.00);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // print results
        System.out.println("Customer: " + bankService.getCustomers());
        System.out.println("Accounts: " + bankService.getAccounts());
        System.out.println("Transactions: " + bankService.getTransactions());
    }
}
