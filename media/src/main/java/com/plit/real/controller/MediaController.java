package com.plit.real.controller;

import com.plit.real.dto.MediaResponse;
import com.plit.real.dto.UploadResponse;
import com.plit.real.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/media")
@RequiredArgsConstructor
public class MediaController {

    private final MediaService mediaService;

    @PostMapping("/upload")
    public ResponseEntity<UploadResponse> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "category", defaultValue = "other") String category,
            @RequestParam(value = "title", required = false) String title) {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        try {
            return ResponseEntity.ok(mediaService.uploadFile(file, category, title));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public ResponseEntity<List<MediaResponse>> getAllFiles(@PathVariable Long id) {
        mediaService.deleteFile(id);
        return ResponseEntity.notFound().build();
    }
}
