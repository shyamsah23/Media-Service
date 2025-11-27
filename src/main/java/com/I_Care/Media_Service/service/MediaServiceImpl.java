package com.I_Care.Media_Service.service;

import com.I_Care.Media_Service.dto.MediaFileDTO;
import com.I_Care.Media_Service.entity.MediaFile;
import com.I_Care.Media_Service.enums.Storage;
import com.I_Care.Media_Service.repository.MediaFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class MediaServiceImpl implements MediaService {

    @Autowired
    private MediaFileRepository mediaFileRepository;

    @Override
    public MediaFileDTO storeFile(MultipartFile file) throws IOException {
        MediaFile mediaFile = MediaFile.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .size(file.getSize())
                .data(file.getBytes())
                .storage(Storage.DB)
                .build();
        MediaFile savedFile = mediaFileRepository.save(mediaFile);
        return MediaFileDTO.builder()
                .id(savedFile.getId())
                .name(savedFile.getName())
                .type(savedFile.getType())
                .size(savedFile.getSize())
                .build();
    }

    @Override
    public Optional<MediaFile> getFile(Long id) {
        return mediaFileRepository.findById(id);
    }
}
