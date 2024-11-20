package com.nixstech.pdp.repository;

import com.nixstech.pdp.entity.OrderBasket;
import com.nixstech.pdp.entity.Product;
import com.nixstech.pdp.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface OrderBasketRepository extends JpaRepository<OrderBasket, Integer> {

  List<OrderBasket> findByUser(User user);

  OrderBasket findByUserAndProduct(User user, Product product);

  @Query(
      "UPDATE OrderBasket orderBasket SET orderBasket.quantity = ?1 WHERE orderBasket.product.id = ?2 "
          +
          "AND orderBasket.user.id = ?3")
  @Modifying
  void updateQuantity(Integer quantity, Integer productId, Integer userId);

  @Query("DELETE FROM OrderBasket orderBasket WHERE orderBasket.user.id = ?1 AND orderBasket.product.id = ?2")
  @Modifying
  void deleteByUserAndProduct(Integer userId, Integer productId);

}