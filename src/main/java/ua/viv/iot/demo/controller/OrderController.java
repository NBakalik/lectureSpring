package ua.viv.iot.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.viv.iot.demo.exceptions.OrderNotFoundException;
import ua.viv.iot.demo.model.Order;
import ua.viv.iot.demo.service.OrderService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

@RestController
@RequestMapping("/order")
public class OrderController {

    private static final Logger LOGGER = Logger.getLogger("ua.viv.iot.demo.controller.OrderController");

    @Autowired
    private OrderService orderService;

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable(name = "id") Integer id) {
        try {
            return new ResponseEntity<Order>(orderService.getOrder(id), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping()
    public List<Order> getOrders() {
        return orderService.getOrders();
    }

    @PutMapping()
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        if (order.getId() == null) {
            return new ResponseEntity<Order>(orderService.addOrder(order), HttpStatus.OK);
        }
        LOGGER.severe("Failed to create an order with passed id. Order creation should not use externals ids");
        return new ResponseEntity<Order>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping()
    public ResponseEntity<Order> updateOrder(@RequestBody Order order) {
        if (order.getId() == null) {
            LOGGER.severe("Can`t update an order without id - null value was passed instead of it");
            return new ResponseEntity<Order>(HttpStatus.BAD_REQUEST);
        }

        try {
            return new ResponseEntity<Order>(orderService.updateOrder(order), HttpStatus.OK);
        } catch (OrderNotFoundException e) {
            LOGGER.severe("Can`t update an order with non-existing id: " + order.getId());
            return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
        }
    }
}
