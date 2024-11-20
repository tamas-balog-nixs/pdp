package com.nixstech.pdp.service;

import com.nixstech.pdp.dto.CategoryDto;
import com.nixstech.pdp.entity.Category;
import com.nixstech.pdp.exception.CategoryNotFoundException;
import java.util.List;

public interface CategoryService {

  Category getCategory(Integer id) throws CategoryNotFoundException;

  List<CategoryDto> getCategories();
}
