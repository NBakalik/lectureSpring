package ua.viv.iot.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;
import ua.viv.iot.demo.exceptions.OrderNotFoundException;
import ua.viv.iot.demo.exceptions.OrderNotFoundException;
import ua.viv.iot.demo.model.Order;
import ua.viv.iot.demo.repo.OrderRepository;

import java.util.List;

@Service
@ApplicationScope
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Order getOrder(Integer id) {
        return orderRepository.findById(id).orElseThrow();
    }

    public Order updateOrder(Order order) throws OrderNotFoundException {
        if (orderRepository.existsById(order.getId())) {
            return orderRepository.save(order);
        }
        throw new OrderNotFoundException("order not found");
    }
}
