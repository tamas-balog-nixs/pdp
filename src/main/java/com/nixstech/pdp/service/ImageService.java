package com.nixstech.pdp.service;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
  String saveImage(MultipartFile file) throws IOException;
}
