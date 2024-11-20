package com.nixstech.pdp.service.impl;

import com.nixstech.pdp.dto.NewProductDto;
import com.nixstech.pdp.dto.ProductDto;
import com.nixstech.pdp.entity.Product;
import com.nixstech.pdp.exception.ProductNotFoundException;
import com.nixstech.pdp.mapper.ProductMapper;
import com.nixstech.pdp.repository.ProductRepository;
import com.nixstech.pdp.service.CategoryService;
import com.nixstech.pdp.service.ProductService;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  public static final int PRODUCTS_PER_PAGE = 10;
  private final ProductRepository productRepository;
  private final ProductMapper mapper;
  private final CategoryService categoryService;

  @Override
  public Page<Product> listByCategory(int pageNum, Integer categoryId) {
    String categoryIdMatch = "-" + categoryId + "-";
    Pageable pageable = PageRequest.of(pageNum - 1, PRODUCTS_PER_PAGE);

    return productRepository.listByCategory(categoryId, pageable, categoryIdMatch);
  }

  @Override
  public List<Product> getAllProducts() throws ProductNotFoundException {
    List<Product> listProducts = productRepository.findAll();
    if (listProducts.isEmpty()) {
      throw new ProductNotFoundException("Couldn't find any product in DB");
    }
    return listProducts;
  }

  @Override
  public List<Product> getRandomAmountOfProducts() throws ProductNotFoundException {
    List<Product> productList = productRepository.findAllByCategoryId(4);
    if (productList.isEmpty()) {
      throw new ProductNotFoundException("Couldn't find any product in DB");
    }
    Collections.shuffle(productList);
    int randomSeriesLength = 8;
    return productList.subList(0, randomSeriesLength);

  }

  @Override
  public void saveProduct(Product product) {
    productRepository.save(product);
  }

  @Override
  public Product getProduct(Integer id) throws ProductNotFoundException {
    try {
      return productRepository.getReferenceById(id);
    } catch (NoSuchElementException e) {
      throw new ProductNotFoundException("Couldn't find any product with id " + id);
    }
  }

  @Override
  public Product getProduct(String alias) throws ProductNotFoundException {
    try {
      return productRepository.findByAlias(alias);
    } catch (NoSuchElementException e) {
      throw new ProductNotFoundException("Couldn't find any product with alias " + alias);
    }
  }

  @Override
  public void deleteProduct(Integer id) throws ProductNotFoundException {
    Long countById = productRepository.countById(id);
    if (countById == null || countById == 0) {
      throw new ProductNotFoundException("Couldn't find any product with ID " + id);
    }
    productRepository.deleteById(id);
  }

  @Override
  public String checkUnique(Integer id, String title) {
    boolean isCreatingNew = (id == null || id == 0);
    Product productByName = productRepository.findByTitle(title);
    if (isCreatingNew) {
      if (productByName != null) {
        return "Duplicate";
      }
    } else {
      if (productByName != null && !Objects.equals(productByName.getId(), id)) {
        return "Duplicate";
      }
    }
    return "OK";
  }

  @Override
  public List<ProductDto> getProductByCategoryId(Integer catId) {
    return productRepository.findAllByCategoryId(catId).stream().map(mapper::toDto).toList();
  }

  @Override
  public ProductDto createProduct(NewProductDto productDto) {
    Product product = mapper.toEntity(productDto);
    product.setCategory(categoryService.getCategory(productDto.catId()));
    return mapper.toDto(productRepository.save(product));
  }
}
