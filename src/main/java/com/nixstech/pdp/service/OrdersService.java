package com.nixstech.pdp.service;

import com.nixstech.pdp.entity.Order;
import com.nixstech.pdp.entity.OrderBasket;
import com.nixstech.pdp.entity.User;
import com.nixstech.pdp.exception.OrderNotFoundException;
import java.util.List;
import org.springframework.data.domain.Page;

public interface OrdersService {

  List<Order> getAllOrders();

  List<Order> getAllOrdersByUser(User user);

  void saveOrder(Order orders);

  Order getOrder(int id);

  Order getOrderByUser(User user) throws OrderNotFoundException;

  float countSum(List<OrderBasket> orderBaskets);

  void deleteOrder(int id) throws OrderNotFoundException;


  Page<Order> listByPage(int pageNum);
}
