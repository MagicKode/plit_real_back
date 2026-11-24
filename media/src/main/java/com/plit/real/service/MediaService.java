package com.plit.real.service;

import com.plit.real.dto.CategoryWithWorksResponse;
import com.plit.real.dto.MediaResponse;
import com.plit.real.dto.UploadResponse;
import com.plit.real.model.PortfolioCategory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MediaService {
    UploadResponse uploadFile(MultipartFile file, String category, String title) throws IOException;

    List<MediaResponse> getAllFiles();

    List<MediaResponse> getFilesByCategory(String category);

    void deleteFile(Long id);

    void deleteFilesByCategory(String category);

    void deletePhysicalFile(String url);
}
