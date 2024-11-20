package com.nixstech.pdp.controller;

import com.nixstech.pdp.dto.NewProductDto;
import com.nixstech.pdp.dto.ProductDto;
import com.nixstech.pdp.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

  private final ProductService productService;

  @GetMapping
  public List<ProductDto> getProductsByCategoryId(@RequestParam("catId") Integer catId) {
    return productService.getProductByCategoryId(catId);
  }

  @PostMapping
  public ProductDto createProduct(@RequestBody NewProductDto productDto) {
    return productService.createProduct(productDto);
  }
}
