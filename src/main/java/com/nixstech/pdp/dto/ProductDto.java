package com.nixstech.pdp.dto;

public record ProductDto(Integer id, Integer catId, String title, String productCode, String image,
                         String thumbnailImage, Integer price, String sku, String description,
                         String specs, String stock) {

}
