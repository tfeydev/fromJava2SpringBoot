package br.com.techthor.tacocloud.web;

import jakarta.validation.Valid;

import br.com.techthor.tacocloud.repositories.OrderRepository;
import br.com.techthor.tacocloud.TacoOrder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
// import org.springframework.web.bind.annotation.SessionAttributes; // Thymeleaf-related, commented out
import org.springframework.web.bind.support.SessionStatus;

@Slf4j
// @Controller  // old annotation for Thymeleaf
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/orders")
// @SessionAttributes("tacoOrder") // Thymeleaf-related, commented out
public class OrderController {

    public final OrderRepository orderRepo;

    public OrderController(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;

    }

    /*
    // Thymeleaf-based Method, commented out
    @GetMapping("/current")
    public String orderForm(Model model) {
        model.addAttribute("tacoOrder", new TacoOrder());
        return "orderForm";
    }
    */

    @GetMapping("/current")
    public TacoOrder getCurrentOrder() {
        return new TacoOrder(); // for React, delivers empty Orderobject
    }


    // Thymeleaf-based Method, commented out
    /*@PostMapping
    public String processOrder(@Valid TacoOrder order,
                               Errors errors,
                               SessionStatus sessionStatus) {

        if (errors.hasErrors()) {
            return "orderForm";
        }

        log.info("Order submitted: {}", order);
        sessionStatus.setComplete();

        return "redirect:/";
    }*/

    @PostMapping
    public ResponseEntity<?> processOrder(@Valid @RequestBody TacoOrder order, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors.getAllErrors());
        }
        TacoOrder saved = orderRepo.save(order);
        return ResponseEntity.ok(saved);
    }

}
