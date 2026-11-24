package com.plit.real.service.impl;

import com.plit.real.dto.MediaResponse;
import com.plit.real.dto.UploadResponse;
import com.plit.real.model.MediaFile;
import com.plit.real.repository.MediaRepository;
import com.plit.real.service.MediaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class MediaServiceImpl implements MediaService {

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
    public UploadResponse uploadFile(MultipartFile file, String category, String title) {
        try {
            String originalFilename = file.getOriginalFilename();
            String extension = "";
            if (originalFilename != null && originalFilename.contains(".")) {
                extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }
            String newFilename = UUID.randomUUID() + extension;

            Path targetPath = uploadDir.resolve(newFilename);
            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

            MediaFile mediaFile = new MediaFile();
            mediaFile.setUrl("/uploads/" + newFilename);
            mediaFile.setOriginalName(originalFilename);
            mediaFile.setCategory(category);
            mediaFile.setTitle(title != null && !title.isBlank() ? title : category);
            MediaFile saved = mediaRepository.save(mediaFile);

            return new UploadResponse(saved.getId(), saved.getUrl(), saved.getCategory());
        } catch (IOException e) {
            throw new RuntimeException("Не удалось сохранить файл", e);
        }
    }

    @Override
    public List<MediaResponse> getAllFiles() {
        return mediaRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public List<MediaResponse> getFilesByCategory(String category) {
        return mediaRepository.findAllByCategoryOrderByIdDesc(category).stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public void deleteFile(Long id) {
        mediaRepository.findById(id).ifPresent(file -> {
            deletePhysicalFile(file.getUrl());
            mediaRepository.delete(file);
        });
    }

    @Override
    @Transactional
    public void deleteFilesByCategory(String category) {
        List<MediaFile> files = mediaRepository.findAllByCategoryOrderByIdDesc(category);
        files.forEach(f -> deletePhysicalFile(f.getUrl()));
        mediaRepository.deleteAll(files);
    }

    @Override
    public void deletePhysicalFile(String url) {
        if (url != null && url.startsWith("/uploads/")) {
            try {
                String filename = url.replace("/uploads/", "");
                Files.deleteIfExists(uploadDir.resolve(filename));
            } catch (IOException ignored) {
            }
        }
    }

    private MediaResponse toResponse(MediaFile f) {
        return new MediaResponse(f.getId(), f.getUrl(), f.getOriginalName(), f.getCategory(), f.getTitle());
    }
}
