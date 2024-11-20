package com.nixstech.pdp.mapper;

import com.nixstech.pdp.dto.NewProductDto;
import com.nixstech.pdp.dto.ProductDto;
import com.nixstech.pdp.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

  ProductDto toDto(Product product);

  Product toEntity(NewProductDto newProductDto);
}
