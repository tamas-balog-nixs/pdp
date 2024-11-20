package com.nixstech.pdp.repository;

import com.nixstech.pdp.entity.Product;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Integer> {

  @Query(
      "SELECT product FROM Product product WHERE (product.category.id = ?1 OR product.category.allParentsIDs LIKE %?2%)"
          + "ORDER BY product.title ASC")
  Page<Product> listByCategory(Integer categoryId, Pageable pageable,
      String categoryIDMatch);

  Product findByAlias(String alias);

  Product findByTitle(String title);

  List<Product> findAllByCategoryId(Integer category);

  Long countById(Integer id);
}
