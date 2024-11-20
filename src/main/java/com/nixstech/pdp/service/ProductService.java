package com.nixstech.pdp.service;

import com.nixstech.pdp.dto.NewProductDto;
import com.nixstech.pdp.dto.ProductDto;
import com.nixstech.pdp.entity.Product;
import com.nixstech.pdp.exception.ProductNotFoundException;
import java.util.List;
import org.springframework.data.domain.Page;

public interface ProductService {

  List<Product> getAllProducts() throws ProductNotFoundException;

  List<Product> getRandomAmountOfProducts() throws ProductNotFoundException;

  Page<Product> listByCategory(int pageNum, Integer categoryId);

  void saveProduct(Product product);

  Product getProduct(Integer id) throws ProductNotFoundException;

  Product getProduct(String alias) throws ProductNotFoundException;

  void deleteProduct(Integer id) throws ProductNotFoundException;


  String checkUnique(Integer id, String title);

  ProductDto createProduct(NewProductDto productDto);

  List<ProductDto> getProductByCategoryId(Integer catId);
}
