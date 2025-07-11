package br.com.techthor.banking_app.controller;

import br.com.techthor.banking_app.domain.Account;
import br.com.techthor.banking_app.domain.Customer;
import br.com.techthor.banking_app.domain.CustomerAccountView;
import br.com.techthor.banking_app.domain.Transaction;
import br.com.techthor.banking_app.repository.CustomerAccountViewRepository;
import br.com.techthor.banking_app.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class BankingController {

    @Autowired
    private BankService bankService;

    @Autowired
    private CustomerAccountViewRepository customerAccountViewRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @GetMapping("/customer-account-view")
    public List<Map<String, Object>> getCustomerAccountView() {
        return jdbcTemplate.queryForList("SELECT * FROM customer_account_view ORDER BY customer_id;");
    }

    /*@GetMapping("/customer-account-view")
    public Iterable<CustomerAccountView> getCustomerAccountView() {
        return customerAccountViewRepository.findAll();
    }*/

    @GetMapping("/")
    public String welcomeMessage() {
        return "Hello and Welcome";
    }

    /*@GetMapping("/customer-account-view")
        public Iterable<CustomerAccountView> getCustomerAccountView() {
            return customerAccountViewRepository.findAll();
        }*/

    @GetMapping("/customers")
    public Iterable<Customer> getAllCustomers() {
        return bankService.getCustomers();
    }

    @GetMapping("/accounts")
    public Iterable<Account> getAllAccounts() {
        return bankService.getAccounts();
    }

    @GetMapping("/transactions")
    public Iterable<Transaction> getAllTransactions() {
        return bankService.getTransactions();
    }

    @GetMapping("/customers/{id}")
    public Customer getCustomer(@PathVariable Long id) {
        Optional<Customer> customer = bankService.getCustomers().stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
        return customer.orElse(null);
    }

    @GetMapping("/accounts/{id}")
    public Account getAccount(@PathVariable Long id) {
        Optional<Account> account = bankService.getAccounts().stream()
                .filter(a -> a.getId().equals(id))
                .findFirst();
        return account.orElse(null);
    }

    @GetMapping("/transactions/{id}")
    public Transaction getTransaction(@PathVariable Long id) {
        Optional<Transaction> transaction = bankService.getTransactions().stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();
        return transaction.orElse(null);
    }

}
