package br.com.techthor.tacocloud.service;

import br.com.techthor.tacocloud.model.TacoOrder;
import br.com.techthor.tacocloud.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<TacoOrder> findAll() {
        return (List<TacoOrder>) orderRepository.findAll();
    }

    public TacoOrder save(TacoOrder order) {
        return orderRepository.save(order);
    }
}

