package br.com.techthor.tacocloud.controller;

import br.com.techthor.tacocloud.model.TacoOrder;
import br.com.techthor.tacocloud.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<TacoOrder> getAllOrders() {
        return (List<TacoOrder>) orderService.findAll();
    }

    @PostMapping
    public TacoOrder createOrder(@RequestBody TacoOrder order) {
        return orderService.save(order);
    }

}
