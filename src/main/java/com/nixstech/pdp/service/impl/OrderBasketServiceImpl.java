package com.nixstech.pdp.service.impl;

import com.nixstech.pdp.entity.OrderBasket;
import com.nixstech.pdp.entity.Product;
import com.nixstech.pdp.entity.User;
import com.nixstech.pdp.repository.OrderBasketRepository;
import com.nixstech.pdp.repository.ProductRepository;
import com.nixstech.pdp.service.OrderBasketService;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderBasketServiceImpl implements OrderBasketService {

  private final OrderBasketRepository orderBasketRep;
  private final ProductRepository productRep;

  @Override
  public List<OrderBasket> getAllOrderBaskets() {
    List<OrderBasket> orderBasket = orderBasketRep.findAll();
    if (orderBasket.isEmpty()) {
      throw new IllegalArgumentException("Couldn't find any product in DB");
    }
    return orderBasket;
  }

  @Override
  public List<OrderBasket> listOrderBasket(User user) {
    return orderBasketRep.findByUser(user);
  }


  @Override
  public Integer addProduct(Integer productId, Integer quantity, User user) {
    Integer addedQuantity = quantity;

    Product product = productRep.getReferenceById(productId);

    OrderBasket orderBasket = orderBasketRep.findByUserAndProduct(user, product);

    if (orderBasket != null) {
      addedQuantity = orderBasket.getQuantity() + quantity;
      orderBasket.setQuantity(addedQuantity);
    }
    //The product hasn't been added to the shopping card by this user
    else {
      orderBasket = new OrderBasket();
      orderBasket.setQuantity(quantity);
      orderBasket.setUser(user);
      orderBasket.setProduct(product);
    }
    orderBasketRep.save(orderBasket);
    return addedQuantity;
  }

  @Override
  public float updateQuantity(Integer productId, Integer quantity, User user) {
    orderBasketRep.updateQuantity(quantity, productId, user.getId());
    Product product = productRep.getReferenceById(productId);

    return product.getPrice() * (float) quantity;
  }

  @Override
  public void removeProduct(Integer productId, User user) {
    orderBasketRep.deleteByUserAndProduct(user.getId(), productId);
  }
}

