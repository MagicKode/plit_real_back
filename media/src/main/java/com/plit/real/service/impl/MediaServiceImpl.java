package com.plit.real.service.impl;

import com.plit.real.dto.MediaResponse;
import com.plit.real.dto.UploadResponse;
import com.plit.real.model.MediaFile;
import com.plit.real.repository.MediaRepository;
import com.plit.real.service.MediaService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MediaServiceImpl implements MediaService{

    private final MediaRepository mediaRepository;
    private final Path uploadDir = Paths.get("uploads");

    public MediaServiceImpl(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
        try {
            Files.createDirectories(uploadDir);
        } catch (IOException e) {
            throw new RuntimeException("Не удалось создать папку для загрузки", e);
        }
    }

    @Override
    public UploadResponse uploadFile(MultipartFile file) {
        try {
            String originalFilename = file.getOriginalFilename();
            String extension = "";
            if (originalFilename != null && originalFilename.contains(".")) {
                extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }
            String newFilename = UUID.randomUUID() + extension;

            //Сохраняем файл
            Path targetPath = uploadDir.resolve(newFilename);
            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

            MediaFile mediaFile = new MediaFile();
            mediaFile.setUrl("/uploads/" + newFilename);
            mediaFile.setOriginalName(originalFilename);
            MediaFile saved = mediaRepository.save(mediaFile);

            // Возаращаем URL (относительный путь)
            return new UploadResponse(saved.getId(), saved.getUrl());
        } catch (IOException e) {
            throw new RuntimeException("Не удалось сохранить файл", e);
        }
    }

    @Override
    public List<MediaResponse> getAllFiles() {
        return mediaRepository.findAll().stream()
                .map(f -> new MediaResponse(f.getId(), f.getUrl(), f.getOriginalName()))
                .collect(Collectors.toList());
    }
}
