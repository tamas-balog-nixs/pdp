package com.nixstech.pdp.controller;

import com.nixstech.pdp.dto.CategoryDto;
import com.nixstech.pdp.service.CategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

  private final CategoryService categoryService;

  @GetMapping
  public List<CategoryDto> getCategories() {
    return categoryService.getCategories();
  }
}
