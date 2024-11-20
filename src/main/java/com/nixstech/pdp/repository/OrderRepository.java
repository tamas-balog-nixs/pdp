package com.nixstech.pdp.repository;

import com.nixstech.pdp.entity.Order;
import com.nixstech.pdp.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {

  Order findByUser(User user);

  List<Order> findOrdersByUser(User user);

  Long countById(Integer id);

}
