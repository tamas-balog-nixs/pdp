package com.nixstech.pdp.service.impl;

import com.nixstech.pdp.dto.CategoryDto;
import com.nixstech.pdp.entity.Category;
import com.nixstech.pdp.exception.CategoryNotFoundException;
import com.nixstech.pdp.mapper.CategoryMapper;
import com.nixstech.pdp.repository.CategoryRepository;
import com.nixstech.pdp.service.CategoryService;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;
  private final CategoryMapper mapper;

  @Override
  public Category getCategory(Integer id) {
    try {
      return categoryRepository.getReferenceById(id);
    } catch (NoSuchElementException e) {
      throw new CategoryNotFoundException("Couldn't find any category with id " + id);
    }
  }

  @Override
  public List<CategoryDto> getCategories() {
    return categoryRepository.findAll().stream().map(mapper::toDto).toList();
  }
}
