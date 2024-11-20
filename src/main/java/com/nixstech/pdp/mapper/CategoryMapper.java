package com.nixstech.pdp.mapper;

import com.nixstech.pdp.dto.CategoryDto;
import com.nixstech.pdp.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

  CategoryDto toDto(Category category);
}
