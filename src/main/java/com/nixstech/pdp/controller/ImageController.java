package com.nixstech.pdp.controller;

import com.nixstech.pdp.dto.ThumbnailRequestDto;
import com.nixstech.pdp.service.ImageService;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/images")
public class ImageController {

  private final ImageService imageService;
  private final JmsTemplate jmsTemplate;

  @Value("${amq.queue}")
  private String queue;

  @PostMapping
  public String upload(@RequestParam("image") MultipartFile imageFile) {
    try {
      return imageService.saveImage(imageFile);
    } catch (IOException e) {
      throw new IllegalArgumentException("Could not save image");
    }
  }

  @PostMapping("/thumbnail")
  public void createThumbnail(@RequestBody ThumbnailRequestDto request) {
    jmsTemplate.convertAndSend(queue, request.productId());
  }
}
