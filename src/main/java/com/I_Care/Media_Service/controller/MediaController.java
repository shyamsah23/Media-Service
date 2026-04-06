package com.I_Care.Media_Service.controller;

import com.I_Care.Media_Service.dto.MediaFileDTO;
import com.I_Care.Media_Service.entity.MediaFile;
import com.I_Care.Media_Service.service.MediaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/media")
public class MediaController {
    private final MediaService mediaService;

    public MediaController(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    Logger logger = LoggerFactory.getLogger(MediaController.class);

    @PostMapping("/upload")
    public ResponseEntity<MediaFileDTO> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            MediaFileDTO mediaFileDTO = mediaService.storeFile(file);
            return ResponseEntity.ok(mediaFileDTO);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
        Optional<MediaFile> mediaFileOptional = mediaService.getFile(id);
        if (mediaFileOptional.isPresent()) {
            MediaFile mediaFile = mediaFileOptional.get();
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + mediaFile.getName() + "\"")
                    .contentType(MediaType.parseMediaType(mediaFile.getType()))
                    .body(mediaFile.getData());
        } else return ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<MediaFileDTO>> getAllFiles() {
        List<MediaFileDTO> mediaFiles = mediaService.getAllFiles();
        return new ResponseEntity<>(mediaFiles,HttpStatus.OK);
    }


}
