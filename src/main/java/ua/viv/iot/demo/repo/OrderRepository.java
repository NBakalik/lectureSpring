package ua.viv.iot.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.viv.iot.demo.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
