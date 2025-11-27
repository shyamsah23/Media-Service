package com.I_Care.Media_Service.service;

import com.I_Care.Media_Service.dto.MediaFileDTO;
import com.I_Care.Media_Service.entity.MediaFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface MediaService {
    MediaFileDTO storeFile(MultipartFile file) throws IOException;

    public Optional<MediaFile> getFile(Long id);
}
